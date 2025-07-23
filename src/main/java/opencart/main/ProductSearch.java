package opencart.main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import opencart.baseClass.BaseClass;

public class ProductSearch extends BaseClass{

	public ProductSearch() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='JFPqaw']")
	WebElement unexpectedDom;
	
	@FindBy(xpath="//span[@role='button']")
	WebElement roleBtn;
	
	@FindBy(name="q")
	WebElement searchBox;
	
	public void searchProduct(String product) {
			searchBox.sendKeys(product);
			log.info("Searched Product which is : "+product);
	}
}
