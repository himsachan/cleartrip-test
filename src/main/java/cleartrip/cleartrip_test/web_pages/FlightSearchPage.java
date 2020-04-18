package cleartrip.cleartrip_test.web_pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends BasePage {
	
	static Logger logger = Logger.getLogger("Flight Search Page");
	
	@FindBy(id="FromTag")
	public WebElement origin;

	@FindBy(id="ToTag")
	public WebElement destination;
	
	@FindBy(id="DepartDate")
	public WebElement departureDate;
	
	@FindBy(id="ReturnDate")
	public WebElement returnDate;
	
	@FindBy(id="Adults")
	public WebElement adults;
	
	@FindBy(id="Childrens")
	public WebElement child;
	
	@FindBy(id="SearchBtn")
	public WebElement searchFlight;
	
	@FindBy(id="RoundTrip")
	public WebElement roundTrip;
	
	
//	@FindBy(xpath=".//section[@class='resultsContainer']//div[@class='row roundTripHead']//button[@class='booking fRight']")
//	public WebElement bookButton;  
	
	@FindBy(xpath="/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[5]/div/div[2]/button")
	public WebElement bookButton1;  
	
	@FindBy(xpath="/html/body/section[3]/div[2]/section[2]/section/div/form/section[2]/div[3]/div[3]/button")
	public WebElement bookButton;  
	
	@FindBy(xpath="/html/body/section[3]/div[2]/section[2]/section/div/form/section[2]/div[4]/div[2]/div/nav/ul/li[4]/a")
	public WebElement sortByDeparture;  

	@FindBy(xpath="/html/body/div[1]/div/main/div/div/div[2]/div[2]/div[8]/div/div[2]/div/div[2]/div[1]/a/span")
	public WebElement sortByDeparture1;
	
	@FindBy(xpath="/html/body/div[1]/div/main/div/div/div[1]/div/aside/div[4]/div[2]/div[2]/div/label[1]/div[1]/span")
	public WebElement nonStopCheckBox;
	
	public FlightSearchPage(WebDriver driver) {
		init(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean fillFlightSearchDetails(String from, String to, String departuredate,String returndate, int adultCount, int childCount )
	{
		try {
			roundTrip.click();
			origin.sendKeys(from);
			destination.sendKeys(to);
			departureDate.sendKeys(departuredate);
			returnDate.clear();
			returnDate.sendKeys(returndate);
			driver.findElement(By.xpath(".//a[@class='ui-state-default ui-state-active ']")).click();
			Select select = new Select(adults);
			select.selectByValue(String.valueOf(adultCount));
			select = new Select(child);
			select.selectByValue(String.valueOf(childCount));
			searchFlight.click();
			logger.info("Search flight successful");
			if(waitForUrl("results",driver))
			{
				logger.info("result page loaded successfully ");
				return true;
			}
			else
			{
				logger.info("unable to load result page");
				return false;
			}
		}
		catch(Exception e) {
			logger.error("Error in filling details for search  flight "+e.getMessage());
			return false;
		}
	}
	
	public boolean selectAndBookFlight() {
		try
		{
			String url = driver.getCurrentUrl();
			logger.info("current url after  search flight page : "+url);
			if(url.contains("results?origin")) {
				wait.until(ExpectedConditions.elementToBeClickable(bookButton1));
				nonStopCheckBox.click();
				sortByDeparture1.click();
				sortByDeparture1.click();
				bookButton1.click();
				if(windowhandle(driver).equals("true")) {
					Thread.sleep(100);
					waitForUrl("flights/itinerary", driver);
					return true;
				}else {
					logger.error("not able  to switch to new window");
					return false;
				}
			}else {
				wait.until(ExpectedConditions.elementToBeClickable(bookButton));
				sortByDeparture.click();
				sortByDeparture.click();
				bookButton.click();
			}
			return true;
		}
		catch(Exception e)
		{
			logger.error("Error in finding/clicking booking button :"+e.getMessage());
			return false;
		}	
	}
}
