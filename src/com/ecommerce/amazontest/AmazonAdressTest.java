package com.ecommerce.amazontest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class AmazonAdressTest {
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
	public void verifyAddress() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);

		driver.findElement(By.id("nav-global-location-popover-link")).click();

		Thread.sleep(5000);
		takeScreenshots("AdressMOdelInput");
		String mainWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		Iterator<String> windowIterator = windows.iterator();

		while (windowIterator.hasNext()) {
			String childWindow = windowIterator.next();
			driver.switchTo().window(childWindow);
			driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdateInput\"]")).sendKeys("500090");
			Thread.sleep(1000);
			takeScreenshots("AdressPINInput");
			driver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input")).click();

		}
		driver.switchTo().window(mainWindow);
		Thread.sleep(2000);

		takeScreenshots("AdressPINOutput");

		String actualText = driver.findElement(By.xpath("//*[@id=\"glow-ingress-line2\"]")).getText();

		if (actualText.contains("Hyderabad")) {
			System.out.println("Test case Passed :)");
		} else {
			System.out.println("Test case Failed :(");
		}
	}	
}