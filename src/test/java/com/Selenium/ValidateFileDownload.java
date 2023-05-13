package com.Selenium;

import org.testng.annotations.Test;

import com.pages.FileDownloadPage;
import com.pages.FileUploadPage;
import com.pages.MouseOperationsPOM;
import com.pages.MultipleWindowsPage;
import com.pages.WebTablePage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.event.MenuKeyEvent;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
public class ValidateFileDownload extends Library {
	
	@Test(priority =1)
	public void ValidateFIleDownloadPageLoadedSuccessfully() {
		driver.get(objProperties.getProperty("FileDownload"));
		PageLoadTimeOut(45);
		String TitleOfFileDownloadPage = driver.getTitle();
		Assert.assertEquals(TitleOfFileDownloadPage, objProperties.getProperty("titleOfFileDownloadPage"));
	}
	
	@Test(priority=2)
	public void ValidateFileDownload100kbDOCX() throws InterruptedException { 
		System.out.println("inside ValidateFileDownload100kbDOCX");
		WebElement element1Mb = driver.findElement(FileDownloadPage.DocFile1Mb);
		scrollIntoWebElement(element1Mb);
		WebElement element100kb = driver.findElement(FileDownloadPage.DocxFile100kb);
		WaitUnitilElementIsClickable(element100kb);
		element100kb.click();
		Thread.sleep(8000);
		File objFile = new File(System.getProperty("user.dir"));
		File[] AllFiles = objFile.listFiles();
		for(File IndividualFile : AllFiles) {
			String IndividualFileName = IndividualFile.getName();
			Boolean fileFound = false;
			if(IndividualFileName.contains("100kB")) {
				fileFound=true;
				Assert.assertTrue(fileFound);
				IndividualFile.deleteOnExit();
				break;
			}
		}
		
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
