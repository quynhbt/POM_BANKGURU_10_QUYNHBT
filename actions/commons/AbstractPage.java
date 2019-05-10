package commons;

import java.util.List;
import java.util.Set;

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
	
	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

//WebElement
	public void clickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
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
	//kiem tra elemnent co duoc hien thi hay k
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
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
	    public void switchToChildWindowByTitle(WebDriver driver, String expectedTitle) {
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
	    
	    public void highlightElement(WebDriver driver, String locator) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        element = driver.findElement(By.xpath(locator));
	        js.executeScript("arguments[0].style.color='red'", element);
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
	    	waitExplicit = new WebDriverWait(driver, 30);
	    	waitExplicit.until(ExpectedConditions.alertIsPresent());
	    }
}
