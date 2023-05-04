package com.Selenium;

import org.testng.annotations.Test;

import com.pages.MouseOperationsPOM;
import com.pages.MultipleWindowsPage;
import com.pages.WebTablePage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
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

public class ValidateWebTable extends Library {
	
	@Test
	public void ValidateWebTable() {
		System.out.println("inside ValidateWebTable");
		driver.get(objProperties.getProperty("WebTableURL"));
		PageLoadTimeOut();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,600)");
		WebElement webTableElement = driver.findElement(WebTablePage.WebTable);
		js.executeScript("arguments[0].scrollIntoView(true);",webTableElement);
		List<WebElement> AllLastNames = driver.findElements(WebTablePage.AllLastNames);
		for(int Row=1 ; Row<=AllLastNames.size()-1;Row++) {
			String EachLastName = AllLastNames.get(Row).getText();
			System.out.println("EachLastName:"+EachLastName);
			if(EachLastName.equalsIgnoreCase(objProperties.getProperty("WebTableLastName"))) {
				Row=Row+1;
				String FirstName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[2]")).getText();
				String LastName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[3]")).getText();
				String Position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[4]")).getText();
				String Office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[5]")).getText();
				String StarteDate = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[6]")).getText();
				String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+Row+"]/td[7]")).getText();
				System.out.println("FirstName:"+FirstName);
				System.out.println("LastName:"+LastName);
				System.out.println("Position:"+Position);
				System.out.println("Office:"+Office);
				System.out.println("StarteDate:"+StarteDate);
				System.out.println("Salary:"+Salary);
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
