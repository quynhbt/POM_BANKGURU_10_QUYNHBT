package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.RegisterPageUI;
import commons.AbstractPage;
import commons.PageFactoryMananger;

public class RegisterPageObject extends AbstractPage {
    private WebDriver driver;
	
	public RegisterPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
		System.out.println("Driver tai Register Page = " + driver.toString());
	}
	public boolean isRegisterPageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		return isControlDisplayed(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
	}

	public void inputToEmailIDTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
		
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDInfor() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordInfor() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public LoginPageObjects openLoginPage(String loginPageUrl) {
		openAnyUrl(driver, loginPageUrl);		
		return PageFactoryMananger.getLoginPage(driver);
	}

}