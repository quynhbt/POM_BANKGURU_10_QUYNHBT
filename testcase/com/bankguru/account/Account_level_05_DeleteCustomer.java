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

public class Account_level_05_DeleteCustomer extends AbstractTest {
	private WebDriver driver;
	private AbstractPage abstractPage;

	private String email, loginURL, customerID, accountID, customertextID;
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
		
		customerID= "";
	}
	
	
	@Test
	public void TC_01_CustomerIDCannotBeEmpty() {
		deleteCustomerPage=(DeleteCustomerPageObject) homePage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
		deleteCustomerPage.DynamicPressTabTextArea(driver, "cusid");
		verifyEquals(deleteCustomerPage.getDynamicErrorMessage(driver, "Customer ID"),"Customer ID is required");
	}
	
	@Test
	public void TC_02_CustomerIDMustBeNumeric() {
		deleteCustomerPage=(DeleteCustomerPageObject) homePage.openMultiplePage(driver, "Delete Customer");
		deleteCustomerPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
		deleteCustomerPage.DynamicPressTabTextArea(driver, "cusid");
		verifyEquals(deleteCustomerPage.getDynamicErrorMessage(driver, "Customer ID"),"Characters are not allowed");
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