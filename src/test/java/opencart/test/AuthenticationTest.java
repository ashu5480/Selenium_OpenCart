package opencart.test;

import org.testng.annotations.Test;

import opencart.baseClass.BaseClass;
import opencart.main.Authentication;

public class AuthenticationTest extends BaseClass{

	Authentication signup;
	
	
	
	@Test
	public void signupUser() throws InterruptedException {
		signup = new Authentication();
		String emailID = "singhashu78@gmail.com";
		String password = "Ashu@123";
		String firstName = "Ashu";
		String lastName = "Singh";
		String birthMonth = "March";
		String birthYear = "1997";
		signup.createAccount(emailID, password, firstName, lastName, birthMonth, birthYear);
	}
	
	@Test
	public void loginUser() {
		signup = new Authentication();
		signup.loginUser("Singhashu78@gmail.com", "Ashu@123");
	}
}
