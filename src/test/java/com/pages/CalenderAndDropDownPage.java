package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalenderAndDropDownPage {
	
	WebDriver driver;
	
	public CalenderAndDropDownPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dateOfBirthInput")
	public WebElement DOBinput;
	
	@FindBy(xpath="//select[@class='react-datepicker__month-select']")
	public WebElement DOB_Month;
	
	@FindBy(xpath="//select[@class='react-datepicker__year-select']")
	public WebElement DOB_Year;
	
	@FindBy(xpath="//div[@role='listbox']/div/div")
	public List<WebElement> DOB_Day;
	
	public void ClickOndateOfBirthInput() {
		DOBinput.click();
	}

}
