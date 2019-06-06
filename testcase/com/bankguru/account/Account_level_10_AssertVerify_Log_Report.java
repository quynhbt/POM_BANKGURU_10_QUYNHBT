package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageFactoryMananger;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class Account_level_10_AssertVerify_Log_Report extends AbstractTest {
   
	@Parameters("browser")
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		email = "quynh" + randomNumber() + "@gmail.com";
		
		loginPage = PageFactoryMananger.getLoginPage(driver);

	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		log.info("Login - Step 01: Verify Login form displayed");
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		
		log.info("LoginPage - Step 02: Get Login Page Url");
		loginPageUrl = loginPage.getLoginPageUrl();
		
		log.info("RegisterPage - Step 03: Click to 'here' link ");
		registerPage = loginPage.clickToHereLink();
		
		log.info("RegisterPage - Step 04: Verify Register Page Displayed ");
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		log.info("RegisterPage - Step 05: Input to Email textbox and click to login button ");
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		
		log.info("RegisterPage - Step 06: Get UserID and Password Infor ");
		userIdfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();

	}
	
	@Test
	public void TC_02_CheckAssertAndVerify() {
		log.info("Login - Step 01: Open Login Page again");
		loginPage = registerPage.openLoginPage(loginPageUrl);
		
		log.info("Login - Step 02: Verify Login form displayed");
		verifyFalse(loginPage.isLoginFormDisplayed());
		
		log.info("Login - Step 03: Input UserID textbox and Password textbox");
		loginPage.inputToUserIDTextbox(userIdfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		
		log.info("Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 05: Verify Welcome Message dislayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());
		
		log.info("Login - Step 06: Verify UserID dislayed");
		verifyTrue(homePage.isUserIDDisplayed(userIdfor));


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
	
	 private WebDriver driver;
		String loginPageUrl, userIdfor, passwordInfor, email;
		AbstractPage abstractPage;
		LoginPageObjects loginPage;
		RegisterPageObject registerPage;
		HomePageObject homePage;

}