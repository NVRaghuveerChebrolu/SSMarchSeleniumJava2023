package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsPage {
	WebDriver driver;

	public AlertsPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "alertButton")
	static WebElement NormalAlert;

	@FindBy(id = "timerAlertButton")
	static WebElement TimerAlert;

	public void ClickNormaAlert() {
		NormalAlert.click();
	}

	public void ClickTimerAlert() {
		TimerAlert.click();
	}

}
