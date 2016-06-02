package qademo.webdriver.pageobjects;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import util.common.CommonHelper;
import util.webdriver.WebDriverUtil;
import util.webdriver.WebElementWait;

public class CheckoutPage extends CommonSectionPage{
	
	        private final Logger logger= Logger.getLogger(this.getClass().getPackage().getName());
	        private By checckoutEntryTitleBy=By.cssSelector(".entry-title");
	        
	        //private String pageLocation=homeURL+"products-page/checkout/";		
	        public CheckoutPage() {
	        	super();
	            /**
		    	Set<Cookie> allCookies=driver.manage().getCookies();
	            for (Cookie cookie : allCookies ) {
	            	driver.manage().addCookie(cookie);
	            }
	            **/
	        	//driver.navigate().to(pageLocation);
	        	//driver.get(pageLocation);
	        	WebDriverUtil.myWaitForVisible(driver, checckoutEntryTitleBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
	        	
	        }
	        
	      
	        
	        private String containerString=".checkout_cart";
	        
	        private List<WebElement>allItemElements() {
	        	return driver.findElements(By.cssSelector(containerString+" a[href]"));
	        }
	        private int elementIndexContainsProduct(String itemName) {
	        	List<WebElement>elements=allItemElements();
	        	int elementSize=elements.size();
	        	for (int i=0 ; i< elementSize; i++) {
	        		try {
	        			String elementName=elements.get(i).getText();
	        			 //if (elementTitle.replaceAll("\\s", "").replaceAll("-", "").trim().contains(itemName.replaceAll("\\s", "").replaceAll("-", "").trim()) == true) {
	        			 if (elementName.replaceAll(" ", "").trim().contains(itemName.replaceAll(" ", "").trim()) == true) { 
	        			    return i;
	        		    } 
	        		}catch (Exception exception) {
	        			exception.printStackTrace();
	        			logger.severe(exception.getMessage());
	        		}
	        	}
	        	return -1;
	        }
	        
	        public int getCheckoutItemQuantity(String itemName) {
	        	
	        	int index=elementIndexContainsProduct(itemName);
	        			
	        	WebElement quantityElement=driver.findElements(By.cssSelector(containerString +" .adjustform.qty>input:nth-child(1)")).get(index);
	        	
	        	return Integer.parseInt(quantityElement.getAttribute("value"));
	        	
	        	
	        }
	        
	        public CheckoutPage  updateCheckoutItemQuantity(String itemName, String newItemNo) {
	        	int index=elementIndexContainsProduct(itemName);
	        	WebElement thisElement=driver.findElements(By.cssSelector(containerString +">tbody>tr>td>form>input:nth-child(1)")).get(index);
	        	thisElement.clear();
	        	thisElement.sendKeys(newItemNo);
	        	driver.findElement(By.cssSelector(containerString + ">tbody>tr>td>form>input:nth-child(4)")).submit();
	        	return this;
	        	
	        }
	        
	        public double getCheckoutItemPrice(String itemName) {
	        	int index=elementIndexContainsProduct(itemName);
	        	return Double.valueOf(driver.findElements(By.cssSelector(containerString + ">tbody>tr>td:nth-child(4)>.pricedisplay")).get(index).getText().substring(1));
	        	
	        }
	        public double getCheckoutItemTotal(String itemName) {
	        	int index=elementIndexContainsProduct(itemName);
	        	return Double.valueOf(driver.findElements(By.cssSelector(containerString + ">tbody>tr>td:nth-child(5)>.pricedisplay")).get(index).getText().substring(1));
	        	
	        }
	        
	        public void removeCheckoutItem(String itemName) {
	        	int index=elementIndexContainsProduct(itemName);
	        	By removeSelector=By.cssSelector(containerString+">tbody>tr>td:nth-child(6)>.adjustform.remove");
	        	WebDriverUtil.myWaitForClickable(driver, removeSelector, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
	        	WebElement elementToRemove=driver.findElements(removeSelector).get(index);
	        	elementToRemove.click();
	        }
	        
	        public CheckoutPage removeAllItems() {
	        
			        	String removeCSS=containerString+" .adjustform.remove>input[value=Remove]";
			        	By removeBy=By.cssSelector(removeCSS);
			       try {
			        	int size=allItemElements().size();
		        		
		        		for (int i=0;i<size && !isCheckoutCartEmpty(); i++) {
		        			List<WebElement>removeElements=driver.findElements(removeBy);
		        			WebElement removeElement=removeElements.get(0);
		        			WebDriverUtil.myWaitForClickable(driver, removeElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		        			removeElement.click();
		        			//WebDriverUtil.myWaitForStaleness(driver, removeElement, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		        		}
		        	} catch (StaleElementReferenceException e) {
		        		e.printStackTrace();
		        		logger.severe(e.getMessage());
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        		logger.severe(e.getMessage());
		        	}
	        	
	        	return this;
	        	
	        }
	        private static final String checkoutSubTotal="#checkout_page_container>.slide1>.yourtotal>.pricedisplay";
	        By checkoutSubTotalBy=By.cssSelector(checkoutSubTotal);
	        double getCheckoutSubTotalPrice() {
	        	return Double.valueOf(driver.findElement(checkoutSubTotalBy).getText().substring(1));
	        }
	        
	        private static final String checkoutContinueCSS=".step2>span";
	        By checkoutContinueBy=By.cssSelector(checkoutContinueCSS);
	        
	        public ShippingPage continueCheckout() {
	        	WebDriverUtil.myWaitForClickable(driver, checkoutContinueBy, 30);
	        	driver.findElement(checkoutContinueBy).click();
	        	return new ShippingPage();
	        }
	        
	        
	        private By checkoutContentBy=By.cssSelector(".entry-content");
	        public boolean isCheckoutCartEmpty() {
	        	if (driver.findElement(checkoutContentBy).getText().contains("nothing")) {
	        		return true;
	        	}
	        	else return false;
	        }

		    public static void main(String[] args) {
				// TODO Auto-generated method stub
				
				        WebDriver mydriver = new FirefoxDriver();
				        CommonSectionPage mainPage=new CommonSectionPage(mydriver);
				       // FirefoxDriver.SystemProperty
						//CommonHeaderPage.setupDriver(mydriver);
						//try {
							//CheckoutPage catagoryPage=PageFactory.initElements(driver, CheckoutPage.class);
				            
				            //AccountPage accountPage=PageFactory.initElements(driver, AccountPage.class);
				            //LoggedUserHeaderPage loggedUserPage=PageFactory.initElements(driver, LoggedUserHeaderPage.class);
				            //ProductCatagoryPage catagoryPage=PageFactory.initElements(driver, ProductCatagoryPage.class);
				            AccountPage accountPage=mainPage.myAccountFromHeader();
				            LoggedUserHeaderPage loggedUserPage=accountPage.login("testuser22c", "passw0rd1", true);
				            System.out.println(loggedUserPage.getYourAccountTransaction());
				      
				            //System.out.println(driver.findElement(By.cssSelector("#header_cart>.cart_icon>.count")).getText());
				        	
				            
				             ProductCatagoryPage catagoryPage=loggedUserPage.selectProductCategory("iPhones");
							 //catagoryPage.addProductToCart("Apple iPhone 4S 16GB SIM-Free - Black");
				        	 catagoryPage.addProductToCart("Black");
							 
						     catagoryPage=catagoryPage.ContinueShopping();
						     
						     catagoryPage.selectProductCategory();
						     catagoryPage.addProductToCart("iPhone 5");
						     catagoryPage=catagoryPage.ContinueShopping();
						     
						    
						     
						     
							 catagoryPage.selectProductCategory("iPhones");
							 //catagoryPage.addProductToCart("Apple iPhone 4S 16GB SIM-Free - Black");
							catagoryPage.addProductToCart("Black");
							
				        	
							 CheckoutPage checkoutPage=catagoryPage.checkoutItem();
							//CheckoutPage checkoutPage=catagoryPage.checkoutFromHeader();
						
							 //checkoutPage=checkoutPage.updateCheckoutItemQuantity("Apple iPhone 4S 16GB SIM-Free - Black", "5");
							catagoryPage.addProductToCart("Black");
					    	 System.out.println("Before Logout : "+checkoutPage.getCheckoutItemQuantity("Apple iPhone 4S 16GB SIM-Free - Black"));
							 
							 
							 checkoutPage.logout();
							 accountPage=mainPage.myAccountFromHeader();
							 loggedUserPage=accountPage.login("testuser22c", "passw0rd1", true);
							 checkoutPage=loggedUserPage.checkoutFromHeader();
							 System.out.println("After Logout :" + checkoutPage.getCheckoutItemQuantity("Apple iPhone 4S 16GB SIM-Free - Black"));
							 
							 catagoryPage=loggedUserPage.selectProductCategory("iPads");
							 
							 catagoryPage.addProductToCart("Black");
							 
							 
							 catagoryPage=catagoryPage.ContinueShopping();
							 
							 catagoryPage.selectProductCategory("iPads");
							 catagoryPage.addProductToCart("Black");
							 checkoutPage=catagoryPage.checkoutItem();
							 
							 checkoutPage=checkoutPage.removeAllItems();
							 System.out.println(checkoutPage.isCheckoutCartEmpty());
							 
							 
							 catagoryPage.selectProductCategory("iPhones");
							 catagoryPage.addProductToCart("Black");
							 checkoutPage=catagoryPage.checkoutItem();
							 ShippingPage shippingPage=checkoutPage.continueCheckout();
							 
							 
							 System.out.println("Price 1 : "+shippingPage.getTotalSum());
							 
							 
							 TransactionResultPage transactionResultPage=shippingPage.completeOrder("testemail@email.com", "firstName", "lastName", "address", "Austin", "TX", "US", "1234566666", true);
							 //shippingPage.completeOrder("firstName", "lastName", "address", "Austin", "USA", "1234566666", true);

							 System.out.println("Price 2 : " + transactionResultPage.completeOrderTotalPrice());
							 
							 
							 
				
				            
		    }
}


