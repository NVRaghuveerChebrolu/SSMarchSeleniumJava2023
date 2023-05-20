package com.Selenium;

import org.testng.annotations.Test;

import com.pages.AutomationRegisterPage;
import com.pages.MouseOperationsPOM;
import com.pages.MultipleWindowsPage;
import com.pages.WebTablePage;
import com.utility.Library;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class ValidateDataDrivenFromExelFile extends Library {

	@Test(priority = 0)
	public void ValidateAutomationTestingRegisterPageLoadedSuccessfully() {
		System.out.println("inside ValidateAutomationTestingRegisterPageLoadedSuccessfully");
		driver.get(objProperties.getProperty("AutomationRegister"));
		PageLoadTimeOut(45);
		String title = driver.getTitle();
		Assert.assertEquals(title, objProperties.getProperty("TitleOfRegisterPage"),
				"TitleOfRegisterPageIsNotValidated");
	}

	@Test(dependsOnMethods = { "ValidateAutomationTestingRegisterPageLoadedSuccessfully" }, priority = 1)
	public void ValidateRegisterPageFromExcel() {
		System.out.println("inside ValidateRegisterPageFromExcel");

		try {
			File objFile = new File(System.getProperty("user.dir") + "//src//test//resources//AutomationDemoSite.xlsx");
			FileInputStream objFileInputStream = new FileInputStream(objFile);
			// use below 2 lines when file extension is .xlsx
			XSSFWorkbook objXSSFWorkBook = new XSSFWorkbook(objFileInputStream);
			XSSFSheet objXSSFSheet = objXSSFWorkBook.getSheet("TestData");

			// use below 2 lines when file extension is .xls
			// HSSFWorkbook objHSSFWorkBook = new HSSFWorkbook(objFileInputStream);
			// HSSFSheet objHSSFSheet = objHSSFWorkBook.getSheet("TestData");

			int NumberOfRows = objXSSFSheet.getLastRowNum();
			System.out.println("number of Rows:" + NumberOfRows);
			for (int row = 1; row <= NumberOfRows; row++) {
				hmap = ReadExcelFile(row, objXSSFSheet);

				System.out.println("---------------------------------------");
				for (Map.Entry<String, String> map : hmap.entrySet()) {
					System.out.println(map.getKey() + ":" + map.getValue());
				}
				if (hmap.get("RunMode").equalsIgnoreCase("Yes")) {

					AutomationRegisterPage objARP = new AutomationRegisterPage(driver);
					objARP.FirstName.clear();
					objARP.FirstName.sendKeys(hmap.get("FirstName"));

					objARP.LastName.clear();
					objARP.LastName.sendKeys(hmap.get("LastName"));

					objARP.Address.clear();
					objARP.Address.sendKeys(hmap.get("Address"));

					objARP.Email.clear();
					objARP.Email.sendKeys(hmap.get("Emailaddress"));

					objARP.Phone.clear();
					objARP.Phone.sendKeys(hmap.get("PhoneNumber"));

					if (hmap.get("Gender").equals("Male")) {
						objARP.MaleRadioButton.click();
					} else {
						objARP.FeMaleRadioButton.click();
					}

					if (hmap.get("Hobbies").equalsIgnoreCase("Cricket")) {
						objARP.Cricket.click();
					} else if (hmap.get("Hobbies").equals("Movies")) {
						objARP.Movies.click();
					} else {
						objARP.Hockey.click();
					}

					scrollIntoWebElement(objARP.Languages);

					if (row > 1) {
						objARP.LanguagesCloseIcon.click();
					}

					objARP.Languages.click();

					List<WebElement> AllLanguages = objARP.AllLanguages;
					SelectDesiredValueFromDropDown(AllLanguages, hmap.get("Languages"));

					objARP.SkillsField.click();

					objARP.Skills.click();
					SelectDesiredValueFromDropDown(objARP.AllSkills, hmap.get("Skills"));

					objARP.SelectCountry.click();
					SelectDesiredValueFromDropDown(objARP.AllCountries, hmap.get("SelectCountry"));

					objARP.Year.click();
					SelectDesiredValueFromDropDown(objARP.AllYears, hmap.get("DOB_YY"));

					objARP.Month.click();
					SelectDesiredValueFromDropDown(objARP.AllMonths, hmap.get("DOB_MM"));

					objARP.Day.click();
					SelectDesiredValueFromDropDown(objARP.AllDays, hmap.get("DOB_DD"));

					objARP.Password.clear();
					objARP.Password.sendKeys(hmap.get("Password"));
					objARP.ConfirmPwd.clear();
					objARP.ConfirmPwd.sendKeys(hmap.get("confirmPassword"));

					FileOutputStream objFileOutPut = new FileOutputStream(objFile);
					WriteToExcelFile(row, objXSSFSheet);
					objXSSFWorkBook.write(objFileOutPut);

				} else {
					int row1 = row + 1;
					System.out.println("Run Mode is not marked as Yes for row number:" + row1);
				}
			}
			objXSSFWorkBook.close();
			objFileInputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
