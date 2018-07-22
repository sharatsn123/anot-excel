package com.anotexcel.example;

import com.anotexcel.annotations.Excel;
import com.anotexcel.annotations.ExcelColumn;

@Excel(name = "sortAndGroup")
public class SortAndGroupVo {
	@ExcelColumn(name="Name", position=3, sort = true, sortPriority=2)
	String studentName;
	
	@ExcelColumn(name="Year", position=2,  sort = true, sortPriority=1)
	Integer year;
	
	@ExcelColumn(name="School",position=1,group=true, sort = true)
	String school;

	public SortAndGroupVo(String studentName, Integer semester, String school) {
		super();
		this.studentName = studentName;
		this.year = semester;
		this.school = school;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
