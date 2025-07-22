package opencart.baseClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	protected static WebDriver driver;
	private static Properties prop;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	public static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Log.class);
	JavascriptExecutor js = (JavascriptExecutor)driver;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis;
			fis = new FileInputStream("src/main/resource/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String Screenshots(WebDriver driver, String screString) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yy-MM-dd-hh-mm-ss").format(new Date(0));
		String destFile = prop.getProperty("user.dir") + "/Screenshots/" + dateName + ".png";
		File destfile = new File(destFile);
		FileUtils.copyFile(srcFile, destfile);
		return destFile;
	}

	@BeforeTest
	public static ExtentReports setReport() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("/testoutput/Report.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		extentReports.setSystemInfo("Author", "Ashutosh Singh");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Project", "OpenCart");

		extentSparkReporter.config().setDocumentTitle("OpenCart Reports");
		extentSparkReporter.config().setReportName("OpenCart-Selenium");
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("UTF-8");

		return extentReports;
	}

	@BeforeMethod
	public static void authenticate() {
		String BrowserName = prop.getProperty("BrowserName");
		if (BrowserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			driver = new ChromeDriver(options);
			options.setAcceptInsecureCerts(true);
			log.info("Chrome Browser Initialized");
		} else if (BrowserName.equals("firefox")) {
			driver = new FirefoxDriver();
			log.info("Firefox Browser Initialized");
		} else if (BrowserName.equals("edge")) {
			driver = new EdgeDriver();
			log.info("Edge Browser Initialized");
		} else {
			System.err.println("Driver Not Found Please check");
			log.info("Driver Browser Not Found");
		}
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@AfterMethod
	public void afterResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.PASS, MarkupHelper.createLabel("This test case is passed : " + result.getName() + " ",
					ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel("This TestCase is failed : " + result.getName() + " ", ExtentColor.RED));
			extentTest.fail(result.getThrowable());
			String ScreenShot = Screenshots(driver, result.getName());
			extentTest.addScreenCaptureFromBase64String(ScreenShot);
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest = extentReports.createTest(result.getName());
			extentTest.log(Status.SKIP, MarkupHelper
					.createLabel("This Test Case is Skipped : " + result.getName() + " ", ExtentColor.YELLOW));
			extentTest.skip(result.getThrowable());
		}
	}

	@AfterTest
	public void clearReport() {
		extentReports.flush();
		driver.close();
		log.info("TestCases Suits are Done");
	}
}
