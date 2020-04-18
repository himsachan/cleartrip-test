package cleartrip.cleartrip_test.web_pages;


import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	
	static Logger logger = Logger.getLogger(BasePage.class);
	
	protected WebDriverWait wait;
	protected WebDriver driver;
	
	public void init(WebDriver driver) {
		logger.info("driver value :"+driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 40);
	}
	
	public boolean waitForUrl(String urlString, WebDriver driver) throws InterruptedException
	{
		int attempts=50;
		while(attempts>0&&!driver.getCurrentUrl().contains(urlString)){
			Thread.sleep(200);
			attempts--;
		}
		if(attempts==0){
			logger.info("---Unable to load Page with Url string : " + urlString + "  --- ");
			return false;
		}
		return true;
	}
	
	/**
	   * Allows to scroll down to specific position in the page
	   */
	  public void scrollDown(WebDriver driver,String X, String Y) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("scroll("+X+","+Y+")");

	  }

	  
	  /**
	   * Switch to the second windows
	   */
	  
	  public String windowhandle(WebDriver driver) throws InterruptedException
		{
			String cHandle = driver.getWindowHandle();
	        String newWindowHandle = null;
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        for (int i = 0; i < 4; i++) 
	        {
	            if (allWindowHandles.size() > 1)  //to switch the controls to another browser window
	            {
	                for (String allHandlers : allWindowHandles) 
	                {
	                    if (!allHandlers.equals(cHandle))
	                    	newWindowHandle = allHandlers;
	                }
	                driver.switchTo().window(newWindowHandle);
	                break;
	            } 
	            else 
	            {
	            	logger.info("Waiting for new window");
	                Thread.sleep(1000);
	            }
	        }
	        if (cHandle != newWindowHandle) 
	        {
	        	return "true"; 
	        }
	        else
	        {
	        	logger.info("Time out - No window found");
	        	return null;
	        }
	    }
	  
	  public void selectOptionWithText(String textToSelect,WebDriver driver, String travellerNo) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 40);
				WebElement autoOptions = driver.findElement(By.id("ui-id-<item_index>".replace("<item_index>",travellerNo)));
     			wait.until(ExpectedConditions.visibilityOf(autoOptions));

				List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
				for(WebElement option : optionsToSelect){
			        if(option.getText().equals(textToSelect)) {
			        	System.out.println("Trying to select: "+textToSelect);
			            option.click();
			            break;
			        }
			    }
				
			} catch (NoSuchElementException e) {
				System.out.println(e.getStackTrace());
			}
			catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
}
