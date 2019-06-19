package pageObjects;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.AbstractPageUI;
import bankguru.NewAccountPageUI;
import bankguru.NewCustomerPageUI;
import commons.AbstractPage;
import commons.Constants;
import commons.PageFactoryMananger;

public class NewAccountPageObject extends AbstractPage {
	private WebDriver driver;
	
	public NewAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean isNewAccountPageDisplayed() {
		waitForControlVisible(driver,NewAccountPageUI.ACCOUNT_GENERATED_SUCCESS_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.ACCOUNT_GENERATED_SUCCESS_MESSAGE);
	}
	public void inputCustomerID(String CustomerID) {
		waitForControlVisible(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX, CustomerID);
	}
	public void inputAccountType() {
		waitForControlVisible(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInDropdown(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, Constants.ACCOUNT_TYPE_SENKEY);
		getSelectedItemInDropdown(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);

	}
	public void inpuInitialDeposit() {
		waitForControlVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX, Constants.INITIAL_DEPOSIT_SENKEY);
	}
	public  void clickNewAccountSubmit() {
		waitForControlVisible(driver, NewAccountPageUI.SUBMIT_BUTTON);
		clickToElementByJS(driver, NewAccountPageUI.SUBMIT_BUTTON);
	}
	public boolean isNewAccountSuccessefullyPageDisplayed() {
		waitForControlVisible(driver,NewAccountPageUI.ACCOUNT_GENERATED_SUCCESS_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.ACCOUNT_GENERATED_SUCCESS_MESSAGE);
	}
	public boolean isCurrentAmountPageDisplayed() {
		waitForControlVisible(driver,NewAccountPageUI.CURRENT_AMOUNT_INFOR);
		return isControlDisplayed(driver, NewAccountPageUI.CURRENT_AMOUNT_INFOR);
	}
	public  String getCurrentAmount() {
		waitForControlVisible(driver, NewAccountPageUI.CURRENT_AMOUNT_INFOR);
		return getTextElement(driver, NewAccountPageUI.CURRENT_AMOUNT_INFOR);
	}
	public String getAccountIDText() {
		waitForControlVisible(driver, NewAccountPageUI.ACCOUNT_ID_INFOR);
		return getTextElement(driver, NewAccountPageUI.ACCOUNT_ID_INFOR);
	}
	


}