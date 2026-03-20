package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {


    public static WebDriver Driver;
    @Before
    public void InitializeTest(Scenario scenario) {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.setCapability("nativeEvents", false);
//        chromeOptions.addArguments("test-type");
//        //chromeOptions.addArguments("--headless");
//        this.Driver = new ChromeDriver(chromeOptions);
//        // CommObjects commObjects = new CommObjects();
//        CommObjects.setDriver(this.Driver);
    }

    @After
    public void CloseBrowser(){
//        if(this.Driver!=null){
//            this.Driver.quit();
//            System.out.println("Driver quit..");
//        }
    }
}
