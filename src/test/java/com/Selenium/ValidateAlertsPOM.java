package com.Selenium;

import org.testng.annotations.Test;

import com.pages.AlertsPage;
import com.utility.Library;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ValidateAlertsPOM extends Library{
	

	@Test(priority=-2)
	public void ValidateAlertsPageLoadedSuccessfully() {
		driver.get(objProperties.getProperty("ALertsUrl"));
		String TitleOfAlertsPage = driver.getTitle();
		Assert.assertEquals(TitleOfAlertsPage, objProperties.getProperty("AlertPageTitle"));

	}
	
	@Test(priority=-1)
	public void ValidateNormalALert() throws InterruptedException {
		System.out.println("inside ValidateNormalALert");
		//driver.findElement(By.id("alertButton")).click();
		AlertsPage.ClickNormaAlert();
		Alert NormalAlert = driver.switchTo().alert();
		String TextOfNormalAlert = NormalAlert.getText();
		System.out.println("TextOfNormalAlert:"+TextOfNormalAlert);
		Assert.assertEquals(TextOfNormalAlert, "You clicked a button");
		Thread.sleep(3000);
		NormalAlert.accept();
	}
	
	
	@Test()
	public void ValidateTimerAlert() throws InterruptedException {
		System.out.println("inside ValidateTimerAlert");
		//driver.findElement(By.id("timerAlertButton")).click();
		AlertsPage.ClickTimerAlert();
		//Explicit Wait : Explicit Wait available in Selenium which is Applicable for one WebElement . Wait until expected Condition 
		//is satisfied
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert TimerAlert = driver.switchTo().alert();
		String TextOfTimerAlert = TimerAlert.getText();
		System.out.println("TextOfTimerAlert:"+TextOfTimerAlert);
		Assert.assertEquals(TextOfTimerAlert, "This alert appeared after 5 seconds");
		Thread.sleep(3000);
		TimerAlert.accept();
		
	}
	
	@Test(priority=1)
	public void ValidateConformBoxAlert() {
		System.out.println("inside ValidateConformBoxAlert");
		driver.findElement(By.id("confirmButton")).click();
		Alert ConfirmBoxAlert = driver.switchTo().alert();
		String ConfirmBoxAlertText = ConfirmBoxAlert.getText();
		System.out.println("ConfirmBoxAlertText:"+ConfirmBoxAlertText);
		Assert.assertEquals(ConfirmBoxAlertText, "Do you confirm action?");
		ConfirmBoxAlert.dismiss();
		String ConfirmBoxResult = driver.findElement(By.id("confirmResult")).getText();
		System.out.println("ConfirmBoxResult:"+ConfirmBoxResult);
		Assert.assertEquals(ConfirmBoxResult, "You selected Cancel");
	}
	
	@Test(priority=2)
	public void ValidatePromptBoxAlert() {
		System.out.println("inside ValidatePromptBoxAlert");
		driver.findElement(By.id("promtButton")).click();
		Alert PromptBoxAlert = driver.switchTo().alert();
		String PromptBoxAlertText = PromptBoxAlert.getText();
		System.out.println("PromptBoxAlertText:"+PromptBoxAlertText);
		Assert.assertEquals(PromptBoxAlertText, "Please enter your name");
		PromptBoxAlert.sendKeys("hi how are you?");
		PromptBoxAlert.accept();
		String PromptResult = driver.findElement(By.id("promptResult")).getText();
		System.out.println("PromptResult:"+PromptResult);
		Assert.assertEquals(PromptResult, "You entered hi how are you?");
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
