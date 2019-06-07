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

public class Account_level_11_ShareClassState_CloseBrowser_WebDriverManager extends AbstractTest {
   
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "quynh" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryMananger.getLoginPage(driver);

	}
	
	@Test
	public void TC_01_CheckAssertAndVerify() {
		
		log.info("Login - Step 02: Verify Login form displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		log.info("Login - Step 03: Input UserID textbox and Password textbox");
		loginPage.inputToUserIDTextbox(Account_Common_01_RegisterToSystem.USER_ID_FOR);
		loginPage.inputToPasswordTextbox(Account_Common_01_RegisterToSystem.PASSWORD_IN_FOR);
		
		log.info("Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Login - Step 05: Verify Welcome Message dislayed");
		verifyTrue(homePage.isWelcomeMessageDisplayed());
		
		log.info("Login - Step 06: Verify UserID dislayed");
		verifyTrue(homePage.isUserIDDisplayed(Account_Common_01_RegisterToSystem.USER_ID_FOR));

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