package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CityPage;
import pages.HomePage;
import pages.MapsPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected CityPage cityPage;
    protected MapsPage mapsPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");

        options.addArguments("--block-url=https://www.google-analytics.com/*");
        options.addArguments("--block-url=https://googletagservices.com/*");
        options.addArguments("--block-url=https://googlesyndication.com/*");
        options.addArguments("--block-url=https://doubleclick.net/*");

        driver = new ChromeDriver(options);

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        cityPage = new CityPage(driver);
        mapsPage = new MapsPage(driver);

        homePage.gotoHomePage();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}