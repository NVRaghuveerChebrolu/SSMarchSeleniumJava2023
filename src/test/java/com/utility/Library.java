package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library {
	protected WebDriver driver;
	public Properties objProperties;
	
	public void ReadPropertiesFile() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		File objFile = new File(System.getProperty("user.dir")+"//src//test//resources//Config.properties");
		try {
			FileInputStream objFileinput = new FileInputStream(objFile);
			objProperties = new Properties();
			objProperties.load(objFileinput);
			System.out.println(objProperties.getProperty("browser"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void LaunchBrowser() {
		// TODO Auto-generated method stub
		String browerName = objProperties.getProperty("browser");
		System.out.println("browerName:"+browerName);
		switch(browerName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions options = new ChromeOptions();
			//File objFile = new File (System.getProperty("user.dir")+"//src//test//resources//addBlocker//extension_5_6_0_0.crx");
			//options.addExtensions(objFile);
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//options.merge(capabilities);
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();;
			driver = new EdgeDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();;
			driver = new InternetExplorerDriver();
			break;	
		case "firefox":
			WebDriverManager.firefoxdriver().setup();;
			driver = new FirefoxDriver();
			break;	
		}
		driver.manage().window().maximize();
		//implicit wait :
		//It is a global waiting mechanism application for all web Elements . This is the maximum duration that the script will wait to recognize the web element.
		//if the webElement is identified in 3 seconds (for example) it will not wait for remaining 27 seconds .
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void PageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public void PageLoadTimeOut(int timoutDuration) {
		driver.manage().timeouts().pageLoadTimeout(timoutDuration, TimeUnit.SECONDS);
	}
	
	
	public void DoubleClick(WebElement element) {
		Actions objAction = new Actions(driver);
		objAction.doubleClick(element).build().perform();
	}
	
	public void RightClick(WebElement element) {
		Actions objAction = new Actions(driver);
		objAction.contextClick(element).build().perform();
	}
}
