package com.pphou.Southwest.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightsResultsPage {
	private WebDriver driver;
	
	// Page elements
	private By departFlightsTable = By.id("faresOutbound");
	private By returnFlightsTable = By.id("faresReturn");
	
	// Page Object constructor
	public FlightsResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public int findCheapestDepartFlight() {
		// Convert price listings from WebElement to string to integer to perform price comparison calculation
		List<Integer> iPricesList = new ArrayList<Integer>();
		List<String> sPricesList = new ArrayList<String>();
		
		// Store departure prices into a collection
		List<WebElement> wPricesList = driver.findElements(By.xpath("//*[@id='faresOutbound']//label[@class='product_price']"));
		
		System.out.println("Returning fare prices: ");
		
		// Perform type conversions for price listings
		for (WebElement wPrice : wPricesList) {
			System.out.println(wPrice.getText());
			sPricesList.add(wPrice.getText().substring(1));
		}
		for (String sPrice : sPricesList) {
			int i = Integer.parseInt(sPrice);
			iPricesList.add(i);
		}
		
		// Determine lowest departure price
		int minPrice = iPricesList.get(0);
		for (int price : iPricesList) {
			if (price < minPrice) minPrice = price;
		}
		
		System.out.println("Cheapest return flight costs: $" + minPrice);
		return minPrice;
	}
	
	public int findCheapestReturnFlight() {
		// Convert price listings from WebElement to string to integer to perform price comparison calculation
		List<Integer> iPricesList = new ArrayList<Integer>();
		List<String> sPricesList = new ArrayList<String>();
		
		// Store return prices into a collection
		List<WebElement> wPricesList = driver.findElements(By.xpath("//*[@id='faresReturn']//label[@class='product_price']"));
		
		System.out.println("Returning fare prices: ");
		
		// Perform type conversions for price listings
		for (WebElement wPrice : wPricesList) {
			System.out.println(wPrice.getText());
			sPricesList.add(wPrice.getText().substring(1));
		}
		for (String sPrice : sPricesList) {
			int i = Integer.parseInt(sPrice);
			iPricesList.add(i);
		}
		
		// Determine lowest return price
		int minPrice = iPricesList.get(0);
		for (int price : iPricesList) {
			if (price < minPrice) minPrice = price;
		}
		
		System.out.println("Cheapest departure flight costs: $" + minPrice);
		return minPrice;
	}
}
