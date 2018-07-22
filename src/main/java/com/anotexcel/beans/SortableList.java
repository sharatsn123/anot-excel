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

import java.util.ArrayList;
import java.util.List;

import com.anotexcel.exception.AnotExcelException;
import com.anotexcel.generator.SortUtil;

public class  SortableList {

	List<Sortable> list;

	
	public SortableList() {
		this.list = new ArrayList();
	}

	public void addElement(Object object, Integer position){
		list.add(new Sortable(object, position));
	}

	
	public List getSortedList() throws AnotExcelException{
		SortUtil sortUtil = new SortUtil();
		List sortedList = new ArrayList();
		if(this.list!=null && !this.list.isEmpty()){
			try {
				this.list= (List<Sortable>) sortUtil.getSortedList(this.list, Sortable.class.getDeclaredField("position"));
			} catch (NoSuchFieldException e) {
				throw new AnotExcelException(e); 
			}
			for(Sortable sortable : list){
				sortedList.add(sortable.getObject());
			}	
		}
		return sortedList;
	}

}