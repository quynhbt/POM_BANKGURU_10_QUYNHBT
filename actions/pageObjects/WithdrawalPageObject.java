package pageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver;
import bankguru.DepositPageUI;
import bankguru.HomePageUI;
import bankguru.RegisterPageUI;
import bankguru.WithdrawPageUI;
import commons.AbstractPage;
import commons.PageFactoryMananger;


public class WithdrawalPageObject extends AbstractPage {
    private WebDriver driver;
    
	public WithdrawalPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	
	public boolean isAmountWithdrawFormDisplayed() {
		waitForElementVisible(driver, WithdrawPageUI.AMOUNT_WITHDRAW_FORM_MESSAGE);
		return isControlDisplayed(driver, WithdrawPageUI.AMOUNT_WITHDRAW_FORM_MESSAGE);
		
	}
	public void inputAccountIDToAccountNoTextbox(String accountID) {
		waitForElementVisible(driver, WithdrawPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, WithdrawPageUI.ACCOUNT_NO_TEXTBOX, accountID);
		
	}
	public void inputAmountToAmountTextbox(String withdrawalamount) {
		waitForElementVisible(driver, WithdrawPageUI.AMOUNT_TEXTBOX);
		sendKeyToElement(driver, WithdrawPageUI.AMOUNT_TEXTBOX, withdrawalamount);
		
	}

	public void clickToWithdrawSubmitButton() {
		waitForElementVisible(driver, WithdrawPageUI.WITHDRAW_SUBMIT_BUTTON);
		clickToElement(driver, WithdrawPageUI.WITHDRAW_SUBMIT_BUTTON);
		

	}

	public boolean isCorrectTracsactionDetailsMessageDisplayed(String accountID) {
		String FORMAT_TRANSACTION_DETAILS_MESSAGE = String.format(WithdrawPageUI.TRANSACTION_DETAILS_MESSAGE, accountID);
		waitForElementVisible(driver, FORMAT_TRANSACTION_DETAILS_MESSAGE);
		return isControlDisplayed(driver, FORMAT_TRANSACTION_DETAILS_MESSAGE);
	}
	public String getTextCurrentBalance() {
		waitForElementVisible(driver, WithdrawPageUI.CURRENT_BALANCE_INFOR);
		return getTextElement(driver, WithdrawPageUI.CURRENT_BALANCE_INFOR);
	}
	public void openHomePageUrl(String homePageUrl) {
		openAnyUrl(driver, homePageUrl);
		
	}
	public void inputDescriptionToDescriptionTextbox(String withdrawDescription) {
		waitForElementVisible(driver, WithdrawPageUI.DESCRIPTION_TEXTBOX);
		sendKeyToElement(driver, WithdrawPageUI.DESCRIPTION_TEXTBOX, withdrawDescription);
	}
	
	public void clickToFunTransferButton() {
		waitForElementVisible(driver, WithdrawPageUI.FUND_TRANSFER);
		clickToElement(driver, HomePageUI.DEPOSIT_BUTTON);
	}

}