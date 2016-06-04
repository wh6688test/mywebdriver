package qademo.testng;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import myutil.common.CommonHelper;
import myutil.webdriver.WebElementWait;
import qademo.webdriver.pageobjects.AccountPage;
import qademo.webdriver.pageobjects.CheckoutPage;
import qademo.webdriver.pageobjects.CommonSectionPage;
import qademo.webdriver.pageobjects.LoggedUserHeaderPage;
import qademo.webdriver.pageobjects.ProductCatagoryPage;
import qademo.webdriver.pageobjects.ShippingPage;
import qademo.webdriver.pageobjects.TransactionResultPage;



public class DemoTest1 {
	
	 private final Logger logger= Logger.getLogger(this.getClass().getPackage().getName());
	 WebDriver mydriver = new FirefoxDriver();

	 @BeforeSuite
	 public void testBeforeSuite() throws Exception {
		logger.info("====Suite Setup====");
		mydriver.manage().window().maximize();
		mydriver.manage().timeouts().implicitlyWait(WebElementWait.DEFAULT_SLEEP_TIMEOUT,  TimeUnit.SECONDS);
		mydriver.get(CommonSectionPage.homeURL);
		//user login is needed before test starts
		
	}

	 
	@AfterSuite
	public void testAfterSuite() throws Exception {
		logger.info("====Suite Teardown====");
		mydriver.quit();
	}
	
	
   @BeforeTest
   public void setup() {
   	logger.info("== Setup in test...");
   }
    
   @AfterTest
   public void tearDown() {
 	   logger.info("== Teardown in test...");
   }  
   
	    //http://www.ontestautomation.com/using-the-page-object-model-pattern-in-selenium-testng-tests/
	    @Parameters({"username","password"})
	    @Test(description="Testing sumit an order that contains submitting iPhonr4s 16GB SIM-Free Black, Verifying total price", priority=1)
	    public void testOrderSubmit(String username, String incorrectpassword) { 
	      try {
	    	CommonSectionPage mainPage=new CommonSectionPage(mydriver);
			AccountPage accountPage=mainPage.myAccountFromHeader();
	        LoggedUserHeaderPage loggedUserPage=accountPage.login("testuser22c", "passw0rd1", true);
	        ProductCatagoryPage catagoryPage=loggedUserPage.selectProductCategory("iPhones");
			catagoryPage.addProductToCart("Black");
		    catagoryPage=catagoryPage.ContinueShopping();
		    catagoryPage.addProductToCart("Black");
		    CheckoutPage checkoutPage=catagoryPage.checkoutItem();
		    ShippingPage shippingPage=checkoutPage.continueCheckout();
			double shippingTotalSum=shippingPage.getTotalSum();
			System.out.println("Price 1 : "  + shippingTotalSum);
			TransactionResultPage transactionResultPage=shippingPage.completeOrder( "testmail@testemail.com","firstName", "lastName", "address", "Austin", "TX", "US", "1234566666", true);
			double orderedTotalPrice=transactionResultPage.completeOrderTotalPrice();
			System.out.println("Price 2 : " + orderedTotalPrice);			 
	        Assert.assertEquals(shippingTotalSum, orderedTotalPrice, "order total price is not correct");
	    } catch (Exception e) {
    		e.printStackTrace();
    		Assert.assertFalse(true, "TestCase Failed with exceptions :"+e.getMessage());
    	}
	     
    }
	    
	    
	    @Parameters({"username","password"})
	    @Test(description="Testing updated order quantity persistence after logout and login back", dependsOnMethods="testOrderSubmit", alwaysRun=true)
	    public void testUpdateOrderQuantity(String username, String incorrectpassword) {     
	    	
	    	try {
	    	    CommonSectionPage mainPage=new CommonSectionPage(mydriver);
			
	    	    ProductCatagoryPage catagoryPage=mainPage.selectProductCategory("iphones");
		    	  
	    	    catagoryPage.addProductToCart("Black");
		        catagoryPage.ContinueShopping();
	    	    catagoryPage.selectProductCategory("ipads");
	    	    catagoryPage.addProductToCart("White");
		        CheckoutPage checkoutPage=catagoryPage.checkoutItem();
	            checkoutPage=checkoutPage.updateCheckoutItemQuantity("Black", "6");
	            int itemNoBeforeLogout=checkoutPage.getCheckoutItemQuantity("Black");
	            checkoutPage.logout();
	            AccountPage accountPage=mainPage.myAccountFromHeader();
	            LoggedUserHeaderPage loggedUserPage=accountPage.login("testuser22c", "passw0rd1", true);
	            checkoutPage=loggedUserPage.checkoutFromHeader();
	            int itemNoAfterLogout=checkoutPage.getCheckoutItemQuantity("Black");
	            Assert.assertEquals(itemNoBeforeLogout, itemNoAfterLogout, "updated Quantity is not correct after account logout and login again");
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		Assert.assertFalse(true, "TestCase Failed with exceptions :"+e.getMessage());
	    	}
	    	
	    	}
	     
	    @Parameters({"username","password"})
	    @Test(description="Testing remove items from cart", dependsOnMethods="testUpdateOrderQuantity", alwaysRun=true)
	    public void testRemoveAllItemsFromCart(String username, String incorrectpassword) {     
	      //try {
	    	    CommonSectionPage mainPage=new CommonSectionPage(mydriver);
	    	    logger.info("INFO : get Common Section Page");
	    	    CheckoutPage checkoutPage=mainPage.checkoutFromHeader();
	    	    logger.info("INFO : getCheckout Page");
	    	    checkoutPage=checkoutPage.removeAllItems();
	    	    logger.info("INFO : removed all elements");
				Assert.assertTrue(checkoutPage.isCheckoutCartEmpty());
	    	//} catch (Exception e) {
	          //  e.printStackTrace();
	    		//CommonHelper.log(e.getCause().getMessage());
	    		//Assert.assertFalse(true, "TestCase Failed with exceptions :"+e.getMessage());
	    	//}
	    	
	    	}
	     
	
}

