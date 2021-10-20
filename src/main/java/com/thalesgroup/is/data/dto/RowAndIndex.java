package com.thalesgroup.is.data.dto;

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

import com.thalesgroup.is.data.model.csv.CsvRow;

public class RowAndIndex {
	private final Integer index;
	private final String[] row;

	public Integer getIndex() {
		return index;
	}

	public String[] getRow() {
		return row;
	}

	public RowAndIndex(Integer index, String[] row) {

		this.index = index;
		this.row = row;
	}


}