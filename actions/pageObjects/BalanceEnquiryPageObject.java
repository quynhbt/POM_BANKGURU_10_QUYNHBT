package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.BalanceEnquiryPageUI;
import commons.AbstractPage;


public class BalanceEnquiryPageObject extends AbstractPage {
	WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}

	public boolean isBalanceEnquiryDisplayed() {
		waitForElementVisible(driver, BalanceEnquiryPageUI.BALANCE_ENQUIRY_TEXT);
		return isControlDisplayed(driver, BalanceEnquiryPageUI.BALANCE_ENQUIRY_TEXT);
	}

	public void inputAccountID(String AccountID) {
		waitForElementVisible(driver, BalanceEnquiryPageUI.ACCOUNT_NO_SENKEY);
		sendKeyToElement(driver,  BalanceEnquiryPageUI.ACCOUNT_NO_SENKEY, AccountID);
	}

	public void clickSubmit() {
		waitForElementVisible(driver,  BalanceEnquiryPageUI.SUBMIT_BALANCE_BUTTON);
		clickToElementByJS(driver, BalanceEnquiryPageUI.SUBMIT_BALANCE_BUTTON);
	}

	public boolean isBalanceEnquirySuccessfullyPageDisplayed(String locator) {
		waitForElementVisible(driver, locator);
		return isControlDisplayed(driver, locator);
	}
	public String getBalanceEnquiry() {
		waitForElementVisible(driver, BalanceEnquiryPageUI.BALANCE_TEXT);
		return getTextElement(driver, BalanceEnquiryPageUI.BALANCE_TEXT);
	}
}