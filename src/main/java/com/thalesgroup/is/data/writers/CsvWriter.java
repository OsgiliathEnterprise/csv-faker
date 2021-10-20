package com.thalesgroup.is.data.writers;

/*-
 * #%L
 * faker
 * %%
 * Copyright (C) 2021 Osgiliath
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.thalesgroup.is.data.config.ApplicationProperties;
import com.thalesgroup.is.data.dto.RowAndIndex;
import com.thalesgroup.is.data.model.csv.CsvCell;
import com.thalesgroup.is.data.model.csv.CsvRow;
import com.thalesgroup.is.data.model.csv.CsvWorksheet;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.stream.Collectors;
import io.vavr.collection.Stream;

@Component
public class CsvWriter implements ModelWriter<CsvWorksheet> {

	private final ApplicationProperties applicationProperties;

	public CsvWriter(ApplicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}
	@Override
	public void write(CsvWorksheet worksheet, Path out) throws IOException {
		try (Writer writer = Files.newBufferedWriter(out)) {

			CustomCsvWriter csvWriter = new CustomCsvWriter(writer, applicationProperties.getDelimiter(), ICSVWriter.DEFAULT_QUOTE_CHARACTER, ICSVWriter.DEFAULT_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END);
			Stream.ofAll(java.util.stream.Stream.concat(Collections.singleton(worksheet.getHeaderRow().get()).stream(), worksheet.getRows().stream()))
			.zipWithIndex(
					(CsvRow row, Integer index) -> new RowAndIndex(index, row.getCells().stream().map(CsvCell::getValue).collect(Collectors.toList()).toArray(String[]::new))
			).forEach((RowAndIndex rowAsString) -> {
				if (rowAsString.getIndex().equals(0)) {
					csvWriter.writeNext(rowAsString.getRow(), true);
				} else {
					csvWriter.writeNext(rowAsString.getRow(), false);
				}
			});
			csvWriter.flush();
			csvWriter.close();
		}
	}
}
