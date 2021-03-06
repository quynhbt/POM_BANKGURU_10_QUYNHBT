package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bankguru.DepositPageUI;
import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryMananger;

public class DepositPageObject extends AbstractPage {
	private WebDriver driver;
	
	public DepositPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	
	public boolean isAmountDepositFormDisplayed() {
		waitForElementVisible(driver, DepositPageUI.AMOUNT_DEPOSIT_FORM_MESSAGE);
		return isControlDisplayed(driver, DepositPageUI.AMOUNT_DEPOSIT_FORM_MESSAGE);
		
	}
	public void inputAccountIDToAccountNoTextbox(String accountID) {
		waitForElementVisible(driver, DepositPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.ACCOUNT_NO_TEXTBOX, accountID);
		
	}
	public void inputAmountToAmountTextbox() {
		waitForElementVisible(driver, DepositPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.AMOUNT_TEXTBOX, Constants.AMOUNT_TEXTBOX);
		
	}

	public void clickToDepositSubmitButton() {
		waitForElementVisible(driver, DepositPageUI.DEPOSIT_SUBMIT_BUTTON);
		clickToElement(driver, DepositPageUI.DEPOSIT_SUBMIT_BUTTON);
	
	}
	
	public boolean isTransactionSuccessfullyPageDisplayed(String locator) {
		waitForControlVisible(driver, locator);
		return isControlDisplayed(driver, locator);
	}

	public boolean isCorrectTracsactionDetailsMessageDisplayed(String accountID) {
		String FORMAT_TRANSACTION_DETAILS_MESSAGE = String.format(DepositPageUI.TRANSACTION_DETAILS_MESSAGE, accountID);
		waitForElementVisible(driver, FORMAT_TRANSACTION_DETAILS_MESSAGE);
		return isControlDisplayed(driver, FORMAT_TRANSACTION_DETAILS_MESSAGE);
	}
	public String getTextCurrentBalance() {
		waitForElementVisible(driver, DepositPageUI.CURRENT_BALANCE_INFOR);
		return getTextElement(driver, DepositPageUI.CURRENT_BALANCE_INFOR);
	}
	public void openHomePageUrl(String homePageUrl) {
		openAnyUrl(driver, homePageUrl);
		
	}
	public void inputDescriptionToDescriptionTextbox() {
		waitForElementVisible(driver, DepositPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, DepositPageUI.DESCRIPTION_TEXTBOX, Constants.DESCRIPTION_TEXTBOX);

	}

	public void clickToWithdrawButton() {
		waitForElementVisible(driver, DepositPageUI.WITHDRAW_LINK);
		clickToElement(driver, DepositPageUI.WITHDRAW_LINK);
	}
}