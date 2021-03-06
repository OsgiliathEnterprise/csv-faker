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

import com.thalesgroup.is.data.model.csv.CsvCell;
import com.thalesgroup.is.data.model.csv.CsvHeaderCell;
import com.thalesgroup.is.data.model.csv.CsvHeaderRow;
import com.thalesgroup.is.data.model.csv.CsvRow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CellFactory {
	public CsvCell createCell(Integer columnIndex, Integer rowIndex, String value) {
		if (isHeaderRow(rowIndex)) {
			return new CsvHeaderCell(columnIndex, rowIndex, value);
		}
		return new CsvCell(columnIndex, rowIndex, value);
	}

	private boolean isHeaderRow(Integer rowIndex) {
		return rowIndex == 0;
	}

	public CsvRow createRow(Boolean isHeader, List<? extends CsvCell> cells) {
		if (isHeader) {
			return new CsvHeaderRow((List<CsvHeaderCell>) cells);
		}
		return new CsvRow(cells);
	}
}
