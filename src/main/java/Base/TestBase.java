package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.TestUtils;
import io.restassured.RestAssured;

public class TestBase {
	public static Properties prop_global;
	public static WebDriver driver;
	public static Properties prop_singleRecord;
	public static Properties prop_invalidRecord;

	public TestBase() throws IOException {
		prop_global = new Properties();
		prop_singleRecord = new Properties();
		prop_invalidRecord = new Properties();

		FileInputStream global_prop = new FileInputStream("src/test/resources/global.properties");
		FileInputStream record_prop = new FileInputStream("src/test/java/TestData/SingleRecord.properties");
		FileInputStream invalid_prop = new FileInputStream("src/test/java/TestData/InvalidRecord.properties");
		
		prop_global.load(global_prop);
		prop_singleRecord.load(record_prop);
		prop_invalidRecord.load(invalid_prop);
	}

	public static void initialization() {
		RestAssured.baseURI = prop_global.getProperty("base_url");
	}

	public static void driverInit() {

		if (prop_global.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", TestUtils.ChromeDriverPath);
			driver = new ChromeDriver();
		}
		if (prop_global.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", TestUtils.FireFoxDriverPath);
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop_global.getProperty("base_url"));
	}
}
