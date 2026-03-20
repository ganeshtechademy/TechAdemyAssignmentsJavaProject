package stepdef;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class BookTicketStepDefinition {

     WebDriver driver;
     WebDriverWait webDriverWait =null;


    @Given("the user to launch the application")
    public void the_user_to_launch_the_application() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //opens the link in the browser
        driver.get("https://makemytrip.global/?cc=GB");
        System.out.println("Application loaded successfully ....>>");

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitTime(5);

        //Accept cookie
        WebElement cookieAccept = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cookiesModal__acceptCookiesBtn buttonCls btn__primary uppercase ']")));
        cookieAccept.click();

    }


    @And("the user choose roundtrip option for journey")
    public void theUserChooseRoundtripOptionForJourney() {
        //Select roundtrip
        WebElement roundTrip = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='roundTrip']")));
        roundTrip.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("the user selects the from {string}")
    public void the_user_selects_the_from (String fromString){
        WebElement fromCity = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        fromCity.click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(fromString);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='react-autowhatever-1-section-0-item-0']"))).click();
        System.out.println("from ********* = " + fromString);
    }

    @Given("the user selects the to as {string}")
    public void the_user_selects_the_to_as (String toString){
        WebElement toCity = driver.findElement(By.id("toCity"));
        toCity.click();
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(toString);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chennai')]"))).click();
        System.out.println("from ********* = " + toString);
    }

    @Given("the user selects the fromdate and todate")
    public void the_user_selects_the_fromdate_and_todate () throws InterruptedException {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='departure']"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DayPicker-Day'])[1]"))).click();
        waitTime(2);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DayPicker-Day'])[5]"))).click();
        waitTime(2);
        System.out.println("Selected to and from dates for journey ....>");

    }
    @When("the user clicks on the search button")
    public void the_user_clicks_on_the_search_button () {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']"))).click();
        System.out.println("Clicked on Search Button ....>");
        try {
            waitTime(5000);
            waitForPageLoad(driver);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("the flights details are displayed successfully")
    public void the_flights_details_are_displayed_successfully () {
        System.out.println("from Flight result details ");
        final String ExpectedResult ="Flights from Hyderabad to Chennai, and back";
        String verifyFlightSearch;
        verifyFlightSearch =webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='listingRhs']/p/span"))).getText();
     //   verifyFlightSearch= driver.findElement(By.xpath("//div[@class='listingRhs']/p/span")).getText();
        Assert.assertEquals(verifyFlightSearch,ExpectedResult);
        System.out.println(" Flight result details verified ..... :)");
    }


    //Helper classes
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


}
