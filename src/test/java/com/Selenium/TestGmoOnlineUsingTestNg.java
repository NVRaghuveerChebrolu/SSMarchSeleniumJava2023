package com.Selenium;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestGmoOnlineUsingTestNg {
	WebDriver driver;

	@Test(priority =-2)
	public void ValidateGmoOnlineAppLoadedSuccessfully() {
		System.out.println("inside ValidateGmoOnlineAppLoadedSuccessfully");
		driver.get("https://demo.borland.com/gmopost/");
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		String TileOfGmoOnlineApp = driver.getTitle();
		Assert.assertEquals(TileOfGmoOnlineApp, "Welcome to Green Mountain Outpost");

	}
	
	@Test(priority =1)
	public void ValidateEnterGmoONlineAndOrderGlacierSunGlass() {
		System.out.println("inside ValidateEnterGmoONlineAndOrderGlacierSunGlass");
		driver.findElement(By.name("bSubmit")).click();
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "OnLine Catalog");
		driver.findElement(By.name("QTY_GLASSES")).clear();
		driver.findElement(By.name("QTY_GLASSES")).sendKeys("3");
		driver.findElement(By.name("bSubmit")).click();
		String TitleOfPlaceOrder = driver.getTitle();
		Assert.assertEquals(TitleOfPlaceOrder, "Place Order");
		
	}
	
	@Test(priority=2)
	public void ValidatePriceCalculationOfSunGlasses() {
		System.out.println("inside ValidatePriceCalculationOfSunGlasses");
		String TextOfUnitPriceFromApp = driver.findElement(By.xpath("//table[@border='1']/tbody/tr[2]/td[4]")).getText();
		System.out.println("TextOfUnitPriceFromApp:"+TextOfUnitPriceFromApp);
		String UnitPrice = TextOfUnitPriceFromApp.substring(2).trim();
		System.out.println("UnitPrice:"+UnitPrice);
		float CalculatedValue = Float.parseFloat(UnitPrice) * 3;
		System.out.println("CalculatedValue:"+CalculatedValue);
		String TotalPrineFromApp  = driver.findElement(By.xpath("//table[@border='1']/tbody/tr[2]/td[5]")).getText();
		String TotalPrice = TotalPrineFromApp.substring(2).trim();
		float Totalprice =Float.parseFloat(TotalPrice);
		System.out.println("Totalprice:"+Totalprice);
		Assert.assertEquals(CalculatedValue, Totalprice);
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
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
