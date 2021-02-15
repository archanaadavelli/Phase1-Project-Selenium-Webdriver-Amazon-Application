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

public class AmazonSigninTest {
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
	public void verifySignin() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);

		// locate search tab
		driver.findElement(By.id("nav-link-accountList")).click();
		
		driver.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("archanakits@gmail.com");
		takeScreenshots("signinUserNameInput");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		
		driver.findElement(By.id("ap_password")).sendKeys("password");
		takeScreenshots("signinPasswordInput");
		Thread.sleep(2000);
		driver.findElement(By.id("signInSubmit")).click();
		
		Thread.sleep(20000);
	
		String actualText = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();	
		String expectedText = "Hello, Archana";
		takeScreenshots("signinOutput");
		Assert.assertEquals(expectedText, actualText);
		
	}
}