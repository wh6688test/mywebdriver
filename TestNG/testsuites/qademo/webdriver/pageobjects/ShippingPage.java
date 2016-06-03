package qademo.webdriver.pageobjects;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import myutil.webdriver.CommonControlUtil;
//import myutil.webdriver.WebDriverUtil;
//import myutil.webdriver.WebElementWait;


public class ShippingPage extends CommonSectionPage {
	 
	private final Logger logger= Logger.getLogger(this.getClass().getPackage().getName());
	 
	
	   public ShippingPage() {
	    	super();
	    	//driver.navigate().to(driver.getCurrentUrl());
	    	myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingTitleBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
	        

	   }
	 
       private By shippingTitleBy=By.cssSelector("#wpsc_shopping_cart_container>h2");
       
       private By billingEmailBy=By.cssSelector(".wpsc_email_address_label input[placeholder=Email]");
       private By billingFirstNameBy=By.cssSelector(".wpsc_checkout_table input[title=billingfirstname]");
       private By billingLastNameBy=By.cssSelector(".wpsc_checkout_table input[title=billinglastname]");
       private By billingAddressBy=By.cssSelector(".wpsc_checkout_table textarea[title=billingaddress]");
       private By billingCityBy=By.cssSelector(".wpsc_checkout_table input[title=billingcity]");
       private By billingStateBy=By.cssSelector(".wpsc_checkout_table input[title=billingstate]");
       private By billingCountryBy=By.cssSelector(".wpsc_checkout_table select[title=billingcountry]");
       private By billingPhoneBy=By.cssSelector(".wpsc_checkout_table input[title=billingphone]");
       
       private By shippingFirstNameBy=By.cssSelector(".wpsc_checkout_table input[title=shippingingfirstname]");
       private By shippingLastNameBy=By.cssSelector(".wpsc_checkout_table input[title=shippinglastname]");
       private By shippingAddressBy=By.cssSelector(".wpsc_checkout_table textarea[titleshippingaddress]");
       private By shippingCityBy=By.cssSelector(".wpsc_checkout_table input[title=shippingcity]");
       private By shippingStateBy=By.cssSelector(".wpsc_checkout_table input[title=shippingstate]");
       private By shippingCountryBy=By.cssSelector(".wpsc_checkout_table select[title=shippingcountry]");
       private By shippingPhoneBy=By.cssSelector(".wpsc_checkout_table input[title=shippingphone]");
       
       public double getTotalSum() {
    	   By priceBy=By.cssSelector(".total_price>.wpsc_totals>#checkout_total>span");
    	   myutil.webdriver.WebDriverUtil.myWaitForPresence(driver, priceBy, 60);
    	   WebElement subElement=driver.findElement(priceBy);
    	   return Double.valueOf((subElement.getText().substring(1)).replace(",", ""));
       }
       
       By shippingBillSameBy=By.cssSelector("#shippingSameBilling[value=true]"); 
       protected  void shippingAddressIsSame() {
    	   
    	   try {
    	   //WebElement checkbox=driver.findElements(shippingBillSameBy).get(0);
    	   WebElement checkbox=driver.findElement(shippingBillSameBy);
    	   myutil.webdriver.WebDriverUtil.myWaitForClickable(driver, shippingBillSameBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    	       	   
    	   if (!checkbox.isSelected()) {
    		   CommonControlUtil.multiStepMoveClick(driver, checkbox, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    	   }
    	   } catch (Exception e) {
    		   e.printStackTrace();
    		   logger.severe(e.getMessage());
    	   }
      
       }
	   
	   By goBackBy=By.cssSelector(".step1>span");
	   
	   public CheckoutPage goBackToCheckout() {
		   myutil.webdriver.WebDriverUtil.myWaitForClickable(driver, goBackBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		   driver.findElement(goBackBy).click();
		   return PageFactory.initElements(driver, CheckoutPage.class);
	   }
	   
	   By purchaseBy=By.cssSelector(".make_purchase.wpsc_buy_button");
	   public  TransactionResultPage makePurchase() { 
		   
		   CommonControlUtil.multiStepMoveClick(driver, driver.findElement(purchaseBy), myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		   return new TransactionResultPage();
	   }
	   
	   public TransactionResultPage completeOrder(String email, String firstName, String lastName, String address, String city, String state, String country, String phone, boolean isShipAddressSame) {
	      
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, purchaseBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("1");
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingEmailBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("2");
		   WebElement billingEmailElement=driver.findElement(billingEmailBy);
                   System.out.println("3");
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingLastNameBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("4");
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingCityBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("5");
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingPhoneBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("6");
		   myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingCountryBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
                   System.out.println("7");

		   billingEmailElement.clear();
		   billingEmailElement.sendKeys(email);
		   
		   if (isShipAddressSame) {
			      shippingAddressIsSame();
		   } else {
			   WebElement shippingFirstNameElement=driver.findElement(shippingFirstNameBy);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingFirstNameBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingLastNameBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingCityBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingPhoneBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, shippingCountryBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		           myutil.webdriver.WebDriverUtil.myWaitForVisible(driver, billingFirstNameBy, myutil.webdriver.WebElementWait.DEFAULT_SLEEP_TIMEOUT);
			   shippingFirstNameElement.clear();
			   shippingFirstNameElement.sendKeys(firstName);
			   WebElement shippingLastNameElement=driver.findElement(shippingLastNameBy);
			   shippingLastNameElement.clear();
			   shippingLastNameElement.sendKeys(lastName);
			   WebElement shippingCityElement=driver.findElement(shippingCityBy);
			   shippingCityElement.clear();
			   shippingCityElement.sendKeys(city);
			   WebElement shippingPhoneELement=driver.findElement(shippingPhoneBy);
			   shippingPhoneELement.clear();
			   shippingPhoneELement.sendKeys(phone);
			   CommonControlUtil.selectBoxByValue(driver.findElement(shippingCountryBy), country);
			   //CommonControlUtil.selectBoxByValue(driver.findElement(shippingStateBy), state);
		   }
		   
		   WebElement firstNameElement=driver.findElement(billingFirstNameBy);
		   firstNameElement.clear();
		   firstNameElement.sendKeys(firstName);
		   WebElement lastNameElement=driver.findElement(billingLastNameBy);
		   lastNameElement.clear();
		   lastNameElement.sendKeys(lastName);
		   WebElement cityElement=driver.findElement(billingCityBy);
		   cityElement.clear();
		   cityElement.sendKeys(city);
		   WebElement phoneElement=driver.findElement(billingPhoneBy);
		   phoneElement.clear();
		   phoneElement.sendKeys(city);
		   

		   CommonControlUtil.selectBoxByValue(driver.findElement(billingCountryBy), country);
		   //CommonControlUtil.selectBoxByValue(driver.findElement(billingStateBy), state);
		   
		   //getTotalSum();
		   //System.out.println("TotalOrderPrice : "+totalOrderPrice);
		  return makePurchase();
		   //double totalOrderedPrice=completeOrderTotalPrice();
		   //System.out.println("TotalOrderedPrice : "+totalOrderedPrice);
	   }
	  
	    
       private static final String purchaseTextCSS=".wpsc-transaction-results-wrap>p";
	   private By purchaseTextBy=By.cssSelector(purchaseTextCSS);
	  
	   private String getFinalOrderedPriceString() {
		   
		   for (WebElement element : driver.findElements(purchaseTextBy)) {
			   String elementText=element.getText();
			   if (elementText.contains("Total")) {
				   return elementText;
			   }
		   }
		   return null;
	   }
	  
	   
	   public double getFinalOrderedPrices (String priceText, String separator, Boolean isShipping) {
		   
		   double result=0.0;
		   for (String line : priceText.split(separator) ) {
			   if (isShipping) {
				   if (line.toLowerCase().contains("shipping")) {
					   result= Double.valueOf(line.split(":")[1].trim().substring(1));   
				   }
			   } else {
				   if (! line.toLowerCase().contains("shipping")) {
					   System.out.println(line);
					   String totalS=(line.split(":")[1].trim().substring(1)).replace(",", "");
					   result=Double.valueOf(totalS);
				   }
			   }
		   }
		   return result;
	   }
	   
	   public double completeOrderTotalPrice() { 
		   return getFinalOrderedPrices(getFinalOrderedPriceString(), "<br/>", false);
	   }
	   
	 
	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//makePurchase();
        //String my="Total Shipping: $12.00\n"+ "<br/>"+"Total: $5,492.00";
        
        //System.out.println(getFinalOrderedPrices (my, "<br/>", true));
        //System.out.println(getFinalOrderedPrices (my, "<br/>", false));			
	}
	**/

}
