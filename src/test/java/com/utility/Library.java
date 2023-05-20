package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library {
	protected WebDriver driver;
	public Properties objProperties;
	public HashMap<String,String> hmap = new HashMap<String,String>();
	
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
			ChromeOptions options = new ChromeOptions();
			//File objFile = new File (System.getProperty("user.dir")+"//src//test//resources//addBlocker//extension_5_6_0_0.crx");
			//options.addExtensions(objFile);
			//DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//options.merge(capabilities);
			//driver = new ChromeDriver(options);
			
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
			
			//driver = new ChromeDriver();
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
	
	
	public void scrollIntoWebElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void WaitUnitilElementIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public HashMap<String, String> ReadExcelFile(int row, XSSFSheet objXSSFSheet) {
		DataFormatter objDataFormatter = new DataFormatter();
		hmap.put("RunMode", objXSSFSheet.getRow(row).getCell(0).getStringCellValue());
		hmap.put("TestCaseName", objXSSFSheet.getRow(row).getCell(1).getStringCellValue());
		hmap.put("FirstName", objXSSFSheet.getRow(row).getCell(2).getStringCellValue());
		hmap.put("LastName", objXSSFSheet.getRow(row).getCell(3).getStringCellValue());
		hmap.put("Address", objXSSFSheet.getRow(row).getCell(4).getStringCellValue());
		hmap.put("Emailaddress", objXSSFSheet.getRow(row).getCell(5).getStringCellValue());
		
		hmap.put("PhoneNumber", objDataFormatter.formatCellValue( objXSSFSheet.getRow(row).getCell(6)));
		
		hmap.put("Gender", objXSSFSheet.getRow(row).getCell(7).getStringCellValue());
		hmap.put("Hobbies", objXSSFSheet.getRow(row).getCell(8).getStringCellValue());
		hmap.put("Languages", objXSSFSheet.getRow(row).getCell(9).getStringCellValue());
		hmap.put("Skills", objXSSFSheet.getRow(row).getCell(10).getStringCellValue());
		hmap.put("Country", objXSSFSheet.getRow(row).getCell(11).getStringCellValue());
		hmap.put("SelectCountry", objXSSFSheet.getRow(row).getCell(12).getStringCellValue());
		
		hmap.put("DOB_YY", objDataFormatter.formatCellValue( objXSSFSheet.getRow(row).getCell(13)));
		
		hmap.put("DOB_MM", objXSSFSheet.getRow(row).getCell(14).getStringCellValue());
		
		hmap.put("DOB_DD", objDataFormatter.formatCellValue( objXSSFSheet.getRow(row).getCell(15)));
		
		hmap.put("Password", objXSSFSheet.getRow(row).getCell(16).getStringCellValue());
		hmap.put("confirmPassword", objXSSFSheet.getRow(row).getCell(17).getStringCellValue());												
		return hmap;
	}
	

	public void WriteToExcelFile(int row, XSSFSheet objXSSFSheet) {
		
		objXSSFSheet.getRow(row).createCell(18).setCellValue("Pass");
		
	}
	
	public void SelectDesiredValueFromDropDown(List<WebElement> AllDropDownValues, String DropDownValueFromExcel) {
		int NumberOfDropDownValues = AllDropDownValues.size();
		for(int i=0;i<NumberOfDropDownValues;i++) {
			String IndividualDropDownValue = AllDropDownValues.get(i).getText();
			if(IndividualDropDownValue.equalsIgnoreCase(DropDownValueFromExcel)) {
				AllDropDownValues.get(i).click();
				break;
			}
		}
		
	}
}
