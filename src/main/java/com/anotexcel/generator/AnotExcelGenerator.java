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
package com.anotexcel.generator;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.anotexcel.annotations.Excel;
import com.anotexcel.annotations.ExcelColumn;
import com.anotexcel.beans.ColumnProperties;
import com.anotexcel.beans.ExcelData;
import com.anotexcel.beans.ExcelProperties;
import com.anotexcel.beans.SortableList;
import com.anotexcel.exception.AnotExcelException;

public class AnotExcelGenerator {

	public void generateExcel(List list) throws Exception {
		Class<?> objectClass;
		if (list != null && !list.isEmpty()) {
			objectClass = list.get(0).getClass();
		} else {
			throw new AnotExcelException("list should not be empty");
		}
		ExcelProperties excelProperties = getExcelPropertiesFromAnnotations(objectClass);
		ExcelData excelData = getExcelData(excelProperties, list);
		createExcel(excelData, excelProperties);
	}

	public void generateExcel(List list, String filePath) throws Exception {
		Class<?> objectClass;
		if (list != null && !list.isEmpty()) {
			objectClass = list.get(0).getClass();
		} else {
			throw new AnotExcelException("list should not be empty");
		}
		ExcelProperties excelProperties = getExcelPropertiesFromAnnotations(objectClass);
		ExcelData excelData = getExcelData(excelProperties, list);
		excelProperties.setName(filePath + excelProperties.getName());
		createExcel(excelData, excelProperties);
	}

	private ExcelProperties getExcelPropertiesFromAnnotations(Class objectClass) throws AnotExcelException {

		ColumnProperties columnProperties;

		SortableList allColumnsOrderedByPosition = new SortableList();
		SortableList columnsToBeSortedOrderedByPriority = new SortableList();
		SortableList columnsToBeGroupedOrderedByPriority = new SortableList();
		ExcelColumn excelColumnAnnotation;
		for (Field field : objectClass.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(ExcelColumn.class)) {
				excelColumnAnnotation = field.getAnnotation(ExcelColumn.class);
				columnProperties = new ColumnProperties(excelColumnAnnotation);

				// All columns of excel ordered based on annotations
				allColumnsOrderedByPosition.addElement(field, columnProperties.getPosition());

				// Columns that should be sorted (ordered by priority)
				if (columnProperties.isSortAnnotationSet()) {
					columnsToBeSortedOrderedByPriority.addElement(field, columnProperties.getSortPriority());
				}

				// Columns that should be grouped (ordered by priority)
				if (columnProperties.isGroupAnnotationSet()) {
					columnsToBeGroupedOrderedByPriority.addElement(field, columnProperties.getGroupPriority());
				}
			}
		}
		Excel excelAnot = (Excel) objectClass.getAnnotation(Excel.class);
		String excelFileName = excelAnot.name() + ".xlsx";
		return new ExcelProperties(allColumnsOrderedByPosition.getSortedList(),
				columnsToBeSortedOrderedByPriority.getSortedList(), columnsToBeGroupedOrderedByPriority.getSortedList(),
				excelFileName);
	}

	private ExcelData getExcelData(ExcelProperties excelProperties, List list)
			throws IllegalAccessException, AnotExcelException {

		List<List<String>> dataList = new ArrayList();
		;
		List<String> headerList = new ArrayList();
		List<Field> allColumns = excelProperties.getColumnsOrderedByPosition();
		String headerValue;
		SortUtil sortUtil = new SortUtil();
		list = sortUtil.getSortedList(list, excelProperties.getColumnsToBeSortedOrderedByPriority());
		for (Field field : allColumns) {
			headerValue = field.getAnnotation(ExcelColumn.class).name();
			headerList.add(headerValue);
		}
		dataList.add(headerList);
		List<Integer> columnPositionsToBeGrouped = excelProperties.getColumnPositionsToBeGrouped();
		List<CellRangeAddress> cellRangeToBeGroupedList = new ArrayList();
		;
		List<String> rowData;
		String dataValue;
		for (Object o : list) {
			rowData = new ArrayList();
			for (Field field : allColumns) {
				dataValue = field.get(o).toString();
				rowData.add(dataValue);
			}
			dataList.add(rowData);
		}
		boolean valueChanged;
		String prevValue;
		Integer start;
		Integer end;
		for (Integer columnPosition : columnPositionsToBeGrouped) {
			prevValue = "";
			start = -1;
			end = -1;
			for (List<String> row : dataList) {
				if (row.get(columnPosition).equals(prevValue)) {
					end++;
				} else {
					if (start != end) {
						cellRangeToBeGroupedList.add(new CellRangeAddress(start, end, columnPosition, columnPosition));
					}
					end++;
					start = end;
				}
				prevValue = row.get(columnPosition);
			}
			if (start != end) {
				cellRangeToBeGroupedList.add(new CellRangeAddress(start, end, columnPosition, columnPosition));
			}
		}
		return new ExcelData(dataList, cellRangeToBeGroupedList);
	}

	private void createExcel(ExcelData excelData, ExcelProperties excelProperties) throws Exception {
		Workbook workbook;
		String fileName = excelProperties.getName();
		workbook = new XSSFWorkbook();
		List<List<String>> dataList = excelData.getDataList();
		Sheet sheet = workbook.createSheet("1");
		int rowIndex = 0;
		int columnIndex = 0;
		Row row;
		Cell cell;
		for (List<String> rowData : dataList) {
			row = sheet.createRow(rowIndex++);
			columnIndex = 0;
			for (String columnData : rowData) {
				cell = row.createCell(columnIndex++);
				cell.setCellValue(columnData);
			}
		}
		List<CellRangeAddress> cellRangeToBeGroupedList = excelData.getCellRangeToBeGroupedList();
		for (CellRangeAddress cellRangeAddress : cellRangeToBeGroupedList) {
			sheet.addMergedRegion(cellRangeAddress);
			row = sheet.getRow(cellRangeAddress.getFirstRow());
			cell = row.getCell(cellRangeAddress.getFirstColumn());
			CellUtil.setAlignment(cell, workbook, CellStyle.ALIGN_CENTER);
			CellStyle centreAlign = workbook.createCellStyle();
			centreAlign.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cell.setCellStyle(centreAlign);
		}
		sheet.setAutoFilter(new CellRangeAddress(0, sheet.getLastRowNum(), 0, columnIndex - 1));
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		fos.close();
	}

}
