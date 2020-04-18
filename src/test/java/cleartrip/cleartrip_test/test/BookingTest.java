package cleartrip.cleartrip_test.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cleartrip.cleartrip_test.web_pages.FlightSearchPage;
import cleartrip.cleartrip_test.web_pages.HomePage;
import cleartrip.cleartrip_test.web_pages.ItineraryPage;
import util.Utils;

public class BookingTest extends BaseTest {
	
	
	HomePage homePage;
	FlightSearchPage flightSearchPage;
	ItineraryPage itineraryPage;
	Utils utils;
	
	@BeforeClass
	public void beforeClass() {
		homePage = new HomePage(driver);
		flightSearchPage = new FlightSearchPage(driver);
		itineraryPage = new ItineraryPage(driver);
		utils = new Utils();
	}
	
	@Test(description="Verify return flight booking flow automation ")
	public void testFlightBookingFlow() throws InterruptedException
	{
		Assert.assertTrue(homePage.loadUrl(),"Error in loading base page url");
		Assert.assertTrue(flightSearchPage.fillFlightSearchDetails(Constant.departureCity, Constant.destinationCity,Constant.getDepartureDate(),Constant.getArrivalDate(),1,1),"Error in loading base page url");
		Assert.assertTrue(flightSearchPage.selectAndBookFlight(),"Error in  selecting searched flight options");
		Assert.assertTrue(itineraryPage.continueBooking(), "Error in proceeding booking flow in itinery page");
		Assert.assertTrue(itineraryPage.login(Constant.email, "test"), "Error in  login");
		Assert.assertTrue(itineraryPage.fillTravellerDetails(Constant.getDepartureAdults(), Constant.getDepartureChilds(), Constant.MobileNo), "Error in filling travellers details in itinery page");
	}
	
	@AfterTest
	public void afterTest() {
		utils.write(itineraryPage.getTripFare());
	}

}
