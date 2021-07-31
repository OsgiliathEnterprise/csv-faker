package com.thalesgroup.is.data.states;

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

import com.thalesgroup.is.data.CellFactory;

import com.thalesgroup.is.data.model.CsvCell;
import com.thalesgroup.is.data.model.CsvRow;
import io.vavr.collection.Stream;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CsvRowReader {

	private final CellFactory cellFactory;

	public CsvRowReader(CellFactory cellFactory) {
		this.cellFactory = cellFactory;
	}

	public Optional<? extends CsvRow> read(String[] line, Boolean hasHeader, int rowIndex) {
		List<? extends CsvCell> cells = Stream.of(line)
				.filter((String value) -> null != value && !value.isEmpty())
				.zipWithIndex((String value, Integer columnIndex) -> cellFactory.createCell(hasHeader, columnIndex, rowIndex, value.trim())).collect(Collectors.toList());
		if (!cells.isEmpty()) {
			CsvRow ret = cellFactory.createRow(hasHeader && rowIndex == 0, cells);
			return Optional.of(ret);
		}
		return Optional.<CsvRow> empty();
	}
}
