package util.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WebDriverUtil {
	
	 public static boolean isValid(WebElement e, By condition) {
		    try {
		        WebElementWait wait = new WebElementWait(e, 1);
		        @SuppressWarnings("unused")
		        WebElement icon = wait
		                .until(new Function<WebElement, WebElement>() {
		                    public WebElement apply(WebElement d) {
		                    	
		                        return d.findElement(By
		                                .xpath("./following-sibling::div[class='invalid-icon']"));
		                    }
		                });
		        return false;
		    } catch (TimeoutException exception) {
		        return true;
		    }
		}
	 
	
	 public static WebElement myWaitForElement(WebDriver driver, int timeout) {
		 
		 WebElement element=(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<WebElement>() {
			 @Override
			 public WebElement apply(WebDriver d) {
				 return d.findElement(By.name("elementName"));
			 }
		 });
		 return element;
	 }
	 
	 public  static void myWaitForClickable(WebDriver driver, By locator, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.elementToBeClickable(locator));
	 }
	 
	 public  static void myWaitForClickable(WebDriver driver, WebElement element, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.elementToBeClickable(element));
	 }
	 
	 public  static void myWaitForSelected(WebDriver driver, By locator, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.elementToBeSelected(locator));
	 }
	 
	 public  static void myWaitForSelected(WebDriver driver, WebElement element, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.elementToBeSelected(element));
	 }
	 
	 
	 public  static void myWaitForVisible(WebDriver driver, By locator, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 }
	 
	 public  static void myWaitForVisible(WebDriver driver, WebElement element, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.visibilityOf(element));
	 }
	 
	 public static void myWaitForPresence(WebDriver driver, By by, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.presenceOfElementLocated(by));
	 }
	 
	 public static void myWaitForAttributeValue(WebDriver driver, By by, int timeout, String attribute, String value) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.attributeContains(by, attribute, value));
		 
	 }
	 
	 public static void myWaitForAttributeNotEmpty(WebDriver driver, WebElement element, int timeout, String attribute) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	 }
	 
	 public  static void myWaitForStaleness(WebDriver driver, WebElement element, int timeout) {
		 WebDriverWait wait = new WebDriverWait(driver, timeout);
		 wait.until(ExpectedConditions.stalenessOf(element));
	 }
	
	 
	 public static boolean elementContainsClass(WebElement element, String classAttribute) {
		   return element.getAttribute("class").contains(classAttribute);
	 }
	 
	 public static boolean elementContainsName(WebElement element, String nameAttribute) {
		   return element.getAttribute("name").contains(nameAttribute);
	 }
	 
	 public static WebElement getElementFromBy(WebDriver mydriver, By by) {
		 return mydriver.findElement(by);
	 }
	 
	 public static By getCSSSelector(String cssString) {
		 return By.cssSelector(cssString);
	 }
		//Get text
     public static String getText(WebElement element)  {
			return element.getText();
	 }
     
     public static void scrollElementIntoView(WebDriver driver, WebElement element) {
         ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
     }
     
     }
