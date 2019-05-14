package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;
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
import org.testng.annotations.AfterClass;

public class Account_level_03_PageObject {
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
	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		//khoi tao loginPage de map driver giua 2 tang
		loginPage = new LoginPageObjects(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		System.out.println("Login Page Url: " + loginPageUrl);
		loginPage.clickToHereLink();
		
		registerPage = new RegisterPageObject(driver);
		
		//Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name='emailid']"));
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		
		//abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", email);
		registerPage.inputToEmailIDTextbox(email);
		
		//abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		registerPage.clickToLoginButton();
		
		//userIdfor = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		//passwordInfor = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
		
		userIdfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();

	}
	
	@Test
	public void TC_02_LoginToSystem() {
		//abstractPage.openAnyUrl(driver, loginPageUrl);
		registerPage.openAnyUrl(loginPageUrl);
		
		loginPage = new LoginPageObjects(driver);
		
		//Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name='frmLogin']"));
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		
		//abstractPage.sendKeyToElement(driver, "//input[@name='uid']", userIdfor);
		loginPage.inputToUserIDTextbox(userIdfor);
		
		//abstractPage.sendKeyToElement(driver, "//input[@name='password']", passwordInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
	
		//Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee [text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		
		//Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : "+ userIdfor +"']"));
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