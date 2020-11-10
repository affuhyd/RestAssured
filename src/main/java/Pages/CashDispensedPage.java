package Pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class CashDispensedPage extends TestBase {
	
	@FindBy(xpath="//div[contains(text(),'Cash dispensed')]")
	WebElement cash;
	
	public CashDispensedPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public boolean  displayTextPage() {
		return cash.isDisplayed();
	}
}
