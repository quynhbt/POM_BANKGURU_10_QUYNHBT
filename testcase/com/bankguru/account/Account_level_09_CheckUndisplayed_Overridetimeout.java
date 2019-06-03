package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.PageFactoryMananger;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class Account_level_09_CheckUndisplayed_Overridetimeout {
    private WebDriver driver;
	String loginPageUrl, userIdfor, passwordInfor, email;
	AbstractPage abstractPage;
	LoginPageObjects loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		email = "quynh" + randomNumber() + "@gmail.com";
		
		loginPage = PageFactoryMananger.getLoginPage(driver);

	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		registerPage = loginPage.clickToHereLink();
		
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		
		userIdfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();

	}
	
	@Test
	public void TC_02_LoginToSystem() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		
		loginPage.inputToUserIDTextbox(userIdfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		homePage = loginPage.clickToLoginButton();
		
		// Check wellcome message displayed
		boolean homePageStatus = homePage.isWelcomeMessageDisplayed();
		System.out.println("Home page message = " + homePageStatus);
		Assert.assertTrue(homePageStatus);
		
		boolean userDataStatus = homePage.isUserDataUndisplayed();
		System.out.println("User data  = " + userDataStatus);
		Assert.assertTrue(userDataStatus);
		
		// check login form undisplayed
		boolean loginFormStatus = homePage.isLoginFormUndisplayed();
		//true: displayed
		//false: undisplayed
		System.out.println("Login form = " + loginFormStatus);
		Assert.assertTrue(loginFormStatus);

		Assert.assertTrue(homePage.isUserIDDisplayed(userIdfor));

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