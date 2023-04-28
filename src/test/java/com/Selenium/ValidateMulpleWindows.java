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
			if(title.equalsIgnoreCase(objProperties.getProperty("newBrowserWindowTitle"))) {
				driver.findElement(MultipleWindowsPage.menuOfNewBrowserWindow).click();
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrllBy(0,300)");
				driver.findElement(MultipleWindowsPage.AboutMeInNewBrowserWindow).click();
			}
		}
		
		
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
