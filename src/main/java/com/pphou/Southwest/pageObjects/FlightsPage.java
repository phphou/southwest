package com.pphou.Southwest.pageObjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightsPage {
	/* 
	 * This test is based on the advanced air widget flights page.
	 */
	
	private WebDriver driver;
	private static String username = "";
	private static String password = "";
	private static String url = "https://www.southwest.com/flight/?int=HOME-BOOKING-WIDGET-ADVANCED-AIR";
	
	// Page elements
	private By usernameTextField = By.id("accountNumber");
	private By passwordTextField = By.id("accountPassword");
	private By loginBtn = By.className("login-submit-button");
	private By roundTrip = By.id("roundTrip");
	private By oneWay = By.id("oneWay");
	private By depart = By.id("originAirport_displayed");
	private By arrival = By.id("destinationAirport_displayed");
	private By departDate = By.id("outboundDate");
	private By returnDate = By.id("returnDate");
	private By dollars = By.id("dollars");
	private By points = By.id("points");
	private By searchButton = By.id("submitButton");

	// Page Object constructor
	public FlightsPage(WebDriver driver) {
		this.driver = driver;
		//driver.get(url);
	}

	public void selectDepartAirport(String from) {
		driver.findElement(depart).sendKeys(from);
	}
	
	public void selectArrivalAirport(String to) {
		driver.findElement(arrival).sendKeys(to);
	}
	
	public void selectDepartDate(String depDate) {
		WebElement e = driver.findElement(departDate);
		e.clear();
		e.sendKeys(depDate);
	}
	
	public void selectReturnDate(String retDate) {
		WebElement e = driver.findElement(returnDate);
		e.clear();
		e.sendKeys(retDate);
	}
	
	public void selectDepartTime(String depTime) {
		Select departTime = new Select(driver.findElement(By.id("outboundTimeOfDay")));
		departTime.selectByValue(depTime);
	}
	
	public void selectReturnTime(String retTime) {
		Select returnTime = new Select(driver.findElement(By.id("returnTimeOfDay")));
		returnTime.selectByValue(retTime);
	}
	
	public void selectOneWay(Boolean isOneWay) {
		if (isOneWay) {
			driver.findElement(oneWay).click();
		}
	}
	
	public void selectAdults(String adults) {
		Select numAdults = new Select(driver.findElement(By.id("adultPassengerCount")));
		numAdults.selectByValue(adults);
	}
	
	public void selectSeniors(String seniors) {
		Select numSeniors = new Select(driver.findElement(By.id("seniorPassengerCount")));
		numSeniors.selectByValue(seniors);
	}

	public void showFaresInPoints(boolean inPoints) {
		if (inPoints) {
			driver.findElement(points).click();
		}
	}
	
	public boolean isLoggedIn() {
		return driver.findElement(loginBtn).isDisplayed();
	}
	
	public void logIn() {
		driver.findElement(usernameTextField).sendKeys(username);
		driver.findElement(passwordTextField).sendKeys(password);
		driver.findElement(loginBtn).click();
	}
	
	// Go to flight results page hence FlightsResultsPage return type
	public FlightsResultsPage clickSearch() {
		driver.findElement(searchButton).click();
		return new FlightsResultsPage(driver);
	}

}
