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
package com.anotexcel.example;

import java.util.ArrayList;
import java.util.List;

import com.anotexcel.generator.AnotExcelGenerator;

public class Example {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		List<SimpleVo> list = new ArrayList<SimpleVo>();
		list.add(new SimpleVo("Abc", 9, "Oxford"));
		list.add(new SimpleVo("Def", 5, "Oxford"));
		list.add(new SimpleVo("Ghi", 7, "Oxford"));
		list.add(new SimpleVo("Jkl", 5, "Oxford"));
		list.add(new SimpleVo("Mno", 1, "Oxford"));
		AnotExcelGenerator<SimpleVo> simpleExcelGenerator = new AnotExcelGenerator<SimpleVo>();
		simpleExcelGenerator.generateExcel(list);

		List<GroupVo> list2 = new ArrayList<GroupVo>();
		list2.add(new GroupVo("Abc", 9, "Oxford"));
		list2.add(new GroupVo("Def", 5, "Oxford"));
		list2.add(new GroupVo("Ghi", 7, "Oxford"));
		list2.add(new GroupVo("Jkl", 5, "Oxford"));
		list2.add(new GroupVo("Mno", 1, "Oxford"));
		AnotExcelGenerator<GroupVo> simpleExcelGenerator2 = new AnotExcelGenerator<GroupVo>();
		simpleExcelGenerator2.generateExcel(list2);

		List<SortVo> list3 = new ArrayList<SortVo>();
		list3.add(new SortVo("Abc", 9, "Oxford"));
		list3.add(new SortVo("Def", 5, "Oxford"));
		list3.add(new SortVo("Ghi", 7, "Oxford"));
		list3.add(new SortVo("Jkl", 5, "Oxford"));
		list3.add(new SortVo("Mno", 1, "Oxford"));
		AnotExcelGenerator<SortVo> simpleExcelGenerator3 = new AnotExcelGenerator<SortVo>();
		simpleExcelGenerator3.generateExcel(list3);

		List<SortAndGroupVo> list4 = new ArrayList<SortAndGroupVo>();
		list4.add(new SortAndGroupVo("Abc", 9, "Oxford"));
		list4.add(new SortAndGroupVo("Def", 5, "Oxford"));
		list4.add(new SortAndGroupVo("Ghi", 7, "Oxford"));
		list4.add(new SortAndGroupVo("Jkl", 5, "Oxford"));
		list4.add(new SortAndGroupVo("Mno", 1, "Oxford"));
		AnotExcelGenerator<SortAndGroupVo> simpleExcelGenerator4 = new AnotExcelGenerator<SortAndGroupVo>();
		simpleExcelGenerator4.generateExcel(list4);
	}

}
