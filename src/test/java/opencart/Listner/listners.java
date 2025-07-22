package opencart.Listner;

import org.testng.ITestListener;
import org.testng.ITestResult;
import opencart.baseClass.*;

public class listners implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		BaseClass.log.info("Test Case Execution Started : "+result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		BaseClass.log.info("Test Cases are success : "+result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		BaseClass.log.info("This test case is failed : "+result.getName());
		BaseClass.log.error("Failed Test Case"+result.getName()+" Reason : "+result.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		BaseClass.log.info("Test_Cases are Skipped : "+result.getName());
	}
}
