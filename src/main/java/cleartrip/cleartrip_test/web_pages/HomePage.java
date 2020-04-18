package cleartrip.cleartrip_test.web_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	   //Page URL
	   private static String PAGE_URL="https://www.cleartrip.com/";

	   //Constructor
	   public HomePage(WebDriver driver){
		   this.driver=driver;
		   init(driver);
	       PageFactory.initElements(driver, this);
	      
	   }
	   
	   public boolean loadUrl() {
		   driver.get(PAGE_URL);
		   driver.manage().window().maximize();
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchBtn")));
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("return window.stop");
		   return true;
	   }

}
