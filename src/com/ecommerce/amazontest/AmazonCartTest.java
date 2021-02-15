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

public class AmazonCartTest {
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

	public void signin() throws InterruptedException {

		driver.findElement(By.id("nav-link-accountList")).click();

		driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("archanakits@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();

		driver.findElement(By.id("ap_password")).sendKeys("password");
		Thread.sleep(1000);
		driver.findElement(By.id("signInSubmit")).click();

		Thread.sleep(20000);

	}
	
	@Test
	public void verifyCart() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		signin();
		// locate search tab
		driver.findElement(By.id("nav-cart-count-container")).click();
		

		// click on link
		
		Thread.sleep(4000);
		takeScreenshots("CartOutput");

		String actualTitle = driver.getTitle();
		String expectedTitle = "Amazon.in Shopping Cart";
		
		Assert.assertEquals(expectedTitle, actualTitle);
		
	}
}