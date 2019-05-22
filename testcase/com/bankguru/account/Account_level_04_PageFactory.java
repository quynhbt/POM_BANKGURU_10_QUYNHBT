package com.bankguru.account;

import org.testng.annotations.Test;

import bankguru.pageFactory.LoginPageFactory;
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

public class Account_level_04_PageFactory {
    private WebDriver driver;
	String loginPageUrl, userIdfor, passwordInfor, email;
	AbstractPage abstractPage;
	LoginPageObjects loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	LoginPageFactory loginPF;
	
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
		//Dung Page Object 
		//loginPage = new LoginPageObjects(driver);
		
		//Dung Page Factory 
		loginPF = new LoginPageFactory(driver);
		Assert.assertTrue(loginPF.isLoginFormDisplayed());
		//loginPageUrl = loginPage.getLoginPageUrl();
		loginPageUrl = loginPF.getLoginPageUrl();
		System.out.println("Login Page Url: " + loginPageUrl);
		loginPF.clickToHereLink();
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