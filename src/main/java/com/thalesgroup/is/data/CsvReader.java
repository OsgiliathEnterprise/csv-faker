package com.thalesgroup.is.data;

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

import com.opencsv.CSVReader;
import com.thalesgroup.is.data.model.*;
import com.thalesgroup.is.data.states.CsvRowReader;
import io.vavr.collection.Stream;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CsvReader {

	private final CsvRowReader csvRowReader;

	public CsvReader(CsvRowReader csvRowReader) {
		this.csvRowReader = csvRowReader;

	}

	public CsvWorksheet loadWorksheet(Path path, Boolean withHeaders) throws IOException {
		CsvWorksheet worksheet = new CsvWorksheet();
		try (Reader reader = Files.newBufferedReader(path)) {
			final CSVReader csvReader = new CSVReader(reader);
			Optional<java.util.stream.Stream<String[]>> rowsAsStream = Optional.ofNullable(StreamSupport.stream(
					Spliterators.spliteratorUnknownSize(csvReader.iterator(), Spliterator.ORDERED),
					false));
			List<? extends CsvRow> cswRows = rowsAsStream
					.map((java.util.stream.Stream<String[]> rs) ->
							Stream.ofAll(rs)
									.filter((String[] r) -> null != r && r.length > 0)
									.zipWithIndex((String[] r, Integer rowIndex) -> csvRowReader.read(r, withHeaders, rowIndex)
									).filter(o -> !o.isEmpty()).map(Optional::get)
									.collect(Collectors.toList())
					).orElseGet(ArrayList::new);

			cswRows.forEach(worksheet::appendRow);

		}
		return worksheet;
	}

}
