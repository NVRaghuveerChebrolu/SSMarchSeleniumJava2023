package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MouseOperationsPOM {
	
	public WebDriver driver;
	
	public MouseOperationsPOM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'right click')]")
	public WebElement rightClickButton;
	
	@FindBy(xpath = "//span[text()='Delete']")
	public WebElement DeleteOption;
	
	@FindBy(xpath = "//iframe")
	public WebElement Frame;
	
	@FindBy(xpath="//span[contains(text(),'Double click')]/preceding-sibling::div")
	public WebElement DoubleClickBox;
	
	@FindBy(id = "draggable")
	public WebElement draggable;
	
	@FindBy(id="droppable")
	public WebElement droppable;

}
