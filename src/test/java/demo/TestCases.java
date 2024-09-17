package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    ChromeDriver driver;

    @Test
    public void testCase01() throws InterruptedException {

        System.out.println("Start TestCase 01");

        boolean status;
        driver.get("https://www.flipkart.com");
        Wrappers.enterText(driver, By.cssSelector(".Pke_EE"), "Washing Machine");
        Thread.sleep(3000);
        Wrappers.clickElement(driver, By.xpath("//div[text()='Popularity']"));
        status = Wrappers.productRating(driver, By.xpath("//span[contains(@id,'productRating')]/div"), 4.0);
       

        System.out.println("End TestCase 01");
    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("Start TestCase 02");

        boolean status;
        driver.get("https://www.flipkart.com");
        Wrappers.enterText(driver, By.cssSelector(".Pke_EE"), "iPhone");
        Thread.sleep(3000);
        status = Wrappers.productTitleAndDiscount(driver, By.xpath("//div[@class='yKfJKb row']"), 17);
       

        System.out.println("End TestCase 02");
    }
    @Test
    public void testCase03() throws InterruptedException {
        System.out.println("Start TestCase 03");

        boolean status;
        driver.get("https://www.flipkart.com");
        Wrappers.enterText(driver, By.cssSelector(".Pke_EE"), "Coffee Mug");
        Thread.sleep(3000);
        Wrappers.clickElement(driver, By.xpath("//div[@class='XqNaEv']"));
        Thread.sleep(3000);
        status = Wrappers.productTitleAndImage(driver, By.xpath("//span[@class='Wphh3N']"));
     

        System.out.println("End TestCase 03");
    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}