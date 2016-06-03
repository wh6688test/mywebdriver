package myutil.webdriver;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import myutil.webdriver.WebDriverUtil;

//http://www.guru99.com/accessing-forms-in-webdriver.html
@SuppressWarnings("unused")
public class CommonControlUtil {
	static Logger log= Logger.getLogger(CommonControlUtil.class.getPackage().getName());
    /**
     * Select an item from a selection box
     * @param selection
     * @param selectValue
     */
	//http://www.seleniumeasy.com/selenium-tutorials/webdriver-select-methods-to-work-with-dropdowns
	public static void selectBoxByValue(WebElement selection, String selectValue) {	
	   //try {
		Select element=new Select(selection);
		element.selectByValue(selectValue);
	   //} catch (StaleElementReferenceException e) {
		// log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
	   //} catch (NoSuchElementException e) {
		// log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
	   //} catch (Exception e) {
		// log.severe("Unknow Exception : \n"+e.getStackTrace());
	   //}	
	   
	}
	
	public static void selectBoxByText(WebElement selection, String text) {	
		   try {
			Select element=new Select(selection);
			element.selectByVisibleText(text);
		   } catch (StaleElementReferenceException e) {
			 log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
		   } catch (NoSuchElementException e) {
			 log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
		   } catch (Exception e) {
			 log.severe("Unknow Exception : \n"+e.getStackTrace());
		   }	
		}
	
	public static void selectBoxByIndex(WebElement selection, int index) {	
		   try {
			Select element=new Select(selection);
			element.selectByIndex(index);;
		   } catch (StaleElementReferenceException e) {
			 log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
		   } catch (NoSuchElementException e) {
			 log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
		   } catch (Exception e) {
			 log.severe("Unknow Exception : \n"+e.getStackTrace());
		   }	
		}
	/**
	 * 
	 * @param elements (array of element)
	 */
	public void selectCheckBoxes(WebElement ... elements) {
		try {
		   for (WebElement check : elements ) {
			    check.click();
		   }
		} catch (StaleElementReferenceException e) {
			log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
		} catch (Exception e) {
			log.severe("Unknow Exception : \n"+e.getStackTrace());
		}	
	}
		
	public void doubleClick(WebDriver driver, WebElement element) {
		try {
		  Actions action = new Actions(driver).doubleClick(element);
		  action.build().perform();
		} catch (StaleElementReferenceException e) {
			log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
		} catch (Exception e) {
			log.severe("Unknow Exception : \n"+e.getStackTrace());
		}	
	}
	
	public void rightClick(WebDriver driver, WebElement element) {
		try {
		   Actions action = new Actions(driver).contextClick(element);
		   action.build().perform();
		} catch (StaleElementReferenceException e) {
			log.severe("StaleElementReferenceException:\n"+e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.severe("NoSuchElementReferenceException:\n"+e.getStackTrace());
		} catch (Exception e) {
			log.severe("Unknow Exception : \n"+e.getStackTrace());
		}	
	}
	
   public static void moveToElement(WebDriver driver, WebElement element) {
	  
	   new Actions(driver).moveToElement(element).perform();
	  
   }
   
   public static void multiStepMoveClick(WebDriver driver, WebElement element, int waitTime) {
	   
	   Actions builder=new Actions(driver);
	   //builder.moveToElement(element).build().perform();
 	   WebDriverUtil.myWaitForVisible(driver, element, waitTime); 
 	   if ("input".equals(element.getTagName()) ){
 		   element.sendKeys("");		   
 	   } else {
 	        builder.moveToElement(element).build().perform();
 	   }
 	   element.click();
   }
   
   
  public static void dropDownMenuClick(WebDriver driver, By menuBy, By subMenuBy, int waitTime) {
	   
	   Actions builder=new Actions(driver);
	   WebDriverUtil.myWaitForVisible(driver, menuBy, waitTime);  
	   builder.moveToElement(driver.findElement(menuBy)).build().perform();
 	   WebDriverUtil.myWaitForVisible(driver, subMenuBy, waitTime);  
 	   builder.moveToElement(driver.findElement(subMenuBy)).click().perform();
 	   
   }

}
