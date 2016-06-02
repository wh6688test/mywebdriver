package qademo.webdriver.pageobjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.webdriver.WebDriverUtil;
import util.webdriver.WebElementWait;
import util.webdriver.CommonControlUtil;

import org.openqa.selenium.JavascriptExecutor;

public class ProductCatagoryPage extends CommonSectionPage{
    final String pageLocation=homeURL+"products-page/product-category/";
    final String productCSSName=".wpsc_product_title";
    final String buttonCSS=".wpsc_buy_button";
    final String productListCSS=".productcol"; 
    
    public ProductCatagoryPage() {
    	super();
    	driver.navigate().to(driver.getCurrentUrl());
    	WebDriverUtil.myWaitForClickable(driver, allProductBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    }
    
    /**
     * get the category title
     */
    
    @ FindBy(how=How.CSS, using=".entry-title")
    private WebElement productTitleElement;
   
    private By popupContinueBy=By.cssSelector(".continue_shopping");   
    private By popupCheckoutBy=By.cssSelector(".go_to_checkout");
    
    @FindBy(how=How.ID, using="fancy_notification_content")
    private WebElement popupNotificationElement;
    
  
    /**
     * 
     * @return : toplevel container list
     */
	private List<WebElement> getProductCatagoryList() {
		List<WebElement>productList=driver.findElements(getByForProductList());
		return productList;	
	}
	private By getByForProductList() {
		return By.cssSelector(productListCSS);
	}
	
	
	private List<WebElement> getProductCatagoryLinkList() {
		return driver.findElements(getByForProductLinkList());
	}
	
	private WebElement getProductCatagoryLinkList(WebElement element) {
		WebElement foundElement=element.findElement(getByForProductLinkList());
		return foundElement;
	}
	
	
	private By getByForProductLinkList() {
		return By.cssSelector(productCSSName);
	}
	
	 /**
     * @return : specific item link
     */
	private  WebElement getProductElementLink(String itemName) {
		 List<WebElement> elementList =getProductCatagoryLinkList();
		 for (WebElement element : elementList) {
			  if (element.getText().contains(itemName)) {
				  return element;
			  }
		 }
		return null;
	}
 
	
    private void addToCartButton(WebElement element) {
    	
    	By foundBy=getByForButtonList();
    	WebElement foundElement=element.findElement(foundBy);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", foundElement);
        CommonControlUtil.moveToElement(driver, foundElement);
        WebDriverUtil.myWaitForVisible(driver, foundBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    	WebDriverUtil.myWaitForClickable(driver, foundBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
    	foundElement.submit();
    }
    private By getByForButtonList() {
    	return By.cssSelector(buttonCSS);
    }
	
	public void addProductToCart(String catagoryName) {
		List<WebElement> allElements=getProductCatagoryList();
		for(WebElement element : allElements) {
			if (getProductCatagoryLinkList(element).getText().contains(catagoryName)) {
				WebDriverUtil.myWaitForVisible(driver, element, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
				addToCartButton(element);
			}
		}
	}
	
	public int getProductSize() {
		return getProductCatagoryList().size();
	}
	
	public String getProductListTitle() {
		   return productTitleElement.getText();
	}
	
	  @FindBy(how=How.CSS, using="#fancy_notification_content>span")
	  private  WebElement popupTextElement;
	    
      private By popupTextBy=By.cssSelector("#fancy_notification_content>span");
	  
      public ProductCatagoryPage ContinueShopping() {  
		  WebDriverUtil.myWaitForClickable(driver, popupContinueBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		  WebDriverUtil.getElementFromBy(driver, popupContinueBy).click();
		  return PageFactory.initElements(driver, ProductCatagoryPage.class);
      }
	
      public CheckoutPage checkoutItem() {
		  WebDriverUtil.myWaitForClickable(driver, popupCheckoutBy, WebElementWait.DEFAULT_SLEEP_TIMEOUT);
		  driver.findElement(popupCheckoutBy).click();
		  return new CheckoutPage();
      }
      
      private String getProductString(String productCatagory) {
    	  return productCatagory.toLowerCase();
      }
      
 	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		    WebDriver driver = new FirefoxDriver();
		    
				    
				//try {
					//CheckoutPage catagoryPage=PageFactory.initElements(driver, CheckoutPage.class);
		  ProductCatagoryPage catagoryPage=PageFactory.initElements(driver, ProductCatagoryPage.class);
				    //CheckoutPage checkoutPage=new CheckoutPage(driver).get();
					System.out.println(catagoryPage.getProductListTitle());
					System.out.println(catagoryPage.getProductSize());
					//System.out.println(catagoryPage.getProductElementCartButton("iPhone 5").getText());
					
					catagoryPage.addProductToCart("iPhone 5");
					catagoryPage.checkoutItem();
					//catagoryPage.ContinueShopping();
					//catagoryPage.addProductCart("Apple iPhone 4S 16GB SIM-Free � Black ");
				    //catagoryPage.checkoutItem();
						  
					
					//System.out.println(catagoryPage.popupNotificationElement);
					//WebElement checkoutElement=catagoryPage.popupCheckoutElement;
					//WebElement continueElement=catagoryPage.popupContinueElement;
					//WebDriverUtil.myWaitForClicable(driver, continueElement, 120);
					//continueElement.click();
					//System.out.println(checkoutElement);
					//WebDriverUtil.myWaitForClicable(driver, checkoutElement, 120);
					//checkoutElement.click();
					
					
					//catagoryPage.popupCheckoutElement.click();
					//driver.switchTo().defaultContent();
					//driver.switchTo().alert();
					//System.out.println("Here2");
					//WebElement myelement=catagoryPage.getNotificationElement();
					//catagoryPage.switchToPopup(driver);		
					//Alert alert = driver.switchTo().alert();
				    //System.out.println(catagoryPage.popupCheckoutElement);
					
					//System.out.println(myelement);
					//catagoryPage.continueShopping();
					//System.out.println("Exception happened");
				//}
			    //finally {
			    	//driver.quit();
			    //}
			}
	**/
	
}
