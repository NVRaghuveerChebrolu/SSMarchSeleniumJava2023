package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
			WebDriverManager.chromedriver().setup();;
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
		//implicit wait : Applicable for all WebElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void PageLoadTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	

}
