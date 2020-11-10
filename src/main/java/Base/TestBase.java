	package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utilities.TestUtils;
import io.restassured.RestAssured;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;

	public TestBase() throws IOException {
		prop = new Properties();
		FileInputStream input = new FileInputStream("src/main/resources/global.properties");
		prop.load(input);
	}

	public static void initialization() {
		RestAssured.baseURI = prop.getProperty("base_url");
	}
	
	public static void driverInit() {
		
		System.setProperty("webdriver.chrome.driver", "");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("base_url"));
		
	}
}
