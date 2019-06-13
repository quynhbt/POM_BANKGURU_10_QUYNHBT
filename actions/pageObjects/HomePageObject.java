package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.PageFactoryMananger;

public class HomePageObject extends AbstractPage {
    private WebDriver driver;
	
	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	}
	
	public boolean isUserDataUndisplayed() {
		waitForElementInvisible(driver, HomePageUI.USER_DATA);
		return isControlUndisplayed(driver, HomePageUI.USER_DATA);		
	}
	public boolean isLoginFormUndisplayed() {
		waitForElementInvisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlUndisplayed(driver, LoginPageUI.LOGIN_FORM);
	}

	public boolean isUserIDDisplayed(String userIdfor) {
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userIdfor);
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

	public NewCustomerPageObject clickToNewCustomerButton() {
		waitForElementVisible(driver, HomePageUI.NEW_CUSTOMER_BUTTON);
		clickToElement(driver, HomePageUI.NEW_CUSTOMER_BUTTON);
		return PageFactoryMananger.getNewCustomerPage(driver);
	
	}
	
	public String getHomePageUrl() {
		return getCurrentPageUrl(driver);
	}

	public void clickToEditCustomerButton() {
		waitForElementVisible(driver, HomePageUI.EDIT_CUSTOMER_BUTTON);
		clickToElement(driver, HomePageUI.EDIT_CUSTOMER_BUTTON);
		
	}

}