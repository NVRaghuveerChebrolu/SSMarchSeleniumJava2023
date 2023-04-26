package com.Selenium;

import org.testng.annotations.Test;

import com.pages.MouseOperationsPOM;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateMouseOperations extends Library {
	@Test(priority = 0)
	public void ValidateRightCickOperation() {
		System.out.println("inside ValidateRightCickOperation");
		driver.get(objProperties.getProperty("mouseOpeartionRightClick"));
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Actions objActions = new Actions(driver);
		MouseOperationsPOM objMoueOperations = new MouseOperationsPOM(driver);
		objActions.contextClick(objMoueOperations.rightClickButton).build().perform();
		objActions.click(objMoueOperations.DeleteOption).build().perform();
		Alert objALert =driver.switchTo().alert();
		String AlertText = objALert.getText();
		Assert.assertEquals(AlertText, objProperties.getProperty("mouseOpeartionRightclick_DeleteAlertText"), "Alert Message is not validated");
		objALert.accept();
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
