package com.Selenium;

import org.testng.annotations.Test;

import com.pages.LinksPage;
import com.pages.MouseOperationsPOM;
import com.pages.MultipleWindowsPage;
import com.pages.WebTablePage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

//using single inheritance 
public class ValidateLinks extends Library {
	
	@Test
	public void ValidateWLinksinToolsQA() throws IOException {
		System.out.println("inside ValidateWLinksinToolsQA");
		driver.get(objProperties.getProperty("BrokenlinksImages"));
		//using over riding which is also called as Run Time Polymorphism
		PageLoadTimeOut();
		LinksPage obj = new LinksPage(driver);
		for (int i=0 ; i<obj.AllLinks.size();i++) {
			String URL = obj.AllLinks.get(i).getAttribute("href");
			System.out.println("URL:"+URL);
			try {
				URL objUrl = new URL(URL);
				HttpURLConnection objHUC = (HttpURLConnection) objUrl.openConnection();
				objHUC.connect();
				int statusCode = objHUC.getResponseCode();
				if(statusCode>=200&&statusCode<=226) {
					System.out.println(statusCode+":"+URL+" is a valid Link");
				}else if(statusCode>=300&&statusCode<=308) {
					System.out.println(statusCode+":"+URL+" is a Redirection Link");
				}else if(statusCode>=400&&statusCode<=499) {
					System.out.println(statusCode+":"+URL+" is a Client Error Link");
				}else if(statusCode>=500&&statusCode<=599) {
					System.out.println(statusCode+":"+URL+" is a Server Error Link");
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
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
