package cleartrip.cleartrip_test.test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import cleartrip.cleartrip_test.util.AdultDetails;
import cleartrip.cleartrip_test.util.ChildDetails;

public class Constant {
	
	public static String departureCity = "Bengaluru";
	public static String destinationCity ="Hyderabad";
	public static String MobileNo = "9999999999";
	public static String email ="test123@gmail.com";
	
	public static String departureDate;
	public static String returnDate;

	static List<AdultDetails> departureAdults = new ArrayList<AdultDetails>();
	static List<ChildDetails> departureChilds = new ArrayList<ChildDetails>();
	
	
	public static List<AdultDetails> getDepartureAdults() {
		departureAdults.add(new AdultDetails("Raj","test","Mr",LocalDate.of(1978, 1, 18),"Indi"));
		return departureAdults;
	}
	
	public static List<ChildDetails> getDepartureChilds() {
		departureChilds.add(new ChildDetails("Pooja","test","Miss",LocalDate.of(2010,4,5),"Indi"));
		return departureChilds;
	}
	
	public static String getDepartureDate()
	{
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("E, d MMM, yyyy");
		LocalDate currentDate = LocalDate.now();
        LocalDate next2Week = currentDate.plus(2, ChronoUnit.WEEKS);
        String date =  next2Week.format(newPattern);
        return date;
	}
	
	public static String getArrivalDate()
	{
		DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("E, d MMM, yyyy");
		LocalDate currentDate = LocalDate.now();
        LocalDate next2WeekDate = currentDate.plus(2, ChronoUnit.WEEKS);
        LocalDate arrivalDate = next2WeekDate.plus(1, ChronoUnit.DAYS);
        String date =  arrivalDate.format(newPattern);
        return date;
	}
}
