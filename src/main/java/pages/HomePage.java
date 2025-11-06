package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    private static final String BASE_URL = "https://www.foreca.com/";

    @FindBy(id = "searchField")
    private WebElement searchField;

    @FindBy(css = ".quickLinks ul li a[href='/weathermap/rain']")
    private WebElement navigationLinkMaps;

    @FindBy(xpath = "//li//a[contains(text(),'Today')]")
    private WebElement navigationLinkToday;

    @FindBy(xpath = "//li//a[contains(text(),'Hourly')]")
    private WebElement navigationLinkHourly;

    @FindBy(id = "dismiss-button")
    public WebElement advertCloseButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void gotoHomePage() {
        driver.get(BASE_URL);
    }

    public void performSearch(String cityName) {
        fillElement(searchField, cityName);
    }

    public void clickMapsLink() {
        clickElement(navigationLinkMaps);
    }

    public void clickTodayLink() {
        clickElement(navigationLinkToday);
    }

    public void clickHourlyLink() {
        clickElement(navigationLinkHourly);
    }


}
