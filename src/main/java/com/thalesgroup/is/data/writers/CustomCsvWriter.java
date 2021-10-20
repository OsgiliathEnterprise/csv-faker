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
import com.thalesgroup.is.data.ValueTypeTester;

import java.io.Writer;

public class CustomCsvWriter extends CSVWriter {

	public CustomCsvWriter(Writer writer, char separator, char quotechar, char escapechar, String lineEnd) {
		super(writer, separator, quotechar, escapechar, lineEnd);
	}
	@Override
	protected boolean stringContainsSpecialCharacters(String elem) {
		if (ValueTypeTester.isInteger(elem)) {
			return false;
		}
		return true;
	}
}
