package qademo.webdriver.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.webdriver.WebDriverUtil;
import util.webdriver.WebElementWait;


public class LoggedUserHeaderPage extends CommonSectionPage{
	
	 private String pageLocation=homeURL+"products-page/your-account/?login=1";
	 public LoggedUserHeaderPage() {
	    	//super(driver);  	
		    //PageFactory.initElements(driver, LoggedUserHeaderPage.class);
	        driver.navigate().to(pageLocation);
	    }

	 @ FindBy(how=How.CSS, using="#account_logout>a")
	WebElement LogoutElement;
	 
	 public void logout () {
		 LogoutElement.click();
	 }
	 
	 @ FindBy(how=How.CSS, using=".user-profile-links>.current")
	 WebElement purchaseHistoryElement;
	 
	 private static final String logDisplayString=".myaccount>.logdisplay>tbody>tr>td";
	 private By logDisplayBy=By.cssSelector(logDisplayString);
	 
	 
	 public String getYourAccountTransaction() {
		 WebDriverUtil.myWaitForVisible(driver, logDisplayBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		 
		 return WebDriverUtil.getElementFromBy(driver, logDisplayBy).getText();
	 }
	 
	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    **/
}
