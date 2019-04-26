package com.bankguru.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Account_level_01_StepByStep_TopDown {
	WebDriver driver;
	String loginPageUrl, userIdfor, passwordInfor, email;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		
		email = "selenium10" + randomNumber() + "@gmail.com";
	}
	
	@Test
	public void TC_01_RegisterToSystem() {
		/* Step 1: Check login Page displated*/
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		/* Step 2: Click to 'here' link */
		loginPageUrl = driver.getCurrentUrl();
		System.out.println("Login Page Url: " + loginPageUrl);
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		/* Step 03 - check register page displayed */
		driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed();
		/* input random email  to Email textbox */
		driver.findElement(By.xpath("//input[@name='emailid']")).click();
		
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		/* Step 05: Click to submit button  */
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		/* Get User/pass information*/
		userIdfor = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passwordInfor = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("User: "+ userIdfor +" - Password: " + passwordInfor);
	}
	
	@Test
	public void TC_02_LoginToSystem() {
		driver.get(loginPageUrl);
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdfor);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordInfor);
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee [text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ userIdfor +"']")).isDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	  
	} 
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}

}
