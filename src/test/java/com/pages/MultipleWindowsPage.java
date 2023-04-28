package com.pages;

import org.openqa.selenium.By;

public class MultipleWindowsPage {
public static final By newBrowserWindowButton = By.name("newbrowserwindow123");
public static final By menuOfNewBrowserWindow = By.xpath("//button[@type='button']");
public static final By AboutMeInNewBrowserWindow = By.xpath("//div[@id='header_menu_toggler']/descendant::a[text()='About Me']/following-sibling::span");

}
