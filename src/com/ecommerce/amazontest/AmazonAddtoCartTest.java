package com.ecommerce.amazontest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class AmazonAddtoCartTest {
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

	public void searchTab(String searchText) throws InterruptedException {

		WebElement searchTab = driver.findElement(By.id("twotabsearchtextbox"));

		// click on link
		searchTab.sendKeys(searchText);
		driver.findElement(By.id("nav-search-submit-button")).click();
		Thread.sleep(2000);

	}

	@Test
	public void verifyAddCart() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		signin();
		// searchTab("Alexa Speakers");
		searchTab("alexa speakers");
		Thread.sleep(4000);
		takeScreenshots("SearchAlexaSpeakers");

		// driver.findElement(By.cssSelector("a[href*='New-Apple-iPhone-12-128GB']")).click();
		driver.findElement(By.xpath(
				"/html/body/div[1]/div[2]/div[1]/div[2]/div/span[3]/div[2]/div[1]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a"))
				.click();

		String mainWindow = driver.getWindowHandle();
		String childWindow = driver.getWindowHandle();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		mainWindow = tabs.get(0);
		childWindow = tabs.get(1);

		driver.switchTo().window(childWindow);
		Thread.sleep(3000);
		takeScreenshots("AlexaSpeakerSelected");
		driver.findElement(By.cssSelector("input[title='Add to Shopping Cart']")).click();
		Thread.sleep(1000);
		takeScreenshots("AlexaSpeakerAddToCart");
		driver.switchTo().window(childWindow).close();

		driver.switchTo().window(mainWindow);

		Thread.sleep(3000);
		driver.findElement(By.id("nav-cart")).click();
		takeScreenshots("AlexaSpeakerCheckCart");

		Thread.sleep(4000);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Amazon.in Shopping Cart";

		Assert.assertEquals(expectedTitle, actualTitle);

	}
}