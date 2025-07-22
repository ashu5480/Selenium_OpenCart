package opencart.main;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import opencart.baseClass.BaseClass;

public class ExploreTutorials extends BaseClass {
	public ExploreTutorials() {
		PageFactory.initElements(driver, this);
	}
	JavascriptExecutor js = (JavascriptExecutor)driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	@FindBy(xpath="//section[@role='listitem']//button")
	WebElement mainMenu;
	
	@FindBy(xpath="//div[text()='Content management']")
	WebElement contentHeading;
	
	@FindBy(xpath="//div[text()='GenStudio for Performance Marketing']")
	WebElement genStudio;
	
	@FindBy(xpath="//a[text()='Try for free']")
	WebElement tryForFreeBtn;
	
	@FindBy(xpath="//div[@class='marketo']")
	WebElement switchForm;
	
	@FindBy(id="FirstName")
	WebElement fname;
	
	@FindBy(name="LastName")
	WebElement lname;
	
	@FindBy(id="Email")
	WebElement emailId;
	
	@FindBy(id="mktoFormsJobTitle")
	WebElement roleSelect;
	
	@FindBy(id="mktoFormsFunctionalArea")
	WebElement departmentSelect;
	
	@FindBy(id="mkto_FormsCompany")
	WebElement orgName;
	
	@FindBy(id="Country")
	WebElement country;
	
	@FindBy(xpath="//button[@id='mktoButton_new']")
	WebElement subBtn;
	
	@FindBy(xpath="//div[@class='georouting-wrapper fragment']")
	WebElement domUnexpected;
	
	@FindBy(xpath="//button[@class='dialog-close']")
	WebElement closebtn;
	
	
	public void enquiry(String firstName, String lastName, String email, String roleName, String department, String organisation, String country) {
		mainMenu.click();
		log.info("Clicked on MainMenu Button");
		wait.until(ExpectedConditions.visibilityOf(contentHeading));
		log.info("Content is Visible and clicked on that");
		genStudio.click();
		log.info("Clicked on Genstudio");
		js.executeScript("arguments[0].click()",tryForFreeBtn);
		if(domUnexpected.isDisplayed()) {
			closebtn.click();
		}
		wait.until(ExpectedConditions.visibilityOf(switchForm));
		fname.sendKeys(firstName);
		lname.sendKeys(lastName);
		emailId.sendKeys(email);
		Select s1 = new Select(roleSelect);
		s1.selectByValue(roleName);
		log.info("Selected Country whihc is : "+roleName);
		Select s2 = new Select(departmentSelect);
		s2.selectByVisibleText(department);
		log.info("Selected Department which is : "+department);
		orgName.sendKeys(organisation);
		log.info("Selected organistaion : "+organisation);
		Select s3 = new Select(this.country);
		s3.selectByValue(country);
		log.info("Selected Country which is : "+country);
		subBtn.click();
		log.info("Operation Succesfull.");
	}
}
