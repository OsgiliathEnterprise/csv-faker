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

import com.thalesgroup.is.data.handlers.GenericProcessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class ApplicationStartup implements ApplicationRunner {
	private static final Logger LOG =
			LoggerFactory.getLogger(ApplicationStartup.class);
	private final GenericProcessHandler processHandler;

	public ApplicationStartup(GenericProcessHandler processHandler) {
		this.processHandler = processHandler;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Application started with option names : {}",
				args.getOptionNames());
		LOG.info("faking...");
		processHandler.chain();
		LOG.info("You're result has been generated");
	}
}
