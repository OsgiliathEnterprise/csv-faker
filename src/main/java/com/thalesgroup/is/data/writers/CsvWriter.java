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
import java.util.stream.Stream;

@Component
public class CsvWriter implements ModelWriter<CsvWorksheet> {

	@Override
	public void write(CsvWorksheet worksheet, Path out) throws IOException {
		try (Writer writer = Files.newBufferedWriter(out)) {
			CSVWriter csvWriter = new CSVWriter(writer,
					CSVWriter.DEFAULT_SEPARATOR,
					CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
			Stream.concat(Collections.singleton(worksheet.getHeaderRow().get()).stream(), worksheet.getRows().stream())
			.map(
					(CsvRow row) -> row.getCells().stream().map(CsvCell::getValue).collect(Collectors.toList()).toArray(String[]::new)
			).forEach((String[] rowAsString) ->
				csvWriter.writeNext(rowAsString)
			);
		}
	}
}
