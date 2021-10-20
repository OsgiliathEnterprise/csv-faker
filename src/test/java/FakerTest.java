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
import com.thalesgroup.is.data.readers.CsvReader;
import com.thalesgroup.is.data.FakerBoostrap;
import com.thalesgroup.is.data.handlers.CsvFakerProcessHandler;
import com.thalesgroup.is.data.config.ApplicationProperties;
import com.thalesgroup.is.data.model.csv.CsvWorksheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FakerBoostrap.class)
@ExtendWith(SpringExtension.class)
public class FakerTest {

	@Autowired
	private CsvFakerProcessHandler handler;
	@Autowired
	private CsvReader csvReader;
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private ResourceLoader resourceLoader;


	@Test
	public void givenACsvWithConfiguredColumnToFakeWhenTheFakerProcessorPassThenColumnsAreFaked() throws IOException {
		handler.chain();
		Resource outPath =  resourceLoader.getResource(applicationProperties.getOutPath());
		CsvWorksheet out = csvReader.loadWorksheet(outPath.getFile().toPath());
		assertThat(out.getColumns().get(0).getCells().get(1).getValue()).isNotEqualTo("JAN");
		assertThat(out.getColumns().get(1).getCells().get(1).getValue()).isEqualTo("340");
		assertThat(out.getColumns().get(2).getCells().get(1).getValue()).isNotEqualTo("360");
		assertThat(out.getColumns().get(3).getCells().get(1).getValue()).isEqualTo("417");
	}
	@Test
	public void givenACsvColumWithIntegerToFakeWhenTheFakerProcessorPassThenColumnsAreFakedAndInteger() throws IOException {
		handler.chain();
		Resource outPath =  resourceLoader.getResource(applicationProperties.getOutPath());
		CsvWorksheet out = csvReader.loadWorksheet(outPath.getFile().toPath());
		Integer.parseInt((String) out.getColumns().get(1).getCells().get(1).getValue());
		Integer.parseInt((String) out.getColumns().get(2).getCells().get(1).getValue());
		Integer.parseInt((String) out.getColumns().get(3).getCells().get(1).getValue());
	}

}
