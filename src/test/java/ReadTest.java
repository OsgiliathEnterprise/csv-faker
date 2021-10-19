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
import com.opencsv.exceptions.CsvException;
import com.thalesgroup.is.data.readers.CsvReader;
import com.thalesgroup.is.data.FakerBoostrap;
import com.thalesgroup.is.data.model.csv.CsvWorksheet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

@SpringBootTest(classes = FakerBoostrap.class)
@ExtendWith(SpringExtension.class)
public class ReadTest {

	@Autowired
	private CsvReader csvReader;

	@Test
	public void testWorksheetColumnheaderWhileReadCsvWithHeaderAndLoad() throws CsvException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Path testFileWithHeaders = Path.of(URI.create("file:///" + classLoader.getResource("airtravel.csv").getPath()));
		CsvWorksheet worksheet = csvReader.loadWorksheet(testFileWithHeaders);
		assertThat(4).isEqualTo(worksheet.getHeaderRow().get().getCells().size());
	}

	@Test
	public void testWorksheetRowsWhileReadCsvWithLoad() throws CsvException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Path testFileWithHeaders = Path.of(URI.create("file:///" + classLoader.getResource("airtravel.csv").getPath()));
		CsvWorksheet worksheet = csvReader.loadWorksheet(testFileWithHeaders);
		assertThat(12).isEqualTo(worksheet.getRows().size());
	}

	@Test
	public void testWorksheetCellsWhileReadCsvWithHeaderAndLoad() throws CsvException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Path testFileWithHeaders = Path.of(URI.create("file:///" + classLoader.getResource("airtravel.csv").getPath()));
		CsvWorksheet worksheet = csvReader.loadWorksheet(testFileWithHeaders);
		assertThat(52).isEqualTo(worksheet.getCells().size());
	}
}
