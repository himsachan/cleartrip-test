package cleartrip.cleartrip_test.web_pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cleartrip.cleartrip_test.util.AdultDetails;
import cleartrip.cleartrip_test.util.ChildDetails;

public class ItineraryPage extends BasePage {
	
	static Logger logger = Logger.getLogger(ItineraryPage.class);
	
	protected WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="itineraryBtn")
	public WebElement continueBookingButton;
	
	@FindBy(id="username")
	public WebElement emailInput;
	
	@FindBy(id="LoginContinueBtn_1")
	public WebElement emailLoginButton;

	@FindBy(id="signinLabel")
	public WebElement enablePassword;
	
	@FindBy(id="signinLabel")
	public WebElement passwordInput;

	@FindBy(id="travellerBtn")
	public WebElement travellerButton;
	
	@FindBy(id="mobileNumber")
	public WebElement mobileNoInput;
	
	@FindBy(id="paymentSubmit")
	public WebElement paymentSubmitButton;
	
	@FindBy(xpath=".//div[@class='itinerary clearFix onwBlock']//div[@class='airlineName']/span[@class='name']")
	public WebElement depairlineName;
	
	@FindBy(xpath=".//div[@class='itinerary clearFix retBlock']//div[@class='airlineName']/span[@class='name']")
	public WebElement retairlineName;
	
	@FindBy(xpath=".//strong[@class='totalFare']//span[@id='counter']")
	public WebElement totalFareBeforeTax;
	
	@FindBy(xpath=".//div[@id='priceButton']//span[@id='payBlockTotal']//strong[@class='totalFare']//span[@id='counter']")
	public WebElement totalFareIncludingConvenienceFee;
	
	private String xpathAdultTitle = ".//select[@name='AdultTitle<item_index>']";
	private String xpathAdultFirstName = ".//input[@name='AdultFname<item_index>']";
	private String xpathAdultLastName = ".//input[@name='AdultLname<item_index>']";
	
	private String xpathAdultDobDay = ".//select[@name='AdultDobDay<item_index>']";
	private String xpathAdultDobMonth = ".//select[@name='AdultDobMonth<item_index>']";
	private String xpathAdultDobYear = ".//select[@name='AdultDobYear<item_index>']";
	
	private String xpathAdultNationality =".//input[@data-idfield='adultNationality<item_index>']";
	private String xpathChildTitle =".//select[@name='ChildTitle<item_index>']";
	private String xpathChildFirstName =".//input[@name='ChildFname<item_index>']";
	private String xpathChildLastName =".//input[@name='ChildLname<item_index>']";
	
	private String xpathChildDobDay =".//select[@name='ChildDobDay<item_index>']";
	private String xpathChildDobMonth =".//select[@name='ChildDobMonth<item_index>']";
	private String xpathChildDobYear =".//select[@name='ChildDobYear<item_index>']";
	
	private String xpathChildNationality = ".//input[@data-idfield='childNationality<item_index>']";
	
	public String totalfare;
	
	static boolean isAirAsiaFlight;
	
	public ItineraryPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}
	
	public String getTotalFareExcludingTax() {
		return totalFareBeforeTax.getText();
	}
	
	public boolean continueBooking() throws InterruptedException {
		try {
			waitForUrl("itinerary", driver);
			//Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOf(depairlineName));
			if(depairlineName.getText().equals("Air Asia")||retairlineName.getText().equals("Air Asia"))
				isAirAsiaFlight =true;
			logger.info("departure airline vendor :"+depairlineName.getText());
			logger.info("total fare excluding tax  :"+getTotalFareExcludingTax());
			// scroll down for visiblity of continue booking button
			scrollDown(driver,"0","1000");
			continueBookingButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(emailLoginButton));
			return true;
		}
		catch(Exception e) {
			logger.error("Exception in continue booking in itinery page :"+e.getMessage() );
			return false;
		}
	}
	
	public boolean login(String email,  String Password) {
		try {
			emailInput.sendKeys(email);
			Thread.sleep(100);
			//enablePassword.click();
			//passwordInput.sendKeys(Password);
			emailLoginButton.click();
			//wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}
		catch(Exception e)
		{
			logger.error("Error in login");
			return false;
		}
	}
	
	public boolean fillTravellerDetails(List<AdultDetails> adults, List<ChildDetails> childs, String mobile)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(travellerButton));
			int travellerCount=0;
			for(int i =0; i<adults.size(); i++) {
				xpathAdultTitle = xpathAdultTitle.replace("<item_index>", String.valueOf(i+1));
				Select select = new Select(driver.findElement(By.xpath(xpathAdultTitle)));
				select.selectByValue(adults.get(i).getTitle());
				xpathAdultFirstName = xpathAdultFirstName.replace("<item_index>", String.valueOf(i+1));
				xpathAdultLastName=xpathAdultLastName.replace("<item_index>", String.valueOf(i+1));
				Thread.sleep(100);
				driver.findElement(By.xpath(xpathAdultFirstName)).sendKeys(adults.get(i).getfirstName());
				Thread.sleep(100);
				driver.findElement(By.xpath(xpathAdultLastName)).sendKeys(adults.get(i).getLastName());
				
				travellerCount++;
				if(isAirAsiaFlight){
					
					int date = adults.get(i).getDob().getDayOfMonth();
				    int month = adults.get(i).getDob().getMonthValue();
				    int year = adults.get(i).getDob().getYear();
				    
				    xpathAdultDobDay = xpathAdultDobDay.replace("<item_index>", String.valueOf(i+1));
				    xpathAdultDobMonth = xpathAdultDobMonth.replace("<item_index>", String.valueOf(i+1));
				    xpathAdultDobYear = xpathAdultDobYear.replace("<item_index>", String.valueOf(i+1));
				    select = new Select(driver.findElement(By.xpath(xpathAdultDobDay)));
				    select.selectByValue(String.valueOf(date));
				    select = new Select(driver.findElement(By.xpath(xpathAdultDobMonth)));
				    select.selectByIndex(month);
				    select = new Select(driver.findElement(By.xpath(xpathAdultDobYear)));
				    select.selectByValue(String.valueOf(year));
				    xpathAdultNationality = xpathAdultNationality.replace("<item_index>", String.valueOf(i+1));
				    driver.findElement(By.xpath(xpathAdultNationality)).sendKeys(adults.get(i).getNationality());
				    selectOptionWithText("India", driver,String.valueOf(travellerCount));
				}
				logger.info("aduls details filled successfully");
			}
			for(int i =0; i<childs.size(); i++) {
				travellerCount++;
				xpathChildTitle = xpathChildTitle.replace("<item_index>", String.valueOf(i+1));
				Select select = new Select(driver.findElement(By.xpath(xpathChildTitle)));
				select.selectByValue(childs.get(i).getTitle());
				Thread.sleep(100);
				driver.findElement(By.xpath(xpathChildFirstName.replace("<item_index>", String.valueOf(i+1)))).sendKeys(childs.get(i).getfirstName());
				Thread.sleep(100);
				driver.findElement(By.xpath(xpathChildLastName.replace("<item_index>", String.valueOf(i+1)))).sendKeys(childs.get(i).getLastName());
			    int date = childs.get(i).getDob().getDayOfMonth();
			    int month = childs.get(i).getDob().getMonthValue();
			    int year = childs.get(i).getDob().getYear();
			    
			    xpathChildDobDay = xpathChildDobDay.replace("<item_index>", String.valueOf(i+1));
			    xpathChildDobMonth = xpathChildDobMonth.replace("<item_index>", String.valueOf(i+1));
			    xpathChildDobYear = xpathChildDobYear.replace("<item_index>", String.valueOf(i+1));
			    select = new Select(driver.findElement(By.xpath(xpathChildDobDay)));
			    select.selectByValue(String.valueOf(date));
			    select = new Select(driver.findElement(By.xpath(xpathChildDobMonth)));
			    select.selectByIndex(month);
			    select = new Select(driver.findElement(By.xpath(xpathChildDobYear)));
			    select.selectByValue(String.valueOf(year));
			    
			    logger.info("date :"+date +"month :"+month+"year :"+year);
			    
			    if(isAirAsiaFlight) {
			    	driver.findElement(By.xpath(xpathChildNationality.replace("<item_index>", String.valueOf(i+1)))).sendKeys(childs.get(i).getNationality());
			    	selectOptionWithText("India", driver,String.valueOf(travellerCount));
			    }
			    logger.info("child details filled successfully");
			}
			mobileNoInput.sendKeys(mobile);
			travellerButton.click();
			Thread.sleep(100);
			wait.until(ExpectedConditions.visibilityOf(paymentSubmitButton));
			totalfare = totalFareIncludingConvenienceFee.getText();
			logger.info("total fare :"+totalfare);
			return true;
		}
		catch(Exception e) {
			logger.error("Error in filling travellers details");
			return false;
		}
		
	}
	
	public String getTripFare() {
		return this.totalfare;
	}

}
