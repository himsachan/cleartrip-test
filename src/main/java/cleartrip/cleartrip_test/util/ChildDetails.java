package cleartrip.cleartrip_test.util;

import java.time.LocalDate;

public class ChildDetails {
	public String firstName;
	public String lastName;
    public String title;
    public LocalDate dob;
    public String nationality;
    public ChildDetails(String firstName, String lastName, String title, LocalDate dob, String nationality) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.title = title;
    	this.dob = dob;
    	this.nationality=nationality;
    }
    public String getfirstName() {
    	return this.firstName;
    }
    
    public String getLastName() {
    	return this.lastName;
    }
    
    public String getTitle() {
    	return this.title;
    }
    
    public LocalDate getDob() {
    	return this.dob;
    }
    
    public String getNationality() {
    	return this.nationality;
    }
    

}
