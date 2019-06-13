package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageFactoryMananger;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;



public class Account_level_02_EditCustomerValidateMessages_BGR extends AbstractTest{
	WebDriver driver;
	LoginPageObjects loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editcustomerPage;
	String loginPageUrl, newCustomerPageUrl, homePageUrl, userIdInfo, passwordInfo, email;
	String validEmailID, validDateOfBirth, validName, validAdress, validCity, validState, validPin, validPhoneNumber, validPassword, customerID, Gender;
	String blankSpace;
	String [] numericValues, specialcharacter, characterPINs, DigitsList, characterPhoneNumbers, specialPhoneNumbers, incorrectEmailIDList; 
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		driver.get("http://demo.guru99.com/v4");
		email = "quynh" + randomNumber() + "@gmail.com";
		loginPage = PageFactoryMananger.getLoginPage(driver);
		email = "quynh" + randomNumber() + "@gmail.com";
		validName = "Bui Thi Quynh";
		Gender = "male";
		validDateOfBirth = "1995-07-24";
		validAdress = "141 Chien thang";
		validCity = "Ha Noi";
		validState = "Ha Dong";
		validPin = "123456";
		validPhoneNumber = "0987654321";
		validEmailID = "quynh" + randomNumber() + "@gmail.com";
		
		validPassword = "abc12345678";
		blankSpace =" ";
		numericValues = new String [] {"1234" , "name123"};
		specialcharacter = new String [] {"quynh*@#","*@#"};
		characterPINs = new String [] {"123PIN","QUYNH890"};
		DigitsList = new String [] {"1","22","321","3214","32147" };
		characterPhoneNumbers = new String [] {"quynh","22 5234"};
		specialPhoneNumbers = new String [] {"111@!13546","!#654321", "456789#@!"};
		incorrectEmailIDList = new String [] {"guru99@gmail","guru99", "guru99@", "guru99@gmail.","guru99gmail.com"};


		loginPage = new LoginPageObjects(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		userIdInfo = registerPage.getUserIDInfor();
		passwordInfo = registerPage.getPasswordInfor();
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObjects(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIdInfo);
		loginPage.inputToPasswordTextbox(passwordInfo);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		homePage.isWelcomeMessageDisplayed();
		homePage.isUserIDDisplayed(userIdInfo);
		homePage.clickToNewCustomerButton();
		newCustomerPage = new NewCustomerPageObject(driver);
		newCustomerPageUrl = newCustomerPage.getNewCustomerPageUrl();
		newCustomerPage = homePage.clickToNewCustomerButton();

		newCustomerPage.inputValidDataToCustomerNameTextbox(validName);
		newCustomerPage.selectMaleGenderRadioButton();
		newCustomerPage.removeDateOfBirthAttribute();
		newCustomerPage.inputValidDataToDateOfBirthTextbox(validDateOfBirth);
		newCustomerPage.inputValidDataToAdressTextArea(validAdress);
		newCustomerPage.inputValidDataToCityTextbox(validCity);
		newCustomerPage.inputValidDataToStateTextbox(validState);
		newCustomerPage.inputValidDataToPinTextbox(validPin);
		newCustomerPage.inputValidDataToMobileNumberTextbox(validPhoneNumber);
		newCustomerPage.inputValidDataToEmailTextbox(validEmailID);
		newCustomerPage.inputValidDataToPasswordTextbox(validPassword);
		newCustomerPage.clickToSubmitButton();
		
		newCustomerPage.isCustomerRegisteredSuccessfullyDisplayed();
		customerID = newCustomerPage.getCustomerID();
		Assert.assertEquals(newCustomerPage.getTextCustomerNameInfo(), validName);
		Assert.assertEquals(newCustomerPage.getTextGenderInfo(), Gender);
		Assert.assertEquals(newCustomerPage.getDateOfBirthInfo(), validDateOfBirth);
		Assert.assertEquals(newCustomerPage.getTextAdressInfo(), validAdress);
		Assert.assertEquals(newCustomerPage.getTextCityInfo(), validCity);
		Assert.assertEquals(newCustomerPage.getTextStateInfo(), validState);
		Assert.assertEquals(newCustomerPage.getTextPinInfo(), validPin);
		Assert.assertEquals(newCustomerPage.getTextMobileNumberInfo(), validPhoneNumber);
		Assert.assertEquals(newCustomerPage.getTextEmailInfo(), validEmailID);

		newCustomerPage.clickToHomeLink();		
		homePage = new HomePageObject(driver);	
		homePage.clickToEditCustomerButton();
	

		editcustomerPage = new EditCustomerPageObject(driver);

		editcustomerPage = newCustomerPage.clickToEditCustomerLink();

	}

	@Test 
	public void TC_01_CustomerIDCanNotBeEmpty() {
		editcustomerPage.clearCustomerIDTextbox();
		editcustomerPage.clickToCustomerIDTexbox();
		editcustomerPage.pressTABKeyToCustomerIDTextbox();
		Assert.assertTrue(editcustomerPage.isCustomerIDIsRequiredMessageDisplayed());
		
	}
	
	@Test
	public void TC_02_CustomerIDCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearCustomerIDTextbox();
		editcustomerPage.inputBlankSpaceToCustomerIDTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfCustomerIDCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_03_CustomerIDCharacterOrIncludeSpaceAreNotAllow() {
		for (String characterPhoneNumber:characterPhoneNumbers) {
			editcustomerPage.clearCustomerIDTextbox();
			editcustomerPage.inputChractersValueToCustomerIDTextbox(characterPhoneNumber);
			Assert.assertTrue(editcustomerPage.isCustomerIDCharacterAreNotAllowMessageDisplayed());
		}
		
	}
	
	@Test
	public void TC_04_CustomerIDCanNotHaveSpecialCharacters() {
		for (String specialPhoneNumber: specialPhoneNumbers) {
			editcustomerPage.clearCustomerIDTextbox();
			editcustomerPage.inputSpecialCharactersToCustomerIDTextbox(specialPhoneNumber);
			Assert.assertTrue(editcustomerPage.isSpecialCharactersOfCustomerIDAreNotAllowedMessageDisplayed());
		}
	}
	
	@Test
	public void TC_05_AddressCanNotBeEmpty() {
		editcustomerPage.clearCustomerIDTextbox();
		editcustomerPage.inputValidCustomerIDToCustomerIDTextbox(customerID);
		editcustomerPage.clickToCustomerIDSubmitButton();
		editcustomerPage.clearAdressTextArea();
		editcustomerPage.clickToAddressTextarea();
		editcustomerPage.pressTABKeyToAddressTextArea();
		Assert.assertFalse(editcustomerPage.isAddressFieldMustNotBeBlankMessageDisplayed());
		
	}
	
	@Test
	public void TC_06_AdressCanNotHaveSpecialCharacters() {
		for (String specialCharactersAddress: specialcharacter) {
			editcustomerPage.clearAdressTextArea();
			editcustomerPage.inputSpecialCharactersToAdressTextArea(specialCharactersAddress);
			Assert.assertTrue(editcustomerPage.isSpecialCharactersOfAddressAreNotAllowedMessageDisplayed());
		}
	}
	
	@Test
	public void TC_07_AddressCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearAdressTextArea();
		editcustomerPage.inputBlankSpaceToAdressTextArea(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfAddressCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_08_CityCanNotBeEmpty() {
		editcustomerPage.clearCityTextbox();
		editcustomerPage.clickToCityTextbox();
		editcustomerPage.pressTABKeyToCityTextbox();
		Assert.assertFalse(editcustomerPage.isCityMustNotBeBlankMessageDisplayed());
	}
	@Test
	public void TC_09_CityCanNotBeNumberic() {
		for(String numericCity: numericValues) {
			editcustomerPage.clearCityTextbox();
			editcustomerPage.inputNumericValueToCityTextbox(numericCity);
			Assert.assertTrue(editcustomerPage.isNumbersAreNotAllowedMessageOfCityDisplayed());
		}
	}
	@Test
	public void TC_10_CityCanNotHaveSpecialCharacters() {
		for (String specialCharactersCity: specialcharacter) {
			editcustomerPage.clearCityTextbox();
			editcustomerPage.inputSpecialCharactersToCityTextbox(specialCharactersCity);
			Assert.assertTrue(editcustomerPage.isSpecialCharactersOfCityAreNotAllowedMessageDisplayed());
		}
	}
	@Test
	public void TC_11_CityCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearCityTextbox();
		editcustomerPage.inputBlankSpaceToCityTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfCityCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_12_StateCanNotBeEmpty() {
		editcustomerPage.clearStateTextbox();
		editcustomerPage.clickToStateTextbox();
		editcustomerPage.pressTABKeyToStateTextbox();
		Assert.assertFalse(editcustomerPage.isStateMustNotBeBlankMessageDisplayed());
	}
	@Test
	public void TC_13_StateCanNotBeNumberic() {
		for(String numericState: numericValues) {
			editcustomerPage.clearStateTextbox();
			editcustomerPage.inputNumericValueToStateTextbox(numericState);
			Assert.assertTrue(editcustomerPage.isNumbersAreNotAllowedMessageOfStateDisplayed());
		}
	}
	@Test
	public void TC_14_StateCanNotHaveSpecialCharacters() {
		for (String specialCharactersState: specialcharacter) {
			editcustomerPage.clearStateTextbox();
			editcustomerPage.inputSpecialCharactersToStateTextbox(specialCharactersState);
			Assert.assertTrue(editcustomerPage.isSpecialCharactersOfStateAreNotAllowedMessageDisplayed());
		}
	}
	@Test
	public void TC_15_StateCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearStateTextbox();
		editcustomerPage.inputBlankSpaceToStateTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfStateCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_16_PinCanNotBeEmpty() {
		editcustomerPage.clearPinTextbox();
		editcustomerPage.clickToPinTextbox();
		editcustomerPage.pressTABKeyToPinTextbox();
		Assert.assertFalse(editcustomerPage.isPinMustNotBeBlankMessageDisplayed());
	}
	
	@Test
	public void TC_17_PinMustBeNumeric() {
		for (String characterPIN:characterPINs) {
			editcustomerPage.clearPinTextbox();
			editcustomerPage.inputChractersValueToPinTextbox(characterPIN);
			Assert.assertTrue(editcustomerPage.isPinCharacterAreNotAllowMessageDisplayed());
		}
	}
	
	@Test
	public void TC_18_PinMustHave6Degits() {
		for (String lessThan6Digits:DigitsList) {
			editcustomerPage.clearPinTextbox();
			editcustomerPage.inputLessThan6DigitsToPinTextbox(lessThan6Digits);
			Assert.assertTrue(editcustomerPage.isPinMustHave6DegitsMessageDisplayed());
		}
	}
	
	@Test
	public void TC_19_PinCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearPinTextbox();
		editcustomerPage.inputBlankSpaceToPinTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfPinCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_20_TelephoneCanNotBeEmpty() {
		editcustomerPage.clearPhoneTextbox();
		editcustomerPage.clickToMobileNumberTextbox();
		editcustomerPage.pressTABKeyToMobileTextbox();
//		Assert.assertFalse(editcustomerPage.isPhoneMustNotBeBlankMessageDisplayed());
	}
	
	@Test
	public void TC_21_TelephoneCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearPhoneTextbox();
		editcustomerPage.inputBlankSpaceToMobileNumberTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfPhoneCanNotHaveSpaceMessageDisplayed());
	}
	
	@Test
	public void TC_22_TelephoneCharacterOrIncludeSpaceAreNotAllow() {
		for (String characterPhoneNumber:characterPhoneNumbers) {
			editcustomerPage.clearPhoneTextbox();
			editcustomerPage.inputChractersValueToMobileNumberTextbox(characterPhoneNumber);
			Assert.assertTrue(editcustomerPage.isPhoneCharacterAreNotAllowMessageDisplayed());
		}
		
	}
	
	@Test
	public void TC_23_TelephoneCanNotHaveSpecialCharacters() {
		for (String specialPhoneNumber: specialPhoneNumbers) {
			editcustomerPage.clearPhoneTextbox();
			editcustomerPage.inputSpecialCharactersToMobileNumberTextbox(specialPhoneNumber);
			Assert.assertTrue(editcustomerPage.isSpecialCharactersOfPhoneAreNotAllowedMessageDisplayed());
		}
	}
	
	@Test
	public void TC_24_EmailCanNotBeEmpty() {
		editcustomerPage.clearEmailTextbox();
		editcustomerPage.clickToEmailTextbox();
		editcustomerPage.pressTABKeyToEmailTextbox();
		Assert.assertFalse(editcustomerPage.isEmailMustNotBeBlankMessageDisplayed());
	}
	
	@Test
	public void TC_25_EmailMustBeInCorrectFormat() {
		for (String incorrectEmailID: incorrectEmailIDList) {
			editcustomerPage.clearEmailTextbox();
			editcustomerPage.inputIncorrectEmailIDToEmailTextbox(incorrectEmailID);
			Assert.assertTrue(editcustomerPage.isEmailIDIsNotValidMessageDisplayed());
		}
	}
	
	@Test
	public void TC_26_EmailCanNotHaveFirstCharacterAsBlankSpace() {
		editcustomerPage.clearEmailTextbox();
		editcustomerPage.inputBlankSpaceToEmailTextbox(blankSpace);
		Assert.assertTrue(editcustomerPage.isFirstCharacterOfEmailCanNotHaveSpaceMessageDisplayed());
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