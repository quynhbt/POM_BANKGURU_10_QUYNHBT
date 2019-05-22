package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.PageFactoryMananger;

public class HomePageObject extends AbstractPage {
    private WebDriver driver;
	
	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
		System.out.println("Driver tai Home Page = " + driver.toString());
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	}

	public boolean isUserIDDisplayed(String userIdfor) {
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userIdfor);
		System.out.println(USER_ID_FORMAT);
		waitForElementVisible(driver, USER_ID_FORMAT);
		return isControlDisplayed(driver, USER_ID_FORMAT);
	}
	
	public LoginPageObjects clickToLogoutLink() {
		waitForElementVisible(driver, HomePageUI.LOG_OUt_LINK);
		clickToElement(driver, HomePageUI.LOG_OUt_LINK);
		waitForAlertPresence(driver);
		acceptAlert(driver);
		return PageFactoryMananger.getLoginPage(driver);
		
	}
		
}