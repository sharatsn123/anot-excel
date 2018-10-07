# anot-excel
anot-excel helps in creating excel sheet by just passing a list of objects.
The data in the list will be compared with annotations in the class to create excel.
Configurations currently supported are filename, names of columns, position of columns, sorting and grouping the columns.
Consider the following class. (More examples are provided in code)

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
   
After creating a list of objects based on above class, i.e SortAndGroupVo, pass it to the generator to get the excel.
	
	List list = new ArrayList();
	list.add(new SortAndGroupVo("Abc", 9, "Oxford"));
	list.add(new SortAndGroupVo("Def", 5, "Oxford"));
	list.add(new SortAndGroupVo("Ghi", 7, "Oxford"));
	list.add(new SortAndGroupVo("Jkl", 5, "Oxford"));
	list.add(new SortAndGroupVo("Mno", 1, "Oxford"));
	AnotExcelGenerator simpleExcelGenerator = new AnotExcelGenerator();
	simpleExcelGenerator.generateExcel(list);


Excel obtained :

<table>
<tr>
	<td>School</td>
	<td>Year</td>
	<td>Name</td>
</tr>
	<tr>
		<td rowspan=5>Oxford</td>
		<td>1</td>
		<td>Mno</td>
	</tr>	
	<tr>
		<td>5</td>
		<td>Def</td>
	</tr>
	<tr>
		<td>5</td>
		<td>Jkl</td>
	</tr>
	<tr>
		<td>7</td>
		<td>Ghi</td>
	</tr>
	<tr>
		<td>9</td>
		<td>Abc</td>
	</tr>
</table>

