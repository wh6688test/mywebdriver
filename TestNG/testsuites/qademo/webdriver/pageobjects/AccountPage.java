package qademo.webdriver.pageobjects;

//import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import myutil.webdriver.WebDriverUtil;
import myutil.webdriver.WebElementWait;


/**
 * @author Wendy
 *
 */
public class AccountPage extends CommonSectionPage {
	
    //WebDriver driver;
    private String pageLocation=homeURL+"products-page/your-account/";
  
    public AccountPage() {
    	super();  	
    	//PageFactory.initElements(driver, AccountPage.class);
        //driver.get(pageLocation);
       driver.navigate().to(pageLocation);
    }
    
    @ FindBy(how=How.ID, using="log")
	WebElement accountUsernameElement;
    @ FindBy(how=How.ID, using="pwd")
   	WebElement accountPwdElement;
    @ FindBy(how=How.ID, using="rememberme")
   	WebElement accountRemembermeElement;
    @ FindBy(how=How.ID, using="login")
   	WebElement accountSubmitElement;
    
    private By userProfileBy= By.cssSelector(".user-profile-links>.current");
    
    public LoggedUserHeaderPage login(String username, String password, boolean rememberme) {
    	
    	WebDriverUtil.myWaitForVisible(driver, accountSubmitElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    	accountUsernameElement.sendKeys(username);
    	accountPwdElement.sendKeys(password);
    	if (rememberme) {
    		accountRemembermeElement.click();
    	}
    	accountSubmitElement.click();	
    	WebDriverUtil.myWaitForVisible(driver, userProfileBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
   
    	return new LoggedUserHeaderPage();
  }
    
  public LoginPage goLogin() {
	  
	  metaLoginElement.click();
	  return PageFactory.initElements(driver, LoginPage.class);
  }
  /**
  public static void main(String[] args) {
		
  }
  **/
  
}
