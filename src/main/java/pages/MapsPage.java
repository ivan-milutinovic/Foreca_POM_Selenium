package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MapsPage extends BasePage {

    @FindBy(xpath = "//canvas[@aria-label='Map']\n")
    public WebElement mapContainer;

    @FindBy(xpath = "//button[@title='Zoom in']//span[@class='maplibregl-ctrl-icon mapboxgl-ctrl-icon']")
    private  WebElement zoomInButton;

    @FindBy(xpath = "//button[@title='Zoom out']//span[@class='maplibregl-ctrl-icon mapboxgl-ctrl-icon']")
    private WebElement zoomOutButton;

    public MapsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickZoomIn() {
        clickElement(zoomInButton);
    }

    public void clickZoomOut() {
        clickElement(zoomOutButton);
    }

    public boolean isMapContainerVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(mapContainer));
            return mapContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
