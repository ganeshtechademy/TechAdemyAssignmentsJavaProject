package com.assigment.ta.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * 	@Description : To set objects of WebDriver,URL,Username & Password in a bean object
 * 
 */
public class AppBean {

	public RemoteWebDriver driver;
	public String parentWindow;

	public String applicationURL;

	public String applicationUserName;

	public String applicationPassword;

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public String getApplicationURL() {
		return applicationURL;
	}

	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}

	public String getApplicationUserName() {
		return applicationUserName;
	}

	public void setApplicationUserName(String applicationUserName) {
		this.applicationUserName = applicationUserName;
	}

	public String getApplicationPassword() {
		return applicationPassword;
	}

	public void setApplicationPassword(String applicationPassword) {
		this.applicationPassword = applicationPassword;
	}


	public String getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(String parentWindow) {
		this.parentWindow = parentWindow;
	}
}
