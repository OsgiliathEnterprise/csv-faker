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

import com.thalesgroup.is.data.config.ApplicationProperties;
import com.thalesgroup.is.data.model.CsvWorksheet;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


import java.io.IOException;

import java.nio.file.Path;

@Component
public class FakerProcessHandler {
	private final CsvReader reader;
	private final ColumnFaker faker;
	private final CsvWriter writer;
	private final ApplicationProperties applicationProperties;
	private final ResourceLoader resourceLoader;

	public FakerProcessHandler(CsvReader reader, ColumnFaker faker, CsvWriter writer, ApplicationProperties applicationProperties, ResourceLoader rl) {
		this.reader = reader;
		this.faker = faker;
		this.writer = writer;
		this.applicationProperties = applicationProperties;
		this.resourceLoader = rl;
	}

	public CsvWorksheet chain() throws IOException {
		Resource in =  resourceLoader.getResource(applicationProperties.getInPath());
		CsvWorksheet worksheet = reader.loadWorksheet(in.getFile().toPath(), Boolean.TRUE);
		faker.fake(worksheet, applicationProperties.getFakedColumns());
		Resource out =  resourceLoader.getResource(applicationProperties.getOutPath());
		writer.write(worksheet, out.getFile().toPath());
		return worksheet;
	}
}
