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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class AmazonMobileLinkTest {
	WebDriver driver;
	TakesScreenshot scrShot;
	File DestFile;

	public void takeScreenshots(String fileName) throws IOException {
		scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		DestFile = new File("/home/archanakitsgmai/TestScreenshots/"+fileName+".png");
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
	public void verifyMobileSourcePage() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);

		// locate mobile link
		WebElement mobileLink = driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[2]"));

		// click on link
		mobileLink.click();

		String actualTitle = driver.getTitle();
		String expectedTitle = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		takeScreenshots("MobileLinkOutput");
		Thread.sleep(4000);
		Assert.assertEquals(expectedTitle, actualTitle);

	}
}