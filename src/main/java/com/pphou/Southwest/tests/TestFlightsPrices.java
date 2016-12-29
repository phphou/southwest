package com.pphou.Southwest.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pphou.Southwest.pageObjects.FlightsPage;
import com.pphou.Southwest.pageObjects.FlightsResultsPage;
import com.pphou.Southwest.utilities.Screenshots;

public class TestFlightsPrices {
	WebDriver driver;
	FlightsPage flightsPage;
	FlightsResultsPage flightsResultsPage;
	int departPrice, returnPrice;
	int budget = 230;	// Set budget
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/phillipphou/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.southwest.com/flight/?int=HOME-BOOKING-WIDGET-ADVANCED-AIR");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*
	 * This test will pass if there is a cheap round trip flight that meets the budget.
	 */
	@Test
	public void getCheapPrices() throws IOException, AWTException {
		// Create FlightsPage object
		flightsPage = new FlightsPage(driver);
		
		// Fill out itinerary
		flightsPage.selectDepartAirport("OAK");
		flightsPage.selectArrivalAirport("SEA");
		flightsPage.selectDepartDate("02/09/2017");
		flightsPage.selectReturnDate("02/11/2017");
		flightsPage.selectDepartTime("AFTER_6PM");
		flightsPage.selectReturnTime("AFTER_6PM");
		
		// Close calendar popup to allow search button to be clicked 
		Actions actions = new Actions(driver);
		Robot robot = new Robot();
		robot.mouseMove(1, 1);
		actions.click().build().perform();
		
		flightsResultsPage = flightsPage.clickSearch();
		
		departPrice = flightsResultsPage.findCheapestDepartFlight();
		returnPrice = flightsResultsPage.findCheapestReturnFlight();
		
		// Perform test for cheap tickets against desired budget. Create screenshot if test passes.
		if (departPrice + returnPrice < budget) {
			Screenshots.takeScreenshot(driver, "oak-sea-flight-prices");
		} else {
			Assert.fail("The roundtrip cost is: $" + (departPrice + returnPrice) + "\n" +
					"Your budget is : $" + budget);
		}
	}
	
	@AfterTest
	public void tearDown() {
		//driver.close();
	}
	
}
