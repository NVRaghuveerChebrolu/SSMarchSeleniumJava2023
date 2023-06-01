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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateMouseOperations extends Library {
	
	@Test(priority = 0)
	public void ValidateRightCickOperation() {
		System.out.println("inside ValidateRightCickOperation");
		ExtTest=ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.get(objProperties.getProperty("mouseOpeartionRightClick"));
		//overriding the PageLoadTimeOut
		PageLoadTimeOut();
		Actions objActions = new Actions(driver);
		MouseOperationsPOM objMoueOperations = new MouseOperationsPOM(driver);
		objActions.contextClick(objMoueOperations.rightClickButton).build().perform();
		objActions.click(objMoueOperations.DeleteOption).build().perform();
		Alert objALert =driver.switchTo().alert();
		String AlertText = objALert.getText();
		Assert.assertEquals(AlertText, objProperties.getProperty("mouseOpeartionRightclick_DeleteAlertText"), "Alert Message is not validated");
		objALert.accept();
	}
	
	@Test(priority=1)
	public void ValidateDoubleClickOperation() {
		System.out.println("inisde ValidateDoubleClickOperation");
		ExtTest=ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(objProperties.getProperty("mouseOpeartionDoubleClick"));
		//over loading 
		PageLoadTimeOut(45);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,500)");//to scroll vertically down by 500 pixels
		//js.executeScript("window.scrollBy(0,-500)");//to scroll vertically up by 500 pixels
		//js.executeScript("window.scrollBy(300,0)");//to scroll horizontally right side by 300 pixels
		//js.executeScript("window.scrollBy(-300,0)");//to scroll horizontally left side by 300 pixels
		//WebElement element = driver.findElement(By.xpath("//iframe"));
	
		MouseOperationsPOM objMoueOperations = new MouseOperationsPOM(driver);
		js.executeScript("arguments[0].scrollIntoView(true);",objMoueOperations.Frame);
		driver.switchTo().frame(objMoueOperations.Frame);
		//Actions objAction = new Actions(driver);
		//objAction.doubleClick(obj.DoubleClickBox).build().perform();
		DoubleClick(objMoueOperations.DoubleClickBox);
		
	}
	
	@Test(priority=2)
	public void ValidateDragAndDrop() {
		System.out.println("inside ValidateDragAndDrop");
		ExtTest=ExtReports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.get(objProperties.getProperty("mouseOperationDragAndDrop"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");//to scroll vertically down by 500 pixels
		MouseOperationsPOM objMoueOperations = new MouseOperationsPOM(driver);
		driver.switchTo().frame(objMoueOperations.Frame);
		Actions ObjActions = new Actions(driver);
		//ObjActions.dragAndDrop(objMoueOperations.draggable, objMoueOperations.droppable).build().perform();
		ObjActions.clickAndHold(objMoueOperations.draggable);
		ObjActions.moveToElement(objMoueOperations.droppable).build().perform();
	}
	
	@Override
	public void PageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("inside afterMethod");
		try {
			UpdatingResultInExtentReport(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		StartExtentReport();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
		FlushReport();
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
