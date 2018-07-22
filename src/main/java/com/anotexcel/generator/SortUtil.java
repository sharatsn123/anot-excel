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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.anotexcel.exception.AnotExcelException;

public class SortUtil<T> {
	public List<T> getSortedList(List<T> list, final List<Field> fieldsInOrderOfSortingPriority)
			throws AnotExcelException {
		final Class<?> classObject = list.get(0).getClass();
		PropertyDescriptor propertyDescriptor;
		Method getter;

		for (Field field : fieldsInOrderOfSortingPriority) {
			try {
				propertyDescriptor = new PropertyDescriptor(field.getName(), classObject);
			} catch (IntrospectionException e) {
				throw new AnotExcelException("Getter/Setter not defined for " + field.getName(), e);
			}
			getter = propertyDescriptor.getReadMethod();
			Class<?> returnType = getter.getReturnType();

			if (!(Comparable.class.isAssignableFrom(returnType) || returnType.isPrimitive())) {
				throw new AnotExcelException("Field " + field.getName() + " is not comparable");
			}
		}

		Collections.sort(list, new Comparator<T>() {
			public int compare(T e1, T e2) {
				try {
					Method getter;
					PropertyDescriptor propertyDescriptor;
					int compareValue = 0;
					for (Field field : fieldsInOrderOfSortingPriority) {
						propertyDescriptor = new PropertyDescriptor(field.getName(), classObject);
						getter = propertyDescriptor.getReadMethod();
						Comparable val1 = (Comparable) getter.invoke(e1);
						Comparable val2 = (Comparable) getter.invoke(e2);
						compareValue = val1.compareTo(val2);
						if (compareValue != 0) {
							break;
						}
					}
					return compareValue;
				} catch (RuntimeException e) {
					throw e;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		return list;
	}

	public List<T> getSortedList(List<T> list, Field field) throws AnotExcelException {
		List<Field> fieldsInOrderOfSortingPriority = new ArrayList();
		fieldsInOrderOfSortingPriority.add(field);
		return getSortedList(list, fieldsInOrderOfSortingPriority);
	}
}
