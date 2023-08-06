package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Library {
	protected WebDriver driver;
	public static Properties objProperties;
	public HashMap<String,String> hmap = new HashMap<String,String>();
	public static ExtentHtmlReporter ExtHtmlReptr;
	public static ExtentReports ExtReports;
	public static ExtentTest ExtTest;
	
	/*
	 * ExtentHtmlReporter : responsible for look and feel of the report ,we can
	 * specify the report name , document title , theme of the report
	 * 
	 * ExtentReports : used to create entries in your report , create test cases in
	 * report , who executed the test case, environment name , browser
	 * 
	 * ExtentTest : update pass fail and skips and logs the test cases results
	 */
	
	public void ReadPropertiesFile() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		File objFile = new File(System.getProperty("user.dir")+"//src//test//resources//Config.properties");
		try {
			FileInputStream objFileinput = new FileInputStream(objFile);
			objProperties = new Properties();
			objProperties.load(objFileinput);
			System.out.println("broswer:"+objProperties.getProperty("browser"));
			System.out.println("Updated checkfrom github.dev to raise a PR");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  String TakeScreenShot(String testcaseName) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "//ScreenShots//" + dateName + testcaseName
				+ "captured.jpeg";
		FileUtils.copyFile(src, new File(destination));
		return destination;

	}

	public String TakeScreenShot() throws IOException {
		// TODO Auto-generated method stub
		File ObjFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String DestFile = System.getProperty("user.dir") + "//ScreenShots//"+dateName+"captured.jpg";
		FileUtils.copyFile(ObjFile, new File(DestFile));
		return DestFile;

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
	
	public static void StartExtentReport() {
		File ObjFile = new File(System.getProperty("user.dir") + "//test-output//ExtentReportV4.html");
		ExtentHtmlReporter objExtentHtmlReporter = new ExtentHtmlReporter(ObjFile);
		objExtentHtmlReporter.config().setDocumentTitle("Automation Report");
		objExtentHtmlReporter.config().setReportName("Automation Results In Extent Report");
		objExtentHtmlReporter.config().setTheme(Theme.STANDARD);
		ExtReports = new ExtentReports();
		ExtReports.attachReporter(objExtentHtmlReporter);

		ExtReports.setSystemInfo("TesterName", "Raghuveer");
		ExtReports.setSystemInfo("Broswer", objProperties.getProperty("browser"));
		ExtReports.setSystemInfo("Environment", "UAT");

	}
	
	public void UpdatingResultInExtentReport(ITestResult result) throws IOException {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SUCCESS) {
			ExtTest.log(Status.PASS, "Test Case Passed is " + result.getName());
			ExtTest.addScreenCaptureFromPath(TakeScreenShot());		
		} else if (result.getStatus() == ITestResult.FAILURE) {
			ExtTest.log(Status.FAIL, "Test Case Failed is " + result.getName());
			ExtTest.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
			try {
				ExtTest.addScreenCaptureFromPath(TakeScreenShot());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtTest.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}
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
	
	public void FlushReport() {
		ExtReports.flush();
	}
	
	public  WebDriver getBrowserCapabilities(String BrowserName) {
		DesiredCapabilities capabilities = null;
		if (BrowserName == null || BrowserName.equalsIgnoreCase("FIREFOX")) {
			capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			// options.setHeadless(headless);
			capabilities.merge(options);
		} else if (BrowserName.equalsIgnoreCase("IE")) {
			capabilities = DesiredCapabilities.internetExplorer();
			// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			// capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			// true);
			InternetExplorerOptions options = new InternetExplorerOptions();
			capabilities.merge(options);
		} else if (BrowserName.equalsIgnoreCase("CHROME")) {
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			// options.setHeadless(headless);
			capabilities.merge(options);
		} else if (BrowserName.equalsIgnoreCase("EDGE")) {
			capabilities = DesiredCapabilities.edge();
			EdgeOptions options = new EdgeOptions();
			capabilities.merge(options);
		}

		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;

	}

	public WebDriver initializeBrowser(String broswerName) {
		DesiredCapabilities dc = new DesiredCapabilities();
		if (broswerName.equals("chrome")) {
			dc.setBrowserName("chrome");
		} else if (broswerName.equals("firefox")) {
			dc.setBrowserName("firefox");
		} else if (broswerName.equals("safari")) {
			dc.setBrowserName("safari");
		} else if (broswerName.equals("Edge")) {
			dc.setBrowserName("Edge");
		} else if (broswerName.equals("ie")) {
			dc.setBrowserName("ie");
		}
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), dc);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

}
