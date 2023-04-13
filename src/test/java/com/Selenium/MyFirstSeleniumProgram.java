package com.Selenium;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstSeleniumProgram {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException  {
		try {
		System.out.println("inside main");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.borland.com/gmopost/");
		driver.manage().window().maximize();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		String TitleOfHomePage = driver.getTitle();
		System.out.println("TitleOfHomePage:"+TitleOfHomePage);
		driver.findElement(By.name("bSubmit")).click();
		driver.findElement(By.name("QTY_GLASSES")).clear();
		driver.findElement(By.name("QTY_GLASSES")).sendKeys("3");
		String TitleOfListPage = driver.getTitle();
		System.out.println("TitleOfListPage:"+TitleOfListPage);
		
		//driver.findElement(By.linkText("Hiking Boots")).click();
		
		driver.findElement(By.name("bSubmt")).click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<WebElement> AllElements = driver.findElements(By.name("bSubmt"));
		System.out.println("AllElements:"+AllElements);
		
		List<WebElement> AllProducLinks = driver.findElements(By.xpath("//a"));
		System.out.println("AllProducLinks:"+AllProducLinks);
		
		//driver.findElement(By.xpath("//table[@border='1']")
		String TitleOfCurrentPage = driver.getTitle();
		System.out.println("TitleOfCurrentPage:"+TitleOfCurrentPage);
		//driver.close();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);
		//driver.navigate().refresh();
		
		driver.findElement(By.name("bSubmit")).click();
		String TitleOfPlaceOrderPage = driver.getTitle();
		System.out.println("TitleOfPlaceOrderPage:"+TitleOfPlaceOrderPage);
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
		//assert.assertEquals(CalculatedValue, Totalprice);
	}

}
