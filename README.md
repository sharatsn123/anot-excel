# anot-excel
Annotation based excel export.
The application helps in creating excel sheet by just passing a list of POJO with proper annotations.
The data in the list will be compared with annotations in the class to create excel.
Configurations currently supported are filename, names of columns, position of columns, sorting and grouping the columns.
Examples are provided



	@Excel(name = "sortAndGroup")
	public class SortAndGroupVo {
  
	@ExcelColumn(name="Name", position=3, sort = true, sortPriority=2)
	String studentName;
	
	@ExcelColumn(name="Year", position=2,  sort = true, sortPriority=1)
	Integer year;
	
	@ExcelColumn(name="School",position=1,group=true, sort = true)
	String school;

