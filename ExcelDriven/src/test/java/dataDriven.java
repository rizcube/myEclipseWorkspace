import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	//identify testcases column by scanning first row
	// once column is identified then scan entire testcases column to identify purchase testcase row
	// after you grab purchase testcase row = pull all the data and feed into your testcase

	public ArrayList<String> getData(String testcaseName) throws IOException
	{
		ArrayList<String> a = new ArrayList();
		
		//fileInputStream argument
		FileInputStream fis = new FileInputStream("/Users/rizcube/eclipse-workspace/ExcelDriven/demoDataMicrosoft.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets();
		//System.out.println(sheets);
		
		for (int i =0; i<sheets; i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
					{
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			//identify testcases column by scanning first row
			Iterator<Row> rows = sheet.iterator(); // sheet is a collection of rows
			Row firstrow = rows.next();		
			
			
			Iterator<Cell> ce = firstrow.cellIterator(); // rows is collection of cells
			int k = 0;
			int column = 0;
			
			while(ce.hasNext())
			{
				Cell value = ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
					
				{
					column=k;
					System.out.println(value);
				}
			k++;
			
			}
			System.out.println(column);
			
			
			while(rows.hasNext())
			{
				Row r = rows.next();
				if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
				{
					//after you grab purchase testcase row = pull all the data and feed into your testcase
					Iterator<Cell> cv = r.cellIterator();
					while(cv.hasNext()) 
					{
						Cell c = cv.next();
						if(c.getCellType()==CellType.STRING )
						{
						a.add(cv.next().getStringCellValue());
						}
						else {
							a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							
						}
						}
					return a;
				}
			}
			
			}
			
			}
		return a;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

}
