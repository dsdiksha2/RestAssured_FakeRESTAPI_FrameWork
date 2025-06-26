package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;

import Utility.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes_Endpoints.routes;

public class BaseClass {


	ConfigReader config ;

	//For logging
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;


	// this method will execute before all the method of a class
	@BeforeClass
	public void SetUp() throws FileNotFoundException {

		//Caling base-URL from routes class and storing it in static rest assured base URI 
		RestAssured.baseURI= routes.BASE_URL;

		//Creating object for config reader file to read config data 
		config = new ConfigReader();


		// Setup filters for logging
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
		PrintStream log = new PrintStream(fos, true);

		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);

		RestAssured.filters(requestLoggingFilter, responseLoggingFilter);



	}


	// Helper method to check if a list is sorted in descending order

	boolean isSortedDesceding(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)<list.get(i+1))           //element at 0 should be less than the next element it is not in desc
			{
				return false;	
			}
		}
		return true;
	}

	// Helper method to check if a list is sorted in asceding order

	boolean isSortedAsceding(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)>list.get(i+1))
			{
				return false;	
			}
		}
		return true;
	}



//////////////////Helper method to check dates fall within the specified range

	//creating variable for formatting all date in same format
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {

		//In below line formatter will remove the time stamp from the string type date,  then localdate.parse will convert the string in date format and then storing it in start/end variable
		//not using substring because in config file we have not specified the time stamp but on list we are getting data from response which have time stamp
		LocalDate start = LocalDate.parse(startDate, FORMATTER);

		LocalDate end = LocalDate.parse(endDate, FORMATTER);

		for (String dateTime : cartDates) 
		{
			//same above concept is implemented for cart dates list, substring will consider the position from 0th to 10th position
			LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), FORMATTER);
			if (cartDate.isBefore(start) || cartDate.isAfter(end)) {
				return false; // Immediately return false if any cart date is out of range
			}
		}
		return true; // All dates are within range
	}


}
