package commons;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import bankguru.AbstractPageUI;
import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.NewAccountPageObject;

public class AbstractPage {
	WebElement element;
	JavascriptExecutor javascriptExecutor;
	List <WebElement> elements;
	WebDriverWait waitExplicit;
	Actions action;
	By byLocator;
	long shortTimeout = 5;
	long longTimeout = 30;
	
	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle(WebDriver driver ) {
		return driver.getTitle();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

//WebElement
	public void clickToElement(WebDriver driver, String locator) {
//		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String...values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendkey, String ...values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
		element = driver.findElement(By.xpath(locator));
		element.sendKeys(valueToSendkey);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String xpathParent, String allItemXpath, String expectedItem) throws Exception  {
		WebElement parentDropdown = driver.findElement(By.xpath(xpathParent));
		waitExplicit = new WebDriverWait(driver, 30);
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca cac phan tu trong dropdown:" + allItems.size());

		for (int i=0; i< allItems.size() ; i++ ) { 
		     System.out.println("Gia tri moi lan get: " + allItems.get(i).getText());
		     
		     if (allItems.get(i).getText().contains(expectedItem))
		     {
		    	 javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", allItems.get(i));
		    	 Thread.sleep(2000);
		 	 
		    	 if (allItems.get(i).isDisplayed()) {
		 	    //Selenium click
		    		 allItems.get(i).click(); 
			 		}
		    	 else {
		    		 //JS click
		    		 javascriptExecutor.executeScript("arguments[0].click();", allItems.get(i));
		 	}
		 	Thread.sleep(1500);
		 	break; 
		    } 

		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}
	
 	public String getTextElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
 	
 	public String getTextElement(WebDriver driver, String locator, String...values) {
 		locator = String.format(locator, (Object[]) values);
		element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public int countElementNumber(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if(!element.isSelected()) {
			element.click();
		}
		
	}
	
	public void unToCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isControlSelected(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public boolean isControlEnable(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	 public void switchToChildWindowByID(WebDriver driver, String parentID) {
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String runWindow : allWindows) {
	        	 System.out.println("window ID=" + runWindow);
	             if (!runWindow.equals(parentID)) {
	                 driver.switchTo().window(runWindow);
	                 break;
	             }
	        }
	    }
	    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String runWindows : allWindows) {
	             driver.switchTo().window(runWindows);
	             String currentWin = driver.getTitle();
	             if (currentWin.equals(expectedTitle)) {
	                  break;
	             }
	        }
	    }
	    
	    public boolean closeAllWithoutParentWindows(WebDriver driver, String parentID) {
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String runWindows : allWindows) {
	             if (!runWindows.equals(parentID)) {
	                 driver.switchTo().window(runWindows);
	                 driver.close();
	             }
	        }
	        driver.switchTo().window(parentID);
	        if (driver.getWindowHandles().size() == 1)
	              return true;
	        else
	              return false;
	    }
	    
	    public void switchToIframe(WebDriver driver, String locator) {
	    	element = driver.findElement(By.xpath(locator));
	    	driver.switchTo().frame(element);
	    }
	    
	    public void backToTopWindow(WebDriver driver) {
	    	driver.switchTo().defaultContent();
	    }
	    
	    public void hoverMouseToElement(WebDriver driver, String locator) {
	    	element = driver.findElement(By.xpath(locator));
	    	action = new Actions(driver);
	    	action.moveToElement(element).perform();
	    }
	    
	    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
	    	element = driver.findElement(By.xpath(locator));
	    	action = new Actions(driver);
	    	action.sendKeys(element, key);
	    }
	    
	    public void sendKeyDynamicboardToElement(WebDriver driver,  Keys key,String locator,String ...dynamicValue) {
			locator=String.format(locator, (Object[])dynamicValue );
			WebElement element = driver.findElement(By.xpath(locator));
			Actions action = new Actions(driver);
			action.sendKeys(element, key).perform();
		}
	    
	    public void highlightElement(WebDriver driver, String locator) {
	    	javascriptExecutor = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	        String originalStyle = element.getAttribute("style");
	        javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border:3px solid red; border-style: dashed;");
	        try {
	        	Thread.sleep(800);
	        } catch (InterruptedException e) {
	        	e.printStackTrace();
	       
	        }
	        javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	    }

	    public String executeForBrowser(WebDriver driver, String javaSript) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        return (String) js.executeScript(javaSript);
	    }

	    public Object clickToElementByJS(WebDriver driver, String locator) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	        return js.executeScript("arguments[0].click();", element);
	    }

	    public Object sendkeyToElementByJS(WebDriver driver, String locator, String value) {
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	       return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	    }

	    public void removeAttributeInDOM(WebDriver driver, String locator, String attribute) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	        js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);

	    }

	    public Object setAttributeInDOM(WebDriver driver, String locator, String attribute, String value) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	        return js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "'); ", element);
	    }

	    public Object navigateToUrlByJS(WebDriver driver, String url) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        return js.executeScript("window.location = '" + url + "'");
	    }
	    
	    public void waitForElementPresence(WebDriver driver, String locator) {
	    	waitExplicit = new WebDriverWait(driver, longTimeout);
	    	byLocator = By.xpath(locator);
	    	waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	    }
	    
	    public void waitForElementVisible(WebDriver driver, String locator) {
	    	waitExplicit = new WebDriverWait(driver, longTimeout);
	    	byLocator = By.xpath(locator);
	    	try {
		    	waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	    	} catch (Exception ex) {
	    		Reporter.log("=================================Wait for element not visible================");
	    		Reporter.log(ex.getMessage());
	    		System.err.println("=====================================wait for element not visible========================");
	    		System.err.println(ex.getMessage() + "\n");
	    	}
	    }
	    
	    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
	    	waitExplicit = new WebDriverWait(driver, longTimeout);
	    	locator = String.format(locator, (Object[]) values);
	    	byLocator = By.xpath(locator);
	    	waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	    }
	    
	    public void waitForElementClickable(WebDriver driver, String locator) {
	    	waitExplicit = new WebDriverWait(driver, longTimeout);
	    	byLocator = By.xpath(locator);
	    	waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
	    }
	    
	    public void waitForElementInvisible(WebDriver driver, String locator) {
	    	waitExplicit = new WebDriverWait(driver, longTimeout);
	    	byLocator = By.xpath(locator);
	    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	    }
	    
	    public void waitForAlertPresence(WebDriver driver) {
	    	waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
	    	waitExplicit.until(ExpectedConditions.alertIsPresent());
	    }
	    
	    public void waitToElementInvisible(WebDriver driver, String locator) {
	    	Date date = new Date();
	    	By byLocator = By.xpath(locator);
	    	WebDriverWait waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
	    	System.out.println("Start time for wait invisible = " +date.toString());
	    	waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	    	System.out.println("End time for wait invisible = " + new Date().toString());
	    }
	    
	    public boolean isControlUndisplayed(WebDriver driver, String locator) {
	    	Date date = new Date();
	    	System.out.println("Start time =" + date.toString());
	    	overideTimeout(driver, Constants.SHORT_TIMEOUT);
	    	List<WebElement> elements = driver.findElements(By.xpath(locator));
	    	
	    	if(elements.size() ==0) {
	    		System.out.println("Element not in DOM");
	    		System.out.println("End time =" + new Date().toString());
	    		overideTimeout(driver, Constants.SHORT_TIMEOUT);
	    		return true;
	    	} else if(elements.size() >0 &&!elements.get(0).isDisplayed()) {
	    		System.out.println("Element in DOM but not visible/displayed");
	    		System.out.println("End time =" + new Date().toString());
	    		overideTimeout(driver, Constants.SHORT_TIMEOUT);
	    		return true;
	    	} else {
	    		System.out.println("Element in DOM and visible");
	    		overideTimeout(driver, Constants.SHORT_TIMEOUT);
	    		return false;
	    	}
		}
	    
	    public void overideTimeout(WebDriver driver, long timeout) {
				driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	    }
	    
		//kiem tra elemnent co duoc hien thi hay k
		public boolean isControlDisplayed(WebDriver driver, String locator) {
			boolean status = true;
			try {
				element = driver.findElement(By.xpath(locator));
				return status;
	    	} catch (Exception ex) {
	    		Reporter.log("=================================Wait for element not visible================");
	    		Reporter.log(ex.getMessage());
	    		System.err.println("=====================================wait for element not visible========================");
	    		System.err.println(ex.getMessage() + "\n");
	    		status = false;
	    	}
			return status;
		}
		
		public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
			locator = String.format(locator, (Object[]) values);
			highlightElement(driver, locator);
			element = driver.findElement(By.xpath(locator));
			return element.isDisplayed();
		}
	    
		public String getTextInElement(WebDriver driver, String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			return element.getText();
		}
		
	    // viet ham de mo ra 14 page nay
	    public HomePageObject openHomePage(WebDriver driver) {
	    	waitForElementVisible(driver, AbstractPageUI.HOME_PAGE_LINK);
	    	clickToElement(driver, AbstractPageUI.HOME_PAGE_LINK);
	    	return PageFactoryMananger.getHomePage(driver);
	    }
	    
	    public NewAccountPageObject openNewAccountPage(WebDriver driver) {
	    	waitForElementVisible(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
	    	clickToElement(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
	    	return PageFactoryMananger.getNewAccountPage(driver);
	    }
	    
	    public DepositPageObject openDepositPage(WebDriver driver) {
	    	waitForElementVisible(driver, AbstractPageUI.DEPOSIT_LINK);
	    	clickToElement(driver, AbstractPageUI.DEPOSIT_LINK);
	    	return PageFactoryMananger.getDeposiPage(driver);
	    }
	    
	    public FundTransferPageObject openFundTransferPage(WebDriver driver) {
	    	waitForElementVisible(driver, AbstractPageUI.FUND_TRANSFER_LINK);
	    	clickToElement(driver, AbstractPageUI.FUND_TRANSFER_LINK);
	    	return PageFactoryMananger.getFundTransferPage(driver);
	    }
	    
	    public AbstractPage openMultiplePage(WebDriver driver, String pageName) {
	    	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	    	clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	    
	    	switch (pageName){ 
	    	case "Manager":
	    		return PageFactoryMananger.getHomePage(driver);
	    	case "Edit Customer":
	    		return PageFactoryMananger.getEditCustomerPage(driver);
	    	case "New Account":
	    		return PageFactoryMananger.getNewAccountPage(driver);
	    	case "New Customer":
	    		return PageFactoryMananger.getNewCustomerPage(driver);
	    	case "Withdrawal":
	    		return PageFactoryMananger.getWithdrawalPage(driver);
	    	case "Deposit":
	    		return PageFactoryMananger.getDeposiPage(driver);
	    	case "Login":
	    		return PageFactoryMananger.getLoginPage(driver);
	    	case "Fund Transfer":
	    		return PageFactoryMananger.getFundTransferPage(driver);
	    	default:
	    		return PageFactoryMananger.getHomePage(driver);
	    	}
	   // cach viet dung if else
//	    	if(pageName.equalsIgnoreCase("Manager")) {
//	    		return PageFactoryMananger.getHomePage(driver);
//	    	} else if(pageName.equalsIgnoreCase("New Account")) {
//	    		return PageFactoryMananger.getNewAccountPage(driver);
//	    	} else if(pageName.equalsIgnoreCase("Deposit")) {
//	    		return PageFactoryMananger.getDeposiPage(driver);
//	    	}
	    }
	    
	    public void openMultiplePages(WebDriver driver, String pageName) {
	    	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	    	clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	    }
	    
	    public void clearTextElement(WebDriver driver, String locator) {
			element = driver.findElement(By.xpath(locator));
			element.clear();
		}
	    
	    public void waitForControlVisible(WebDriver driver, String locator) {
			WebDriverWait wait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}
		}

		public void waitForControlVisible(WebDriver driver, String locator, String... dynamicValue) {
			locator = String.format(locator, (Object[]) dynamicValue);
			WebDriverWait wait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		}
		
		// Dynamic
		public void clickToDynamicButtonTextboxTextArea(WebDriver driver, String fieldName) {
	    	waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, fieldName);
	    	clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, fieldName);
		}
		
		public void inputToDynamicTextboxOrTextArea(WebDriver driver,String fieldName, String value) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, fieldName);
			sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, value, fieldName);
		}
		
		public String getDynamicErrorMessage(WebDriver driver,String fieldName ) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
			return getTextElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
		}
		
		public boolean isDynamicPageTitleOrPageMessageDisplayed(WebDriver driver, String pageTitle) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_OR_MESSAGE_DISPLAYED, pageTitle);
			return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_OR_MESSAGE_DISPLAYED, pageTitle);
		}
		
		public boolean getDynamicValueInTable(WebDriver driver, String fieldName) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, fieldName);
			return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_VALUE_IN_TABLE, fieldName);
		}
		
		public void DynamicPressTabTextArea(WebDriver driver,String dynamicValue) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, dynamicValue);
			sendKeyDynamicboardToElement(driver, Keys.TAB, AbstractPageUI.DYNAMIC_TEXTBOX_BUTTON_CHECKBOX, dynamicValue);
		}
		
}
