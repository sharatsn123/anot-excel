package com.anotexcel.example;

import com.anotexcel.annotations.Excel;
import com.anotexcel.annotations.ExcelColumn;

@Excel(name="simple")
public class SimpleVo {
	
	@ExcelColumn(name="Name", position = 3)
	private String name;
	
	@ExcelColumn(name="Year", position = 2)
	private Integer year;

	@ExcelColumn(name="School", position = 1)
	private String school;
	

	public SimpleVo(String name, Integer year, String school) {
		super();
		this.name = name;
		this.year = year;
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
		
	
	
	
}
