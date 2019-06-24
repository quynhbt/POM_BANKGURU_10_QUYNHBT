package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.DeleteAccountPageUI;
import bankguru.DeleteCustomerPageUI;
import commons.AbstractPage;


public class DeleteCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}

	public boolean isDeleteCustomerDisplayed() {
		waitForControlVisible(driver, DeleteCustomerPageUI.DELETE_CUSTOMER_TEXT);
		return isControlDisplayed(driver, DeleteCustomerPageUI.DELETE_CUSTOMER_TEXT);
	}
	
	public void inputCustomerID(String CustomerID) {
		waitForControlVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_SENKEY);
		sendKeyToElement(driver,  DeleteCustomerPageUI.CUSTOMER_ID_SENKEY, CustomerID);
	}


	public void inputAccountID(String AccountID) {
		waitForControlVisible(driver, DeleteAccountPageUI.ACCOUNT_NO_SENKEY);
		sendKeyToElement(driver,  DeleteAccountPageUI.ACCOUNT_NO_SENKEY, AccountID);
	}

	public void clickSubmit() {
		waitForControlVisible(driver,  DeleteAccountPageUI.SUBMIT_DELETE_ACCOUNT_BUTTON);
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