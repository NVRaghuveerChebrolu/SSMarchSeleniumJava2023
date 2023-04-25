package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramesPage {
	
	public static final String singleFrame = "singleframe";
	public static final By TextBox = By.xpath("//input[@type='text']");
	public static final By FrameWithInFramebutton= By.xpath("//a[contains(text(),'with in an')]");
	public static final By OuterFrame = By.xpath("//iframe[@src='MultipleFrames.html']");
	public static final By InnerFrame = By.xpath("//iframe[@src='SingleFrame.html']");
	
	
}
