package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryMananger;

public class FundTransferPageObject extends AbstractPage {
	private WebDriver driver;
	
	public FundTransferPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
		
	}

	public boolean isFundTransferDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.FUND_TRANSFER_TEXT);
		return isControlDisplayed(driver, FundTransferPageUI.FUND_TRANSFER_TEXT);
	}

	public void inputPayersAccount(String AccountID) {
		waitForElementVisible(driver, FundTransferPageUI.PAYERS_ACCOUNT_NO_SENKEY);
		sendKeyToElement(driver,  FundTransferPageUI.PAYERS_ACCOUNT_NO_SENKEY, AccountID);
	}

	public void inputPayeessAccount() {
		waitForElementVisible(driver, FundTransferPageUI.PAYEES_ACCOUNT_NO_SENKEY);
		sendKeyToElement(driver,  FundTransferPageUI.PAYEES_ACCOUNT_NO_SENKEY, Constants.PAYEES_ACCOUNT_SENKEY);
	}

	public void inputAmount() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_SENKEY);
		sendKeyToElement(driver,  FundTransferPageUI.AMOUNT_SENKEY, Constants.AMOUNT_FUND_TRANSFER_SENKEY);
	}

	public void inputDescription() {
		waitForElementVisible(driver, FundTransferPageUI.DESCRIPTION_SENKEY);
		sendKeyToElement(driver,  FundTransferPageUI.DESCRIPTION_SENKEY, Constants.DESCRIPTION_FUND_TRANSFER_SENKEY);
	}
	public void clickSubmit() {
		waitForElementVisible(driver, FundTransferPageUI.SUBMIT_FUND_TRANSFER_BUTTON);
		clickToElementByJS(driver, FundTransferPageUI.SUBMIT_FUND_TRANSFER_BUTTON);
	}

	public boolean isTransactionSuccessfullyPageDisplayed() {
		waitForElementVisible(driver, FundTransferPageUI.FUND_TRANSFER_SUCCESSEFULLY_TEXT);
		return isControlDisplayed(driver, FundTransferPageUI.FUND_TRANSFER_SUCCESSEFULLY_TEXT);
	}
	public String getFromAccount() {
		waitForElementVisible(driver, FundTransferPageUI.FROM_ACCOUNT_TEXT);
		return getTextElement(driver, FundTransferPageUI.FROM_ACCOUNT_TEXT);
	}
	public String getToAccount() {
		waitForElementVisible(driver, FundTransferPageUI.TO_ACCOUNT_TEXT);
		return getTextElement(driver, FundTransferPageUI.TO_ACCOUNT_TEXT);
	}
	public String getAmountFundTranster() {
		waitForElementVisible(driver, FundTransferPageUI.AMOUNT_TEXT);
		return getTextElement(driver, FundTransferPageUI.AMOUNT_TEXT);
	}
	
	public void clickToBalaceEnquiryButton() {
		waitForElementVisible(driver, FundTransferPageUI.BLANCE_ENQUIRY_BUTTON);
		clickToElement(driver, FundTransferPageUI.BLANCE_ENQUIRY_BUTTON);
	}
}
