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

import com.anotexcel.annotations.ExcelColumn;

public class ColumnProperties {
	int position;
	boolean isSortAnnotationSet;
	boolean isGroupAnnotationSet;
	int sortPriority;
	int groupPriority;
	
	public ColumnProperties() {
		super();
	}
	public ColumnProperties(ExcelColumn excelColumnAnnotation) {
		super();
		this.position = excelColumnAnnotation.position();
		this.isSortAnnotationSet = excelColumnAnnotation.sort();
		this.isGroupAnnotationSet = excelColumnAnnotation.group();
		this.sortPriority = excelColumnAnnotation.sortPriority();
		this.groupPriority = excelColumnAnnotation.groupPriority();
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isSortAnnotationSet() {
		return isSortAnnotationSet;
	}
	public void setSortAnnotationSet(boolean isSortAnnotationSet) {
		this.isSortAnnotationSet = isSortAnnotationSet;
	}
	public boolean isGroupAnnotationSet() {
		return isGroupAnnotationSet;
	}
	public void setGroupAnnotationSet(boolean isGroupAnnotationSet) {
		this.isGroupAnnotationSet = isGroupAnnotationSet;
	}
	public int getSortPriority() {
		return sortPriority;
	}
	public void setSortPriority(int sortPriority) {
		this.sortPriority = sortPriority;
	}
	public int getGroupPriority() {
		return groupPriority;
	}
	public void setGroupPriority(int groupPriority) {
		this.groupPriority = groupPriority;
	}
	
}
