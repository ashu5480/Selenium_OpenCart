package opencart.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import opencart.baseClass.BaseClass;
import opencart.main.ExploreTutorials;

public class ExploreTutorialTest extends BaseClass {

	public ExploreTutorialTest() {
		super();
	}

	ExploreTutorials exploreTutorials;

	@DataProvider(name = "userData")
	public Object[][] provideUserData() {
		return new Object[][] { { "John", "Doe", "john.doe@example.com", "ANALYST", "Digital Commerce", "TechCorp", "US" },
				{ "Alice", "Smith", "alice.smith@example.com", "ANALYST", "Marketing: Advertising/Brand", "CodeInc", "IN" },
				{ "Bob", "Brown", "bob.brown@example.com","SENIOR_VP", "IT: Security" ,"SoftSolutions", "IT" } };
	}
	
	@DataProvider(name="userExcel")
	public static Object[][] getDataFromExcel() throws IOException{
		FileInputStream fis = new FileInputStream("D:\\SeleniumPractice\\OpenCart_Selenium\\test-data\\usersdetails.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		int RowCount = sheet.getLastRowNum();
		int ColCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[RowCount-1][ColCount];
		for(int i=1; i<RowCount; i++) {
			Row row = sheet.getRow(i);
			for(int j=0; j<ColCount; j++) {
				data[i-1][j]=row.getCell(j).toString();
			}
		}
		workbook.close();
		fis.close();
		return data;
	}

	@Test(dataProvider = "userExcel")
	public void exploreTut(String firstName, String lastName, String email,
            String roleName, String department, String organisation, String country) {
		exploreTutorials = new ExploreTutorials();
		exploreTutorials.enquiry(firstName, lastName, email, roleName, department, organisation,country);
	}
}
