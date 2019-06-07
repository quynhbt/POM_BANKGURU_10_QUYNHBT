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

public class Account_Common_01_RegisterToSystem extends AbstractTest {
   
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "quynh" + randomNumber() + "@gmail.com";
		
		loginPage = PageFactoryMananger.getLoginPage(driver);
	}
	@Test
	public void TC_01_CheckAssertAndVerify() {
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
		USER_ID_FOR = registerPage.getUserIDInfor();
		PASSWORD_IN_FOR = registerPage.getPasswordInfor();

	}
	
	
	@AfterClass(alwaysRun=true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	} 
	
	public int randomNumber() {
		  Random random = new Random();
		  int number = random.nextInt(999999);
		  System.out.println("Random number = " + number);
		  return number;
	}
	
	 private WebDriver driver;
	 	public static String USER_ID_FOR, PASSWORD_IN_FOR;
		String loginPageUrl, email;
		AbstractPage abstractPage;
		LoginPageObjects loginPage;
		RegisterPageObject registerPage;
		HomePageObject homePage;

}