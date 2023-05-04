package com.Selenium;

import org.testng.annotations.Test;

import com.pages.CalenderAndDropDownPage;
import com.pages.MouseOperationsPOM;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateCalenderAndDropDowns extends Library {
	
	
	@Test
	public void ValidateDropDownAndCalender() throws InterruptedException {
		driver.get(objProperties.getProperty("Calender"));
		PageLoadTimeOut();
		Thread.sleep(15000);
		Set<String> AllWindows = driver.getWindowHandles();
		for(String IndividualWindow : AllWindows) {
			driver.switchTo().window(IndividualWindow);
			String Title = driver.getTitle();
			if(Title.equalsIgnoreCase(objProperties.getProperty("TitleOfCalenderPage"))) {
				driver.navigate().refresh();
				PageLoadTimeOut();
				CalenderAndDropDownPage obj = new CalenderAndDropDownPage(driver);
				obj.ClickOndateOfBirthInput();
				Select ObjSelect = new Select(obj.DOB_Month);
				//ObjSelect.selectByIndex(10);
				//ObjSelect.selectByValue(Title);
				ObjSelect.selectByVisibleText(objProperties.getProperty("MonthOfCalender"));
				Select ObjSelectYear = new Select(obj.DOB_Year);
				ObjSelectYear.selectByValue(objProperties.getProperty("YearOfCalender"));
				
				List<WebElement> AllDays = obj.DOB_Day;
				for(int i=0 ;i <=AllDays.size()-1;i++) {
					String IndividualDay = AllDays.get(i).getText();
					System.out.println("IndividualDay:"+IndividualDay);
					if(IndividualDay.equalsIgnoreCase(objProperties.getProperty("DayOfCalender"))) {
						AllDays.get(i).click();
						break;
					}
				}
				break;
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
