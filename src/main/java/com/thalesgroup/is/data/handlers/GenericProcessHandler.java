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

import com.thalesgroup.is.data.writers.ModelWriter;
import com.thalesgroup.is.data.fakers.ObjectFaker;
import com.thalesgroup.is.data.readers.ResourceReader;
import com.thalesgroup.is.data.config.ApplicationProperties;
import com.thalesgroup.is.data.model.ModeledElement;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public abstract class GenericProcessHandler<R extends ResourceReader, M extends ModeledElement, F extends ObjectFaker, W extends ModelWriter> implements FakerProcessHandler {

	protected final ApplicationProperties applicationProperties;
	protected final ResourceLoader resourceLoader;
	private final F faker;
	private final W writer;
	private final R reader;

	public GenericProcessHandler(ApplicationProperties applicationProperties, ResourceLoader resourceLoader, R reader, F faker, W writer) {
		this.applicationProperties = applicationProperties;
		this.resourceLoader = resourceLoader;
		this.reader = reader;
		this.faker = faker;
		this.writer = writer;
	}

	@Override
	public void chain() throws IOException {
		Resource in =  resourceLoader.getResource(applicationProperties.getInPath());
		M worksheet = loadModel(in);
		faker.fake(worksheet, applicationProperties.getFakedColumns());
		write(worksheet);

	}

	private void write(M worksheet) throws IOException {
		Resource out =  resourceLoader.getResource(applicationProperties.getOutPath());
		writer.write(worksheet, out.getFile().toPath());
	}

	protected M loadModel(Resource in) throws IOException {
		return (M) reader.loadWorksheet(in.getFile().toPath());
	}

}
