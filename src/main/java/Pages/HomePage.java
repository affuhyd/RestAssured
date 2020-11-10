package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Dispense Now')]")
	WebElement btn_DispenseNow;

	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public String  getBtnColor() {
		return btn_DispenseNow.getCssValue("background-color");
	}
	public CashDispensedPage clickDispenseNow() throws IOException {
		btn_DispenseNow.click();
		return new CashDispensedPage();
	}
	
	public String getDispenseNowBtnText() {
		return btn_DispenseNow.getText();
	}
}
