package com.Selenium;

import org.testng.annotations.Test;

import com.pages.MouseOperationsPOM;
import com.pages.MultipleWindowsPage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateMulpleWindows extends Library {
	
	@Test
	public void ValidateNewBrowserWindow() {
		System.out.println("inside ValidateNewBrowserWindow");
		driver.get(objProperties.getProperty("nxtgenaiacademyURL"));
		PageLoadTimeOut();
		//MultipleWindowsPage obj = new MultipleWindowsPage();
		String ParentWindow = driver.getWindowHandle();
		driver.findElement(MultipleWindowsPage.newBrowserWindowButton).click();
		Set<String> AllWindows = driver.getWindowHandles();
		for(String IndividualWindow : AllWindows) {
			driver.switchTo().window(IndividualWindow);
			String title = driver.getTitle();
			System.out.println("Title:"+title);
			String URL = driver.getCurrentUrl();
			System.out.println("URL:"+URL);
			if(title.equalsIgnoreCase(objProperties.getProperty("newBrowserWindowTitle"))) {
				WebElement element = driver.findElement(MultipleWindowsPage.menuOfNewBrowserWindow);
				WebDriverWait wait = new WebDriverWait(driver,60);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,300)");
				driver.findElement(MultipleWindowsPage.AboutMeInNewBrowserWindow).click();
				String TextOfTeckTalk = driver.findElement(MultipleWindowsPage.TechTalkInNewBrowserWindow).getText();
				Assert.assertEquals(TextOfTeckTalk, objProperties.getProperty("TechTalkInNewBrowserWindow"));
				String TechTalkURL = driver.findElement(MultipleWindowsPage.TechTalkInNewBrowserWindow).getAttribute("href");
				Assert.assertEquals(TechTalkURL, objProperties.getProperty("UrlOfTechTalkInNewBrowserWindow"));
				driver.close(); // closes the currently window that is currently open (where driver is currently Active)
				driver.quit();//quit will closes all the windows that are open and operating by webDriver.
				//break;
			}
		}
		driver.switchTo().window(ParentWindow);
		String titleOfMainWindow = driver.getTitle();
		Assert.assertEquals(titleOfMainWindow,objProperties.getProperty("nxtgenaiacademyTitle") );
		driver.close();
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		LaunchBrowser();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			ReadPropertiesFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
