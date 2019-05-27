package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.RegisterPageObject;

public class PageFactoryMananger {
	
	public static LoginPageObjects getLoginPage(WebDriver driver) {
		return new LoginPageObjects(driver);
		
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
		
	}
	
	public static DepositPageObject getDeposiPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}
	
	public static FundTransferPageObject getFundTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
	
}
