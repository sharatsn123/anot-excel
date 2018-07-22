/**
 * Copyright 2018 Sharat S Nair
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.anotexcel.beans;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelProperties {
	private List<Field> columnsOrderedByPosition;
	private List<Field> columnsToBeSortedOrderedByPriority;
	private List<Field> columnsToBeGroupedOrderedByPriority;
	private String name;
	
	
	public ExcelProperties(List<Field> columnsOrderedByPosition, List<Field> columnsToBeSortedOrderedByPriority,
			List<Field> columnsToBeGroupedOrderedByPriority,String name) {
		super();
		this.columnsOrderedByPosition = columnsOrderedByPosition;
		this.columnsToBeSortedOrderedByPriority = columnsToBeSortedOrderedByPriority;
		this.columnsToBeGroupedOrderedByPriority = columnsToBeGroupedOrderedByPriority;
		this.name=name;
	}
	
	public ExcelProperties() {
		super();
	}

	public List<Integer> getColumnPositionsToBeGrouped(){
		List<Integer> columnPositionsToBeGrouped = new ArrayList();
		Integer columnPosition=0;
		for(Field column : columnsOrderedByPosition){
			if(columnsToBeGroupedOrderedByPriority.contains(column)){
				columnPositionsToBeGrouped.add(columnPosition);
			}
			columnPosition++;
		}
		return columnPositionsToBeGrouped;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getColumnsOrderedByPosition() {
		return columnsOrderedByPosition;
	}
	public void setColumnsOrderedByPosition(List<Field> columnsOrderedByPosition) {
		this.columnsOrderedByPosition = columnsOrderedByPosition;
	}
	public List<Field> getColumnsToBeSortedOrderedByPriority() {
		return columnsToBeSortedOrderedByPriority;
	}
	public void setColumnsToBeSortedOrderedByPriority(List<Field> columnsToBeSortedOrderedByPriority) {
		this.columnsToBeSortedOrderedByPriority = columnsToBeSortedOrderedByPriority;
	}
	public List<Field> getColumnsToBeGroupedOrderedByPriority() {
		return columnsToBeGroupedOrderedByPriority;
	}
	public void setColumnsToBeGroupedOrderedByPriority(List<Field> columnsToBeGroupedOrderedByPriority) {
		this.columnsToBeGroupedOrderedByPriority = columnsToBeGroupedOrderedByPriority;
	}
	
}
