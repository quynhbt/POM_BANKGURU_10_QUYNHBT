package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.DepositPageUI;
import commons.AbstractPage;
import commons.PageFactoryMananger;

public class DepositPageObject extends AbstractPage {
	private WebDriver driver;
	
	public DepositPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
		System.out.println("Driver at Deposit Page = " + driver.toString());
		
	}

}
