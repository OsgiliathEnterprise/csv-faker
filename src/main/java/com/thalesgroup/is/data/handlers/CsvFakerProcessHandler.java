package com.thalesgroup.is.data.handlers;

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

import com.thalesgroup.is.data.fakers.ColumnFaker;
import com.thalesgroup.is.data.Csv;
import com.thalesgroup.is.data.readers.CsvReader;
import com.thalesgroup.is.data.writers.CsvWriter;
import com.thalesgroup.is.data.config.ApplicationProperties;
import com.thalesgroup.is.data.model.csv.CsvWorksheet;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Csv
@Component
public class CsvFakerProcessHandler extends GenericProcessHandler<CsvReader, CsvWorksheet, ColumnFaker, CsvWriter> implements FakerProcessHandler {

	public CsvFakerProcessHandler(CsvReader reader, ColumnFaker faker, CsvWriter writer, ApplicationProperties applicationProperties, ResourceLoader rl) {
		super(applicationProperties, rl, reader, faker, writer);
	}

}
