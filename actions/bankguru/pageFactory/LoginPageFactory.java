package bankguru.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import bankguru.LoginPageUI;
import commons.AbstractPage;

public class LoginPageFactory extends AbstractPage {
	private WebDriver driver; 
	
	@FindBy(how = How.XPATH, using = "//form[@name='frmLogin']" )
	private WebElement loginForm;
	
	@FindBy(how = How.NAME, using = "uid" )
	private WebElement userIDTextbox;
	
	@FindBy(how = How.NAME, using = "btnLogin" )
	private WebElement loginButton;
	
	@FindBy(how = How.NAME, using = "//a[text()='here']" )
	private WebElement hereLink;
	
	
	public LoginPageFactory(WebDriver mappingDriver) {
			this.driver = mappingDriver;
			PageFactory.initElements(driver, this.getClass());
	}
	
	public boolean isLoginFormDisplayed() {
		return false;
	}
	
	public String getLoginPageUrl() {
		return null;
	}

	public void clickToHereLink() {
		
	}
	
	public void inputToUserIDTextbox(String userIdfor) {
		
	}

	public void inputToPasswordTextbox(String passwordInfor) {
	
	}

	public void clickToLoginButton() {
		
	}


}
