package qademo.webdriver.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import util.webdriver.WebDriverUtil;


public class TransactionResultPage extends CommonSectionPage {
	
	   public TransactionResultPage() {
	    	super();
	    	//driver.navigate().to(driver.getCurrentUrl());
	    	//WebDriverUtil.myWaitForVisible(driver, finalShippingBy, 60);
	        

	   }

            private By finalShippingBy=By.cssSelector(".wpsc-transaction-results-wrap>p");
	    
	    public double completeOrderTotalPrice() { 	
		   //String finalTotalPriceString=driver.findElement(finalShippingBy).getText();
		   List<WebElement >allTempElements=driver.findElements(finalShippingBy);
		   String finalTotalPriceString=allTempElements.get(allTempElements.size()-1).getText();
		   System.out.println("final new : " + finalTotalPriceString + "\n");
		   String totalPriceString=finalTotalPriceString.split("\n")[1].trim().split(":")[1].trim().substring(1).replace(",","");
		   return Double.valueOf(totalPriceString);
	   }	   
/**
	    private static final String finalShippingCSS=".wpsc-transaction-results-wrap>p:nth-child(5)";
	    private By finalShippingBy=By.cssSelector(finalShippingCSS);
	 
	   public double completeOrderTotalPrice() { 	
		   String finalTotalPriceString=driver.findElement(finalShippingBy).getText();
		   String totalPriceString=finalTotalPriceString.split("\n")[1].trim().split(":")[1].trim().substring(1).replace(",","");
		   return Double.valueOf(totalPriceString);
	   }
**/
	   
	/**
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	**/

}
