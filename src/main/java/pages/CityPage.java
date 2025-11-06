package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CityPage extends BasePage {

    @FindBy(css = "#search ul li:first-child")
    public WebElement firstSearchResult;

    @FindBy(css = "#search ul li .name p em")
    public WebElement firstSearchResultCityName;

    @FindBy(css = "#search ul li")
    public List<WebElement> searchResult;

    @FindBy(css = ".row .temp .large .temp_c")
    public WebElement currentCityTemperatureC;

    @FindBy(css = ".row .temp .large .temp_f")
    public WebElement currentCityTemperatureF;

    @FindBy(id = "drawerToggle")
    private WebElement hamburgerMenuToggle;

    @FindBy(xpath = "//span[normalize-space()='F°']")
    private WebElement unitToggleFahrenheit;

    @FindBy(xpath = "//span[normalize-space()='12h']")
    private WebElement unitToggle12h;

    @FindBy(xpath = "//section[@class='quickLinks']//a[contains(text(),'10 day')]")
    private WebElement tenDaysForecastTab;

    @FindBy(css = ".dayContainer .day-container")
    public List<WebElement> tenDaysRows;

    @FindBy(css = ".dayContainer .day-container:first-of-type .date")
    public WebElement tenDaysRowsFirstRowDate;

    @FindBy(css = ".dayContainer .day-container:first-of-type .dataContainer .temp:first-of-type")
    public WebElement tenDaysRowsFirstRowTemp;

    public CityPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForCurrentCityTemperatureCVisible() {
        wait.until(ExpectedConditions.visibilityOf(currentCityTemperatureC));
    }

    public void clickFirstSearchResult() {
        clickElement(firstSearchResult);
    }

    public void clickHamburgerMenuButton() {
        clickElement(hamburgerMenuToggle);
    }

    public void clickTenDayForecast() { clickElement(tenDaysForecastTab); };

    public void toggleToFahrenheit() {
        clickElement(unitToggleFahrenheit);
    }

    public void toggleTo12h() {
        clickElement(unitToggle12h);
    }

    public boolean isCurrentCityTemperatureCVisible() {
        try {
            waitForCurrentCityTemperatureCVisible();
            return currentCityTemperatureC.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentTemperatureTextC() {
        return currentCityTemperatureC.getText();
    }




}
