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
import com.thalesgroup.is.data.FakerBoostrap;
import com.thalesgroup.is.data.FakerProcessHandler;
import com.thalesgroup.is.data.model.CsvWorksheet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FakerBoostrap.class)
@ExtendWith(SpringExtension.class)
public class FakerTest {

	@Autowired
	private FakerProcessHandler handler;
	@Test
	public void givenACsvWithConfiguredColumnToFakeWhenTheFakerProcessorPassThenColumnsAreFaked() throws IOException {
		CsvWorksheet worksheet = handler.chain();
		assertThat(worksheet.getColumns().get(0).getCells().get(1).getValue()).isNotEqualTo("JAN");
		assertThat(worksheet.getColumns().get(1).getCells().get(1).getValue()).isEqualTo("340");
		assertThat(worksheet.getColumns().get(2).getCells().get(1).getValue()).isNotEqualTo("360");
		assertThat(worksheet.getColumns().get(3).getCells().get(1).getValue()).isEqualTo("417");
	}
}
