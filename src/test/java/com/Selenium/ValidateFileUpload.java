package com.Selenium;

import org.testng.annotations.Test;

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
public class ValidateFileUpload extends Library {
	
	@Test(priority =1)
	public void ValidateFIleUploadPageLoadedSuccessfully() {
		driver.get(objProperties.getProperty("FileUpload"));
		PageLoadTimeOut(45);
		String TitleOfFileUploadPage = driver.getTitle();
		Assert.assertEquals(TitleOfFileUploadPage, objProperties.getProperty("TiTleOfFileupload"));
	}
	
	@Test(priority=2)
	public void ValidateFileUploadByBrowseButton() throws AWTException, InterruptedException, UnsupportedFlavorException, IOException {
		FileUploadPage obj = new FileUploadPage(driver);
		Actions objActions = new Actions(driver);
		objActions.click(obj.BrowseButton).click().build().perform();
		File objFile =  new File (System.getProperty("user.dir")+"//src//test//resources//Materials//SampleFileToUpload.jpg");
		StringSelection objStringSelection = new StringSelection(objFile.toString());
		Clipboard objclipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objclipboard.setContents(objStringSelection,null);
		Transferable objTransferable = objclipboard.getContents(null);
		if(objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		}
		Robot objRobot = new Robot();
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		//objRobot.wait(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		//objRobot.wait(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		
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
