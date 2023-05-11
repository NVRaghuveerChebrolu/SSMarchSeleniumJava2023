package com.Selenium;

import org.testng.annotations.Test;

import com.pages.FramesPage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateFramesWithPOM extends Library {
  @Test(priority=0)
  public void ValidateFramesPageLoadedSuccessfully() {
	  driver.get(objProperties.getProperty("FramesURL"));
	  PageLoadTimeOut();
	  String Title = driver.getTitle();
	  Assert.assertEquals(Title, "Frames");
	  
  }
  
  @Test(priority=1)
  public void ValidateSingleFrame() { 
	  FramesPage obj = new FramesPage();
	  obj.SetSingleFrame("singleframe");
	  driver.switchTo().frame(obj.getSingleFrame());
	  driver.findElement(FramesPage.TextBox).sendKeys(objProperties.getProperty("SingleFrameText"));
	  driver.switchTo().defaultContent();
	  driver.findElement(FramesPage.FrameWithInFramebutton).click();
	  
  }
  
  @Test(priority=2)
  public void ValidateIframeWithInIframe() {
	  WebElement OuterFrame = driver.findElement(FramesPage.OuterFrame);
	  driver.switchTo().frame(OuterFrame);
	  WebElement InnerFrame = driver.findElement(FramesPage.InnerFrame);
	  driver.switchTo().frame(InnerFrame);
	  driver.findElement(FramesPage.TextBox).sendKeys(objProperties.getProperty("DoubleFrameText"));
	  driver.switchTo().defaultContent();
	  
	
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
