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
	}
	
Meaning of Annotations
1) @Excel
   1) Specifies that objects of this class is used for excel export. 
   2) Parameter name specifies the name of excel file.
   
   
2) @ExcelColumn
   1) Marks the data member as a column in excel.
   2) Parameter name specifies the name of the column. This is a Mandatory param.
   3) position specifies the positionn of the column from left. This is a Mandatory param.
   4) sort specifies whether that column should be sorted in excel. This is Optional.
   5) sortPriority is used when multiple columns need to be sorted. In the above example, rows will be sorted by year first as it has higher priority(smaller the number, higher will be the priority). But if two years are same, name will be used to sort the rows
   

