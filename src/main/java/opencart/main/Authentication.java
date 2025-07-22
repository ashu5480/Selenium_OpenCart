package opencart.main;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.util.method.Messages;
import opencart.baseClass.BaseClass;

public class Authentication extends BaseClass{

	public Authentication() {
		PageFactory.initElements(driver, this);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	@FindBy(xpath="//button[text()='Sign In']")
	WebElement signInBtn;
	
	@FindBy(xpath="//a[text()='Experience Cloud']")
	WebElement experienceCloud;
	
	@FindBy(id="react-aria8341625099-6")
	WebElement continueBtn;
	
	@FindBy(xpath="//span[text()=' Create an account ']")
	WebElement createAccount;
	
	@FindBy(id="Signup-EmailField")
	WebElement signUpEmailField;
	
	@FindBy(xpath="//span[text()='An account with this email address already exists. ']")
	WebElement errorMsg;
	
	@FindBy(id="Signup-PasswordField")
	WebElement signUpPasswordField;
	
	@FindBy(xpath="//button[@data-id='Signup-CreateAccountBtn']")
	WebElement signUpContinueBtn;
	
	@FindBy(id="Signup-FirstNameField")
	WebElement signupFirstNameField;
	
	@FindBy(id="Signup-LastNameField")
	WebElement signupLastNameField;
	
	@FindBy(id="Signup-DateOfBirthChooser-Month")
	WebElement signupDateOfBirthChooserMonth;
	
	@FindBy(id="//div[@data-testid='popover']")
	WebElement monthPopOver;
	
	@FindBy(xpath="//span[text()='March']")
	WebElement monthChoose;
	
	@FindBy(xpath="//input[@data-id='Signup-DateOfBirthChooser-Year']")
	WebElement yearChoose;
	
	@FindBy(xpath="//button[@data-id='Signup-CreateAccountBtn']")
	WebElement createAccountBtn;
	
	@FindBy(xpath="//h1[text()='Welcome to Adobe Experience Cloud']")
	WebElement afterSignupMsg;
	
	@FindBy(id="EmailPage-EmailField")
	WebElement emailId;
	
	@FindBy(xpath="//button[@data-id='EmailPage-ContinueButton']")
	WebElement continueEmail;
	
	@FindBy(xpath="//input[@data-id='PasswordPage-PasswordField']")
	WebElement emailPwd;
	
	@FindBy(xpath="//input[@data-id='PasswordPage-RememberMeButton']")
	WebElement rememberBtn;
	
	@FindBy(xpath="//button[@data-id='PasswordPage-ContinueButton']")
	WebElement pwdContinue;
	
	@FindBy(xpath="//a[text()='Go to Enterprise Support']")
	WebElement mainPage;
	
	@FindBy(xpath="//div[@class='signup-dialog-container step1-container']")
	WebElement closeDialog;
	
	@FindBy(xpath="//div[@class='signup-dialog-close-bar']/span")
	WebElement closeBtn;
	
	public void createAccount(String emailId, String password, String fname, String lname, String birthMonth, String year) throws InterruptedException {
		js.executeScript("arguments[0].click()", signInBtn);
		wait.until(ExpectedConditions.visibilityOf(experienceCloud));
		js.executeScript("arguments[0].click()", experienceCloud);
		createAccount.click();
		signUpEmailField.sendKeys(emailId);
			signUpPasswordField.sendKeys(password);
			Thread.sleep(3000);
			signUpContinueBtn.click();
			signupFirstNameField.sendKeys(fname);
			signupLastNameField.sendKeys(lname);
			js.executeScript("arguments[0].click()", signupDateOfBirthChooserMonth);
			driver.findElement(By.xpath("//span[text()='"+birthMonth+"']")).click();
			yearChoose.sendKeys(year);
			js.executeScript("arguments[0].click()", createAccountBtn);
			String Actual_Msg = afterSignupMsg.getText();
			String Expected_Msg = Messages.adobeAccountCreationMsg;
			Assert.assertEquals(Actual_Msg, Expected_Msg);
			System.out.println("Account is created...");
			log.info("User Account is Created");
			mainPage.click();
			log.info("Navigated to main Page");
	}
	
	public void loginUser(String email, String pwd) {
		js.executeScript("arguments[0].click()", signInBtn);
		wait.until(ExpectedConditions.visibilityOf(experienceCloud));
		js.executeScript("arguments[0].click()", experienceCloud);
		emailId.sendKeys(email);
		continueEmail.click();
		emailPwd.sendKeys(pwd);
		pwdContinue.click();
		String Actual_Msg = afterSignupMsg.getText();
		String Expected_Msg = Messages.adobeAccountCreationMsg;
		Assert.assertEquals(Actual_Msg, Expected_Msg);
		System.out.println("Account is created...");
		log.info("User Account is LoggedIn");
		mainPage.click();
		log.info("Navigated to main Page");
	}
}
