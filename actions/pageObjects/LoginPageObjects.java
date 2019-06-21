package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.AbstractPageUI;
import bankguru.LoginPageUI;
import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryMananger;

public class LoginPageObjects extends AbstractPage {
    private WebDriver driver;
	
	public LoginPageObjects(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean isLoginFormDisplayed() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, LoginPageUI.LOGIN_FORM);
	}

	public String getLoginPageUrl() {
		return getCurrentPageUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementVisible(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageFactoryMananger.getRegisterPage(driver);
		
	}

	public void inputToUserIDTextbox(String userIdfor) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userIdfor);
	}

	public void inputToPasswordTextbox(String passwordInfor) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordInfor);

	}

	public HomePageObject clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.Login_BUTTON);
		clickToElement(driver, LoginPageUI.Login_BUTTON);
		return PageFactoryMananger.getHomePage(driver);
	}
	
	public void senkeyDynamicPage(WebDriver driver, String inputName) {
		waitForControlVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, inputName );
		sendKeyToElement(driver, Constants.CUSTOMER_NAME_SENKEY,AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, inputName);
	}
	
	public LoginPageObjects openLoginPage(String loginPageUrl) {
		openAnyUrl(driver, loginPageUrl);
		//return new LoginPageObject(driver);
		return PageFactoryMananger.getLoginPage(driver);
	}
}