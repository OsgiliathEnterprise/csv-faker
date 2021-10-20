package com.thalesgroup.is.data.model.csv;

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

import com.thalesgroup.is.data.model.ModeledElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CsvWorksheet implements ModeledElement {
	private List<CsvRow> rows = new ArrayList<>();

	public List<CsvCell> getCells() {
		return rows.stream().flatMap(
				(CsvRow row) -> row.getCells().stream()
		).collect(Collectors.toList());
	}

	public Optional<CsvHeaderRow> getHeaderRow() {
		return !rows.isEmpty() ? Optional.of((CsvHeaderRow) rows.iterator().next()):Optional.empty();
	}

	public List<CsvColumn> getColumns() {
		List<CsvColumn> ret = new ArrayList<>();
		rows.stream().flatMap(row -> row.getCells().stream())
				.forEach(cell
						-> {

								if (cell.getX() == ret.size()) {// new Column
									CsvColumn column = new CsvColumn();
									ret.add(column);
								}
								ret.get(cell.getX()).appendCell(cell);
								}
						);
		return ret;
	}

	public List<CsvRow> getRows() {
		if (rows.size() > 0) {
			return rows.stream().skip(1).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}


	public void appendRow(CsvRow csvRow) {
		rows.add(csvRow);
	}
}
