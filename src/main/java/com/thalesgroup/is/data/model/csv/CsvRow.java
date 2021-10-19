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

import java.util.ArrayList;
import java.util.List;

public class CsvRow {
	private List<? extends CsvCell> cells = new ArrayList<>();

	public CsvRow(List<? extends CsvCell> cells) {
		this.cells = cells;
	}

	public List<? extends CsvCell> getCells() {
		return cells;
	}

	public void setCells(List<CsvCell> cells) {
		this.cells = cells;
	}
}
