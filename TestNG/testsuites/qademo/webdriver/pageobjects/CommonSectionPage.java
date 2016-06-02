package qademo.webdriver.pageobjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.InvalidCookieDomainException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import util.webdriver.CommonControlUtil;
import util.webdriver.WebDriverUtil;
import util.webdriver.WebElementWait;

import com.google.common.base.Function;

public class CommonSectionPage  {
	
	
	   public static WebDriver driver;
	   Set<Cookie> allCookies;
	   public final static String homeURL="http://store.demoqa.com/";
	   
	   public CommonSectionPage() {
		   
	   }
	   public CommonSectionPage(WebDriver thisdriver) {
		   driver=thisdriver;
		   //driver.navigate().to(driver.getCurrentUrl());
		   driver.get(homeURL);
		  
	   }
	   
	   @ FindBy(how=How.ID, using="logo")
	   protected WebElement logElement;   
	   
	   @ FindBy(how=How.CSS, using=".cart_icon")
	   protected WebElement checkoutElement;
	   
	   @ FindBy(how=How.CSS, using=".account_icon>.icon")
	   protected WebElement accountElement;
	   
	   @ FindBy(how=How.CSS, using="#main-nav>#menu-main-menu>#menu-item-15>a")
	   protected WebElement homeElement;
	   
	   private final String productCatagoryString="#main-nav>#menu-main-menu>#menu-item-33>a";
	   private By productCatagoryBy=By.cssSelector(productCatagoryString);
	   @ FindBy(how=How.CSS, using=productCatagoryString)
	   protected WebElement productCatagoryElement;
	   
	   @ FindBy(how=How.CSS, using="#main-nav>#menu-main-menu>#menu-item-72>a")
	   protected WebElement allProductElement;
	   protected By allProductBy=By.cssSelector("#main-nav>#menu-main-menu>#menu-item-72>a");
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-34>a")
	   protected WebElement accessaryProductElement;
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-35>a")
	   protected WebElement imacsProductElement;
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-36>a")
	   protected WebElement ipadsProductElement;
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-37>a")
	   protected WebElement iphonesProductElement;
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-38>a")
	   protected WebElement ipodsProductElement;
	   
	   @ FindBy(how=How.CSS, using=".sub-menu>#menu-item-39>a")
	   protected WebElement macbooksProductElement;
	   
	   @ FindBy(how=How.LINK_TEXT, using="http://store.demoqa.com/tools-qa/?action=register")
	    protected WebElement metaRegisterElement;
	    @ FindBy(how=How.CSS, using="#meta>ul>li>a")
	    protected WebElement metaLoginElement;
	    
	    private static final String logoutCSS="#account_logout>a";
	    By logoutBy=By.cssSelector(logoutCSS);
	    
	 
		 public void logout () {
			 WebElement logoutElement=driver.findElement(logoutBy);
			 if (logoutElement != null) {
			    logoutElement.click();
			 }
		 }
		 
	    /**
	   void setCookies() {
		   
		      System.out.println(driver.getCurrentUrl());
		      
			  allCookies=driver.manage().getCookies();
			  System.out.println(allCookies);
		      for (Cookie cookie : allCookies ) {
		    	  try {
		    	     driver.manage().addCookie(cookie);
		    	  } catch (InvalidCookieDomainException e) {
		    		  e.printStackTrace();
		    		  //basically ignore those cookies that do not belong to you
		    	  }
		      }
	   }
	   **/
	 
	   public CheckoutPage checkoutFromHeader() {
		   
		   WebElement checkoutElement = driver.findElement(By.cssSelector("#header_cart>.cart_icon[href]"));
		   WebDriverUtil.myWaitForClickable(driver, checkoutElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		   checkoutElement.click();
		   //to do : if user is logged
		   return new CheckoutPage();
		   //return PageFactory.initElements(driver, CheckoutPage.class);
	   }
	   CheckoutPage checkoutIconFromHeader() {
		   WebDriverUtil.myWaitForClickable(driver, checkoutElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		   checkoutElement.findElement(By.className("icon")).click();
		   return PageFactory.initElements(driver, CheckoutPage.class);
	   }
	   
	   public int getItemCountFromHeader() {
		   return Integer.parseInt(checkoutElement.findElement(By.className("count")).getText());
	   }
	   
	   private final By accountElementBy=By.cssSelector(".account_icon>.icon");
	   
	  public AccountPage myAccountFromHeader() {
		   WebElement accountElement=WebDriverUtil.getElementFromBy(driver, accountElementBy);
		   System.out.println(accountElement);
		   WebDriverUtil.myWaitForClickable(driver, accountElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		   accountElement.click();
		   return PageFactory.initElements(driver, AccountPage.class);
	   }
	   
	   public AccountPage accountIconFromHeader() {
		   accountElement.findElement(By.className("icon")).click();
		   return PageFactory.initElements(driver, AccountPage.class);
	   }
	   
	   
	   @FindBy(how=How.CSS, using="#menu-main-menu>#menu-item-33>.sub-menu")
	    WebElement subMenuElement;
	    
	    @FindBy(how=How.CSS, using="#menu-item-33>a")
	    WebElement productCategoryMenuElement;
	    
	    private By subMenuBy(String category) {
	    	 String subElementLocator;
	    	 switch (category.toLowerCase()) {
		        case "accessories": 
		        	subElementLocator="#menu-item-34>a";
			        break;
		        case "imacs" : 
		        	subElementLocator="#menu-item-35>a";
			         break;
		        case "ipads" : 
		        	subElementLocator="#menu-item-36>a";
		        	break;
		        case "iphones" : 
		        	subElementLocator="#menu-item-37>a";
		        	break;
		        case "ipods" : 
		        	subElementLocator="#menu-item-38>a";
		        	break;
		        case "macbooks" : 
		        	subElementLocator="#menu-item-39>a";
		        	break;
		        default : 
			        subElementLocator="#menu-item-33>a";
	    	 }
			   
	 	     return WebDriverUtil.getCSSSelector(subElementLocator);
	    		
	    }
	    
	     public ProductCatagoryPage selectProductCategory(String category) {
	    	  
	    	  By subElementBy=subMenuBy(category);
	    	  
	    	  CommonControlUtil.dropDownMenuClick(driver, productCatagoryBy, subElementBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
	    	  return PageFactory.initElements(driver, ProductCatagoryPage.class);
	    
	      }
	  
	     void selectProductCategory() {
	    	 WebDriverUtil.myWaitForClickable(driver, productCatagoryBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
	         driver.findElement(productCatagoryBy).click();
	     
	     }
}
