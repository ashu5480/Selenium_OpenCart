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
import opencart.main.ProductSearch;

public class ProductSearchTest extends BaseClass{

	ProductSearch productSearch;
	
	@DataProvider(name="searchProduct")
	public static Object[][] productdetails() throws IOException{
		FileInputStream fis = new FileInputStream("D:\\SeleniumPractice\\OpenCart_Selenium\\test-data\\ProductList.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("sheet1");
		//int total_row = sheet.getPhysicalNumberOfRows();
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		log.info("Total Number of Row and Column :Row Count "+rowCount+"  "+"Column Count : "+colCount);
		Object[][] data = new Object[rowCount][colCount];
		for(int i=1; i<=rowCount; i++) {
			Row row = sheet.getRow(i);
			for(int j=0; j<colCount; j++) {
				data[i-1][j]=row.getCell(j).toString();
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
	
	@Test(dataProvider = "searchProduct")
	public void searchProduct(String product) {
		productSearch = new ProductSearch();
		productSearch.searchProduct(product);
	}
}
