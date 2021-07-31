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

import com.github.javafaker.Faker;
import com.thalesgroup.is.data.model.CsvCell;
import com.thalesgroup.is.data.model.CsvColumn;
import com.thalesgroup.is.data.model.CsvWorksheet;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class ColumnFaker {


	public void fake(CsvWorksheet worksheet, Collection<String> fakedColumns) {
		Faker faker = Faker.instance(new Random());
		List<CsvColumn> columns = worksheet.getColumns();
		columns.stream()
				.filter((CsvColumn column) -> column.getHeaderCell().isPresent())
				.filter((CsvColumn column) -> fakedColumns.contains(column.getHeaderCell().get().getValue()))
				.flatMap((CsvColumn column) -> column.getCells().stream().skip(1))
				.map((CsvCell cell) -> {
					cell.setValue(faker.dragonBall().character());
					return cell;
				}).collect(Collectors.toList());
	}
}
