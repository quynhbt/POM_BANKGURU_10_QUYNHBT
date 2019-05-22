package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObject;

public class PageFactoryMananger {
	private static LoginPageObjects loginPage;
	private static RegisterPageObject registerPage;
	private static HomePageObject homePage;
	
	public static LoginPageObjects getLoginPage(WebDriver driver) {
//		if(loginPage != null) {
//			return loginPage;
//		} else {
//			return new LoginPageObjects(driver);
//		}
		
		if(loginPage == null) {
			loginPage = new LoginPageObjects(driver);
		}
		return loginPage;
		
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		if(registerPage == null) {
			registerPage = new RegisterPageObject(driver);
		}
		return registerPage;
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		if(homePage == null) {
			homePage = new HomePageObject(driver);
		}
		return homePage;
	}
	
	
}
