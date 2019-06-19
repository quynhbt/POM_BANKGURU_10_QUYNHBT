package com.bankguru.account;

import java.lang.reflect.Method;
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
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.RegisterPageObject;


public class Account_level_04_NewAccount_BGR extends AbstractTest {
	private WebDriver driver;
	
	String loginPageUrl, email, userIdInfor, passwordInfor;
	AbstractPage abstractPage;
	LoginPageObjects loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	
	String customerID, accounType, initialDeposit;
	
	public static String  ACCOUNT_NO;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "quynh" + randomNumber() + "@gmail.com";
		
		loginPage = PageFactoryMananger.getLoginPage(driver);	
		
		customerID = "";
		initialDeposit= "";
		
	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		log.info("LoginPage - Step 02: Get Login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();
		
		log.info("RegisterPage - Step 03: Click to 'here' link ");
		registerPage = loginPage.clickToHereLink();
		log.info("RegisterPage - Step 05: Input to Email textbox and click to login button ");
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		
		log.info("RegisterPage - Step 06: Get UserID and Password Infor ");
		userIdInfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();

	}

	@Test
	public void TC_02_LoginToSystem() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		loginPage.inputToUserIDTextbox(userIdInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isWelcomeMessageDisplayed());
		newAccountPage = homePage.clickToNewAccountButton();
	}

//	@Test
//	public void TC_03_CustomerIDCannotEmpty(Method testMethod) {
//		log.info("============== START: " + testMethod.getName() + " ============== ");
//
//		log.info("Step 01: Do not enter a value Customer ID");
//		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
//
//		log.info("Step 02: Press Tab Customer ID");
//		newAccountPage.DynamicPressTabTextArea(driver, "cusid");
//
//		log.info("Step 03: Verify text Customer ID");
//		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"),"Customer ID is required");
//				
//		log.info("============== AND: " + testMethod.getName() + " ============== ");
//	}
//	
//	@Test
//	public void TC_04_CustomerIDMustBeNumeric(Method testMethod) {
//		log.info("============== START: " + testMethod.getName() + " ============== ");
//
//		log.info("Step 01: Customer ID must be numeric");
//		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
//
//
//		log.info("Step 03: Verify text Customer ID");
//		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"),"Characters are not allowed");
//
//		log.info("============== AND: " + testMethod.getName() + " ============== ");
//	}
	
//	@Test
//	public void TC_05_CustomerIDCannotSpecialCharacter(Method testMethod) {
//		log.info("============== START: "+testMethod.getName()+" ============== ");
//
//		log.info("Step 01: Cannot Special Character value in CustomerID");
//
//		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
//
//		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"), "Special characters are not allowed");
//		log.info("============== AND: "+testMethod.getName()+" ============== ");
//	}
	
	@Test
	public void TC_06_CustomerIDCannotHaveBlankSpage(Method testMethod) {
		log.info("============== START: "+testMethod.getName()+" ============== ");

		log.info("Step 01: Customer ID Cannot Have bank spage");

		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);

		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"), "Numbers are not allowed");
		log.info("============== AND: "+testMethod.getName()+" ============== ");
	}
	
//	@Test
//	public void TC_07_CustomerIDFirstCannotSpace(Method testMethod) {
//		log.info("============== START: "+testMethod.getName()+" ============== ");
//
//		log.info("Step 01: Customer ID First Cannot space");
//
//		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "cusid", customerID);
//
//		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Customer id"), "First character can not have space");
//		log.info("============== AND: "+testMethod.getName()+" ============== ");
//	}
//	
//	@Test
//	public void TC_08_InitialDepositCannotEmpty(Method testMethod) {
//		log.info("============== START: " + testMethod.getName() + " ============== ");
//
//		log.info("Step 01: Do not enter a value InitialDeposit");
//		newAccountPage.inputToDynamicTextboxOrTextArea(driver, "inideposit", initialDeposit);
//
//		log.info("Step 02: Press Tab Initial Deposit");
//		newAccountPage.DynamicPressTabTextArea(driver, "inideposit");
//
//		log.info("Step 03: Verify text Initial Deposit");
//		verifyEquals(newAccountPage.getDynamicErrorMessage(driver, "Initial deposit"),"Initial Deposit must not be blank");
//
//		log.info("============== AND: " + testMethod.getName() + " ============== ");
//	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
	
	public int randomNumber() {
		  Random random = new Random();
		  int number = random.nextInt(999999);
		  System.out.println("Random number = " + number);
		  return number;
	}
}