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

import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelData {
	private List<List<String>> dataList;
	private List<CellRangeAddress> cellRangeToBeGroupedList;

	public ExcelData(List<List<String>> dataList, List<CellRangeAddress> cellRangeToBeGroupedList) {
		super();
		this.dataList = dataList;
		this.cellRangeToBeGroupedList = cellRangeToBeGroupedList;
	}

	public ExcelData() {
		super();
	}

	public List<List<String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<List<String>> dataList) {
		this.dataList = dataList;
	}

	public List<CellRangeAddress> getCellRangeToBeGroupedList() {
		return cellRangeToBeGroupedList;
	}

	public void setCellRangeToBeGroupedList(List<CellRangeAddress> cellRangeToBeGroupedList) {
		this.cellRangeToBeGroupedList = cellRangeToBeGroupedList;
	}

}
