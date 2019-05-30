package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageFactoryMananger;
import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class Account_level_08_DynamicLocator_RestParameter extends AbstractTest {
	private WebDriver driver;
	String loginPageUrl, userIdfor, passwordInfor, email;
	AbstractPage abstractPage;
	LoginPageObjects loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;
	FundTransferPageObject fundTransferPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		System.out.println("Driver at test class = " +driver.toString());
		email = "quynh" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryMananger.getLoginPage(driver);
	}

	@Test
	public void TC_01_RegisterToSystem() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		System.out.println("Login Page Url: " + loginPageUrl);
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
		// homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		Assert.assertTrue(homePage.isUserIDDisplayed(userIdfor));

	}

	@Test
	public void TC_03_OpenMultiPage() {
		newAccountPage = (NewAccountPageObject) homePage.openMultiplePage(driver, "New Account");
		
		depositPage = (DepositPageObject) newAccountPage.openMultiplePage(driver, "Deposit");
		
		fundTransferPage = (FundTransferPageObject) depositPage.openMultiplePage(driver, "Fund Transfer");

		homePage = (HomePageObject) fundTransferPage.openMultiplePage(driver, "Manager");
		
		fundTransferPage = (FundTransferPageObject) homePage.openMultiplePage(driver, "Fund Transfer");
		
		newAccountPage = (NewAccountPageObject) fundTransferPage.openMultiplePage(driver, "New Account");
		
		homePage = (HomePageObject) newAccountPage.openMultiplePage(driver, "Manager");

	}
	
	@Test
	public void TC_04_OpenMultiPages() {
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryMananger.getNewAccountPage(driver);
		
		newAccountPage.openMultiplePages(driver, "Deposit");
		depositPage = PageFactoryMananger.getDeposiPage(driver);
		
		depositPage.openMultiplePages(driver, "Fund Transfer");
		fundTransferPage = PageFactoryMananger.getFundTransferPage(driver);

		fundTransferPage.openMultiplePages(driver, "Manager");
		homePage = PageFactoryMananger.getHomePage(driver);
		
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