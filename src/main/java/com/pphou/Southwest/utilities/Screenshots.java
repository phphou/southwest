package com.pphou.Southwest.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	public static void takeScreenshot(WebDriver driver, String filename) throws IOException {
		filename = filename + ".png";
		String directory = "./screenshots/";	// This folder is located in Southwest project folder
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(directory + filename));
	}
}