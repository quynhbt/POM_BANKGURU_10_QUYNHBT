package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.DeleteAccountPageUI;
import commons.AbstractPage;



public class DeleteAccountPageObject extends AbstractPage {
	WebDriver driver;

	public DeleteAccountPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}

	public boolean isDeleteAccountDisplayed() {
		waitForElementVisible(driver, DeleteAccountPageUI.DELETE_ACCOUNT_TEXT);
		return isControlDisplayed(driver, DeleteAccountPageUI.DELETE_ACCOUNT_TEXT);
	}

	public void inputAccountID(String AccountID) {
		waitForElementVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_SENKEY);
		sendKeyToElement(driver,  DeleteAccountPageUI.ACCOUNT_NO_SENKEY, AccountID);
	}

	public void clickSubmit() {
		waitForElementVisible(driver,  DeleteAccountPageUI.SUBMIT_DELETE_ACCOUNT_BUTTON);
		clickToElementByJS(driver, DeleteAccountPageUI.SUBMIT_DELETE_ACCOUNT_BUTTON);
	}
	public void AcceptDeleteAccountPageDisplayed() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}
	public String getDeleteAccountSuccessfullyPageDisplayed() {
		waitForAlertPresence(driver);
		return getTextAlert(driver);
	}
	public void AcceptDeleteAccountSuccessfullyPageDisplayed() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
	}



}