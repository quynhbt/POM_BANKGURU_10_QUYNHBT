package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryMananger;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.WithdrawalPageObject;




public class Account_level_03_PaymentFunction_BGR extends AbstractTest{
	WebDriver driver;
	LoginPageObjects loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editcustomerPage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;
	WithdrawalPageObject withdrawPage;
	FundTransferPageObject fundTransferPage;
	BalanceEnquiryPageObject blanceEnquiryPage;
	
	String loginPageUrl, homePageUrl, userIdInfo, passwordInfo, email;
	String validEmailID, validDateOfBirth, validName, validADDR, validCity, validState, validPin, validPhone, validPassword, customerID, Gender;
	String editEmail, editADDR, editCity, editState, editPin, editPhone, accountID;	
	int currentAmount, depositAmount, currentbalanceafterdeposit, withdrawalamount, Currentbalanceafterwithdrawal;
	String depositDescription, withdrawDescription;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);

		email = "quynh" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryMananger.getLoginPage(driver);
		email = "quynh" + randomNumber() + "@gmail.com";
		validName = "Bui Thi Quynh";
		Gender = "male";
		validDateOfBirth = "1995-07-24";
		validADDR = "141 Chien thang";
		validCity = "Ha Noi";
		validState = "Ha Dong";
		validPin = "123456";
		validPhone = "0987654321";
		validEmailID = "quynh" + randomNumber() + "@gmail.com";
		validPassword = "abc12345678";
	
		editADDR = "QUYNH BUI THI";
		editCity = "54 Tan Trieu";
		editState = "Thanh Tri";
		editPin = "123321";
		editPhone = "0987654321";
		editEmail = "nhuquynh" + randomNumber() + "@gmail.com";
		
		
		currentAmount = 50000;
		depositAmount = 5000;
		withdrawalamount = 15000;
		currentbalanceafterdeposit = currentAmount + depositAmount;
		Currentbalanceafterwithdrawal = currentbalanceafterdeposit - withdrawalamount;
		
		depositDescription = "Deposit";
		withdrawDescription = "Withdraw";
		
		loginPage = new LoginPageObjects(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		userIdInfo = registerPage.getUserIDInfor();
		passwordInfo = registerPage.getPasswordInfor();
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObjects(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIdInfo);
		loginPage.inputToPasswordTextbox(passwordInfo);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		homePage.isWelcomeMessageDisplayed();
		homePage.isUserIDDisplayed(userIdInfo);
		homePage.clickToNewCustomerButton();
		newCustomerPage = new NewCustomerPageObject(driver);
		newCustomerPage = homePage.clickToNewCustomerButton();
	}

	@Test
	public void TC_01_CreatNewandCheckCustomerSuccessfully() {
		newCustomerPage.inputValidDataToCustomerNameTextbox(validName);
		newCustomerPage.selectMaleGenderRadioButton();
		newCustomerPage.removeDateOfBirthAttribute();
		newCustomerPage.inputValidDataToDateOfBirthTextbox(validDateOfBirth);
		newCustomerPage.inputValidDataToAdressTextArea(validADDR);
		newCustomerPage.inputValidDataToCityTextbox(validCity);
		newCustomerPage.inputValidDataToStateTextbox(validState);
		newCustomerPage.inputValidDataToPinTextbox(validPin);
		newCustomerPage.inputValidDataToMobileNumberTextbox(validPhone);
		newCustomerPage.inputValidDataToEmailTextbox(validEmailID);
		newCustomerPage.inputValidDataToPasswordTextbox(validPassword);
		newCustomerPage.clickToSubmitButton();
		
		newCustomerPage.isCustomerRegisteredSuccessfullyDisplayed();
		customerID = newCustomerPage.getCustomerID();
		
		Assert.assertEquals(newCustomerPage.getTextCustomerNameInfo(), validName);
		Assert.assertEquals(newCustomerPage.getTextGenderInfo(), Gender);
		Assert.assertEquals(newCustomerPage.getDateOfBirthInfo(), validDateOfBirth);
		Assert.assertEquals(newCustomerPage.getTextAdressInfo(), validADDR);
		Assert.assertEquals(newCustomerPage.getTextCityInfo(), validCity);
		Assert.assertEquals(newCustomerPage.getTextStateInfo(), validState);
		Assert.assertEquals(newCustomerPage.getTextPinInfo(), validPin);
		Assert.assertEquals(newCustomerPage.getTextMobileNumberInfo(), validPhone);
		Assert.assertEquals(newCustomerPage.getTextEmailInfo(), validEmailID);
	}
	
	@Test
	public void TC_02_EditCustomerAccount() {
		editcustomerPage = newCustomerPage.clickToEditCustomerLink();
		editcustomerPage.inputCustomerIDToCustomerIDTextbox(customerID);
		editcustomerPage.clicktoSubmitCustomerIDButton();

		editcustomerPage.clearAdressTextArea();
		editcustomerPage.inputValidDataToAdressTextArea(editADDR);
		editcustomerPage.clearCityTextbox();
		editcustomerPage.inputValidDataToCityTextbox(editCity);
		editcustomerPage.clearStateTextbox();
		editcustomerPage.inputValidDataToStateTextbox(editState);
		editcustomerPage.clearPinTextbox();
		editcustomerPage.inputValidDataToPinTextbox(editPin);
		editcustomerPage.clearPhoneTextbox();
		editcustomerPage.inputValidDataToMobileNumberTextbox(editPhone);
		editcustomerPage.clearEmailTextbox();
		editcustomerPage.inputValidDataToEmailTextbox(editEmail);
		editcustomerPage.clickToEditSubmitButton();
	}
	
	@Test
	public void TC_03_AddNewAccountandCheckCreatedSuccessfully() {
		newAccountPage = editcustomerPage.clickToNewAccountButton();
		newAccountPage.inputCustomerID(customerID);
		newAccountPage.selectCurrentInAccountType();
		newAccountPage.inputAmountToInitialDeposit(String.valueOf(currentAmount));
		newAccountPage.clickToSubmitButton();
		Assert.assertTrue(newAccountPage.isAccountGeneratedSuccessfullyMessageDisplayed());
		Assert.assertEquals(newAccountPage.getTextCurrentAmount(), String.valueOf(currentAmount));
		accountID = newAccountPage.getAccountID();
	}
	
	@Test
	public void TC_04_TransferMoneyToCurrentAccount() {

		newAccountPage.clickToDepositButton();
		depositPage = new DepositPageObject(driver);
		Assert.assertTrue(depositPage.isAmountDepositFormDisplayed());
		depositPage.inputAccountIDToAccountNoTextbox(accountID);
		depositPage.inputAmountToAmountTextbox(String.valueOf(depositAmount));
		depositPage.inputDescriptionToDescriptionTextbox(depositDescription);
		depositPage.clickToDepositSubmitButton();
		Assert.assertTrue(depositPage.isCorrectTracsactionDetailsMessageDisplayed(accountID));
		Assert.assertEquals(depositPage.getTextCurrentBalance(), String.valueOf(currentbalanceafterdeposit));
	}
	
	@Test
	public void TC_05_WithdrawFromCurrentAccount() {
		depositPage.clickToWithdrawButton();
		withdrawPage = new WithdrawalPageObject(driver);
		Assert.assertTrue(withdrawPage.isAmountWithdrawFormDisplayed());
		withdrawPage.inputAccountIDToAccountNoTextbox(accountID);
		withdrawPage.inputAmountToAmountTextbox(String.valueOf(withdrawalamount));
		withdrawPage.inputDescriptionToDescriptionTextbox(withdrawDescription);
		withdrawPage.clickToWithdrawSubmitButton();
		Assert.assertTrue(withdrawPage.isCorrectTracsactionDetailsMessageDisplayed(accountID));
		Assert.assertEquals(withdrawPage.getTextCurrentBalance(),"40000");
	}
	
	@Test
	public void TC_06_TransferIntoAnotherAccountAndCheck() {
	withdrawPage.clickToFunTransferButton();
	fundTransferPage = new FundTransferPageObject(driver);
	Assert.assertFalse(fundTransferPage.isFundTransferDisplayed());

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
		fundTransferPage.clickToBalaceEnquiryButton();
		blanceEnquiryPage = new BalanceEnquiryPageObject(driver);
		Assert.assertTrue(blanceEnquiryPage.isBalanceEnquiryDisplayed());

		blanceEnquiryPage.inputAccountID(accountID);
		blanceEnquiryPage.clickSubmit();

		Assert.assertTrue(blanceEnquiryPage.isBalanceEnquirySuccessfullyPageDisplayed("//p[text()='Balance Details for Account "+accountID+"']"));
		Assert.assertEquals(blanceEnquiryPage.getBalanceEnquiry(), "40000");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		  Random random = new Random();
		  int number = random.nextInt(999999);
		  System.out.println("Random number = " + number);
		  return number;
	}
}