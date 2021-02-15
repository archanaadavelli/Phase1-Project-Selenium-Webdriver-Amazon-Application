package com.ecommerce.amazontest;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class AmazonDealsPageTest {
	WebDriver driver;
	TakesScreenshot scrShot;
	File DestFile;

	public void takeScreenshots(String fileName) throws IOException {
		scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		DestFile = new File("/home/archanakitsgmai/TestScreenshots/" + fileName + ".png");
		FileHandler.copy(SrcFile, DestFile);
	}

	@Before
	public void setUpDriver() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"/home/archanakitsgmai/eclipse-workspace/phase1-selenium-tests-webdrivers-01-31-2021/driver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {

		driver.close();

	}

	@Test
	public void verifyTodayDeals() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);

		driver.findElement(By.linkText("Today's Deals")).click();

		Thread.sleep(4000);
		takeScreenshots("TodayDealsOutput");

		String actualTitle = driver.getTitle();
		String expectedTitle = "Amazon.in Today's Deals: Great Savings. Every Day.";

		Assert.assertEquals(expectedTitle, actualTitle);

	}
}