package com.assignment.ui.qa.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.io.IOException;
import java.time.Duration;

public class TestTwoMakeMyTripBooking {
    public static RemoteWebDriver driver = null;
    WebDriverWait webDriverWait = null;
    final String webURL = "https://makemytrip.global/?cc=GB";

    /**
     * @throws Exception
     * @Description : Test to verify user to  access MakeMyTripBooking
     * application and must able to  book round trip tickets - happy
     * path flow
     */
    @BeforeMethod
    public void initalizeBrowserNew() throws Exception {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //opens the link in the browser
        driver.get(webURL);
        System.out.println("Application loaded successfully ....>>");

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitTime(5);

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("--incognito");
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//
//        options.addArguments("Chrome/146.0.7680.80");
//
////		options.addArguments("user-data-dir=/Users/soujanyavakkalanka/Library/Application Support/Google/Chrome");
////		options.addArguments("profile-directory=Default");
//
//        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.setAcceptInsecureCerts(true);
//        driver = new ChromeDriver(options);

        //Accept cookie
        WebElement cookieAccept = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cookiesModal__acceptCookiesBtn buttonCls btn__primary uppercase ']")));
        cookieAccept.click();
    }

    @Test
    public void book_roundTrip_TC() throws Exception {

        the_user_selects_roundtrip_option();
        the_user_selects_the_from("HYD");
        the_user_selects_the_to_as("MAA");
        the_user_selects_the_fromdate_and_todate();
        the_user_clicks_on_the_search_button();
        the_flights_details_are_displayed_successfully();
		//homePage.searchResult();
    }


    private void the_user_selects_roundtrip_option() throws NoSuchElementException {

        //Select roundtrip
        WebElement roundTrip = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='roundTrip']")));
        roundTrip.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //User to select from location
    private void the_user_selects_the_from(String fromString) throws NoSuchElementException,InterruptedException {
        System.out.println("from ********* = " + fromString);
        WebElement fromCity = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        fromCity.click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(fromString);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='react-autowhatever-1-section-0-item-0']"))).click();

    }

    //User to select from to Location
    private void the_user_selects_the_to_as(String toString) throws NoSuchElementException,InterruptedException{
        WebElement toCity = driver.findElement(By.id("toCity"));
        toCity.click();
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(toString);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chennai')]"))).click();
    }


    //Select to and from date
    private void the_user_selects_the_fromdate_and_todate() throws NoSuchElementException,InterruptedException {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='departure']"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DayPicker-Day'])[1]"))).click();
        waitTime(2);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DayPicker-Day'])[5]"))).click();
        waitTime(2);
        System.out.println("Selected to and from dates ....>");
    }


    //search button for results
    private void the_user_clicks_on_the_search_button() throws NoSuchElementException,InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']"))).click();
        System.out.println("Clicked on Search Button ....>");
        try {
            Thread.sleep(5000);
            waitForPageLoad(driver);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //Validate the flight results
    private void the_flights_details_are_displayed_successfully() throws NoSuchElementException {
		final String ExpectedResult ="Flights from Hyderabad to Chennai, and back";
		String verifyFlightSearch;
		verifyFlightSearch= driver.findElement(By.xpath("//div[@class='listingRhs']/p/span")).getText();

		Assert.assertEquals(verifyFlightSearch,ExpectedResult);
    }


    // Helper functions

	private void waitTime(long durationinSec) throws InterruptedException {
		Thread.sleep(durationinSec);
	}

    private void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    @AfterMethod
    public void AfterTest(){
        //Quit driver instance
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Quit Driver: ");
            } catch (Exception e) {
                System.out.println("Error while closing browser: " + e.getMessage());
            } finally {
                driver = null; // Important: manual nulling prevents double-quit attempts
            }
        }

    }
}
