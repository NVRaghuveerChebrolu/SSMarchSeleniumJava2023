package com.Selenium;

import org.testng.annotations.Test;

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

public class ValidateFrames extends Library {
  @Test(priority=0)
  public void ValidateFramesPageLoadedSuccessfully() {
	  driver.get(objProperties.getProperty("FramesURL"));
	  PageLoadTimeOut();
	  String Title = driver.getTitle();
	  Assert.assertEquals(Title, "Frames");
	  
  }
  
  @Test(priority=1)
  public void ValidateSingleFrame() {
	  driver.switchTo().frame("singleframe");
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("I am inside singleframe");
	  driver.switchTo().defaultContent();
	  driver.findElement(By.xpath("//a[contains(text(),'with in an')]")).click();
	  
  }
  
  @Test(priority=2)
  public void ValidateIframeWithInIframe() {
	  WebElement OuterFrame = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
	  driver.switchTo().frame(OuterFrame);
	  WebElement InnerFrame = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
	  driver.switchTo().frame(InnerFrame);
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("TextBox inside frame with in an iframe");
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
