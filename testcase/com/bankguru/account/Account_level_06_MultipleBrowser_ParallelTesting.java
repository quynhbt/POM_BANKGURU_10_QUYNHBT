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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class Account_level_06_MultipleBrowser_ParallelTesting extends AbstractTest {
	private WebDriver driver;
	String loginPageUrl, userIdfor, passwordInfor, email;
	AbstractPage abstractPage;
	LoginPageObjects loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

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
		// loginPage = new LoginPageObjects(driver);

		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIdfor);
		loginPage.inputToPasswordTextbox(passwordInfor);

		homePage = loginPage.clickToLoginButton();
		// homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		Assert.assertTrue(homePage.isUserIDDisplayed(userIdfor));

		// them 1 step la tu home page logout ra - login page
		// khoi tao login page 1 lan nua, sau khi click thi no quay ve trang loginPage
		loginPage = homePage.clickToLogoutLink();
		Assert.assertTrue(loginPage.isLoginFormDisplayed());

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