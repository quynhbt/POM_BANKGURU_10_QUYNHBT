package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.Constants;
import commons.PageFactoryMananger;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.WithdrawalPageObject;

public class PaymentFunction extends AbstractTest {
	private WebDriver driver;
	private AbstractPage abstractPage;

	private String email, loginURL,customerID, accountID, customertextID;
	private LoginPageObjects loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private NewAccountPageObject newAccountPage;
	private DepositPageObject depositPage;
	private FundTransferPageObject fundTransferPage;
	private WithdrawalPageObject withdrawPage;
	private BalanceEnquiryPageObject balanceEnquiryPage;
	private DeleteAccountPageObject deleteAccountPage;
	private DeleteCustomerPageObject deleteCustomerPage;

	@Test
	public void TC_01_NewCustomer() {
		
		newCustomerPage=(NewCustomerPageObject) homePage.openMultiplePage(driver, "New Customer");
		Assert.assertTrue(newCustomerPage.isNewCustomerPageDisplayed());
		newCustomerPage.inputToDynamicTextboxOrTextArea(driver, "name", customerID);
		newCustomerPage.inputDateOfBirthText();
		newCustomerPage.inputAdressTextArea();
		newCustomerPage.inputCityText();
		newCustomerPage.inputStateText();
		newCustomerPage.inputPinText();
		newCustomerPage.inputMobileText();
		newCustomerPage.inputEmailText(email);
		newCustomerPage.inputPasswordText();
		newCustomerPage.clicksubmitButton();
		Assert.assertTrue(newCustomerPage.isNewCustomerSuccessfullyPageDisplayed());
		customertextID=newCustomerPage.getCustomerIDText();
	}
	@Test
	public void TC_02_EditCustomerAndCheckSuccessfully() {
		editCustomerPage=(EditCustomerPageObject) newCustomerPage.openMultiplePage(driver, "Edit Customer");
		Assert.assertTrue(editCustomerPage.isEditCustomerPageDisplayed());
		
		editCustomerPage.inputCustomerIDText(customertextID);
		editCustomerPage.clickCustomerIDSubmit();
		
		editCustomerPage.inputEditAdressTextArea();
		editCustomerPage.inputEditCityText();
		editCustomerPage.inputEditStateText();
		editCustomerPage.inputEditMobileText();
		editCustomerPage.inputEditEmailText(email);
		editCustomerPage.clickCustomerIDSubmit();
		Assert.assertTrue(editCustomerPage.isEditCustomerSuccessfullyPageDisplayed());
	}
	@Test
	public void TC_03_NewAccountAndCheckSuccessfullyThenCurrentAmountEqualInitialDeposit() {
		newAccountPage=(NewAccountPageObject) editCustomerPage.openMultiplePage(driver, "New Account");
		Assert.assertTrue(newAccountPage.isNewAccountPageDisplayed());
		
		newAccountPage.inputCustomerID(customertextID);
		newAccountPage.inputAccountType();
		newAccountPage.inpuInitialDeposit();
		newAccountPage.clickNewAccountSubmit();
		
		newAccountPage.isNewAccountSuccessefullyPageDisplayed();
		newAccountPage.isCurrentAmountPageDisplayed();
		Assert.assertEquals(newAccountPage.getCurrentAmount(), Constants.INITIAL_DEPOSIT_SENKEY);
		accountID=newAccountPage.getAccountIDText();

	}
	
	@Test
	public void TC_04_TransferAndCheck_Deposit() {
		depositPage=(DepositPageObject) newAccountPage.openMultiplePage(driver, "Deposit");
		Assert.assertTrue(depositPage.isAmountDepositFormDisplayed());

		depositPage.inputAccountIDToAccountNoTextbox(accountID);
		depositPage.inputAmountToAmountTextbox();
		depositPage.inputDescriptionToDescriptionTextbox();
		depositPage.clickToDepositSubmitButton();

		Assert.assertTrue(depositPage.isTransactionSuccessfullyPageDisplayed("//p[text()='Transaction details of Deposit for Account "+accountID+"']"));
		Assert.assertEquals(depositPage.getTextCurrentBalance(), "173456");
		//depositPage.getTransactionID();
		//depositPage.getCurrentBalance();
	}
	
	@Test
	public void TC_05_WithdrawalAndCheck() {
		withdrawPage=(WithdrawalPageObject) depositPage.openMultiplePage(driver, "Withdrawal");
		Assert.assertTrue(withdrawPage.isAmountWithdrawFormDisplayed());

		withdrawPage.inputAccountIDToAccountNoTextbox(accountID);
		withdrawPage.inputAmountToAmountTextbox();
		withdrawPage.inputDescriptionToDescriptionTextbox();
		withdrawPage.clickToWithdrawSubmitButton();

		Assert.assertTrue(withdrawPage.isTransactionSuccessfullyPageDisplayed("//p[text()='Transaction details of Withdrawal for Account "+accountID+"']"));
		Assert.assertEquals(depositPage.getTextCurrentBalance(), "62234");
	}
	
	@Test
	public void TC_06_TransferIntoAnotherAccountAndCheck() {
		fundTransferPage=(FundTransferPageObject) withdrawPage.openMultiplePage(driver, "Fund Transfer");
		Assert.assertTrue(fundTransferPage.isFundTransferDisplayed());
 
		fundTransferPage.inputPayersAccount(accountID);
		fundTransferPage.inputPayeessAccount();
		fundTransferPage.inputAmount();
		fundTransferPage.inputDescription();
		fundTransferPage.clickSubmit();

		Assert.assertTrue(fundTransferPage.isTransactionSuccessfullyPageDisplayed());
		Assert.assertEquals(fundTransferPage.getAmountFundTranster(), "10000");
	}
	
	@Test
	public void TC_07_CheckBalance() {
		balanceEnquiryPage=(BalanceEnquiryPageObject) fundTransferPage.openMultiplePage(driver, "Balance Enquiry");
		Assert.assertTrue(balanceEnquiryPage.isBalanceEnquiryDisplayed());

		balanceEnquiryPage.inputAccountID(accountID);
		balanceEnquiryPage.clickSubmit();

		Assert.assertTrue(balanceEnquiryPage.isBalanceEnquirySuccessfullyPageDisplayed("//p[text()='Balance Details for Account "+accountID+"']"));
		Assert.assertEquals(balanceEnquiryPage.getBalanceEnquiry(), "62234");
	}
	
	@Test
	public void TC_08_DeleteAllAccountAndCheck() throws Exception {
		deleteAccountPage=(DeleteAccountPageObject) balanceEnquiryPage.openMultiplePage(driver, "Delete Account");
		Assert.assertTrue(deleteAccountPage.isDeleteAccountDisplayed());

		deleteAccountPage.inputAccountID(accountID);
		deleteAccountPage.clickSubmit();
		Thread.sleep(3000);
		deleteAccountPage.AcceptDeleteAccountPageDisplayed();
		Assert.assertEquals(deleteAccountPage.getDeleteAccountSuccessfullyPageDisplayed(), "Account Deleted Sucessfully");
		Thread.sleep(3000);
		deleteAccountPage.AcceptDeleteAccountSuccessfullyPageDisplayed();

		homePage=(HomePageObject) deleteAccountPage.openMultiplePage(driver, "Manager");
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}
	
	@Test
	public void TC_09_DeleteExistCustomerAccountAndCheck() throws Exception {
		deleteCustomerPage=(DeleteCustomerPageObject) homePage.openMultiplePage(driver, "Delete Customer");
		Assert.assertTrue(deleteCustomerPage.isDeleteCustomerDisplayed());

		deleteCustomerPage.inputCustomerID(accountID);
		deleteCustomerPage.clickSubmit();
		Thread.sleep(3000);
		deleteCustomerPage.AcceptDeleteAccountPageDisplayed();
		Assert.assertEquals(deleteCustomerPage.getDeleteAccountSuccessfullyPageDisplayed(), "Customer does not exist!!");
		Thread.sleep(3000);
		deleteCustomerPage.AcceptDeleteAccountSuccessfullyPageDisplayed();

		homePage=(HomePageObject) deleteCustomerPage.openMultiplePage(driver, "Manager");
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver=openMultiBrowser(browserName);
		
		email = "quynh231" + randomNumber() + "@gmail.com";
		loginPage =PageFactoryMananger.getLoginPage(driver);
		
		loginURL =loginPage.getLoginPageUrl();
	
		loginPage=loginPage.openLoginPage(loginURL);
		loginPage.inputToUserIDTextbox(Constants.UserID);
		loginPage.inputToPasswordTextbox(Constants.Pass);
		homePage=loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		
		customerID="Bui Thi Quynh";
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}

}