package qademo.webdriver.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends CommonSectionPage {

	  //private String pageLocation=homeURL+"/tools-qa/";
	    
	  public LoginPage() {
	    	//this.driver=driver;
	    	//driver.get(pageLocation);
	    }
	    
	    @ FindBy(how=How.ID, using="user_login")
		WebElement usernameElement;
		
	    @ FindBy(how=How.ID, using="user_pass")
	   	WebElement pwdElement;
	    
	    @ FindBy(how=How.ID, using="rememberme")
	   	WebElement remembermeElement;
	    
	    @ FindBy(how=How.ID, using="wp_submit")
	   	WebElement submitElement;
	    
	    
	    void login(String username, String password, boolean rememberme) {
	    	
	    	usernameElement.sendKeys(username);
	    	pwdElement.sendKeys(password);
	    	if (rememberme) {
	    		remembermeElement.click();
	    	}
	    	submitElement.submit();
	  }
	
	    /**
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	**/

}
