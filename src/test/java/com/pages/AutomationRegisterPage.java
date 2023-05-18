package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationRegisterPage {
	WebDriver driver;
	
	public AutomationRegisterPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	public WebElement FirstName;
	
	@FindBy(xpath="//input[@placeholder='Last Name']")
	public WebElement LastName;
	
	@FindBy(xpath="//textarea[@ng-model='Adress']")
	public WebElement Address;
	
	@FindBy(xpath="//*[@type='email']")
	public WebElement Email;
	
	@FindBy(xpath="//*[@type='tel']")
	public WebElement Phone;
	
	@FindBy(xpath="//input[@value='Male']")
	public WebElement MaleRadioButton;
	
	@FindBy(xpath="//input[@value='FeMale']")
	public WebElement FeMaleRadioButton;
	
	@FindBy(id="checkbox1")
	public WebElement Cricket;
	
	@FindBy(id="checkbox2")
	public WebElement Movies;
	
	@FindBy(id="checkbox3")
	public WebElement Hockey;
	
	@FindBy(id="msdd")
	public WebElement Languages;
	
	@FindBy(xpath="//div[@id='msdd']/following-sibling::div/ul/li/a")
	public List<WebElement> AllLanguages;
	
	
	
	
	

	
	

}
