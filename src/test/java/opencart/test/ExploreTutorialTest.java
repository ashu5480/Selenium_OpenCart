package opencart.test;

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

	@Test(dataProvider = "userData")
	public void exploreTut(String firstName, String lastName, String email,
            String roleName, String department, String organisation, String country) {
		exploreTutorials = new ExploreTutorials();
		exploreTutorials.enquiry(firstName, lastName, email, roleName, department, organisation,country);
	}
}
