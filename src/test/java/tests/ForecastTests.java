package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ForecastTests extends BaseTest {

    private final String TARGET_CITY_URL = "https://www.foreca.com/100792680/Belgrade-Serbia";

    @Test
    public void testS701_TenDayForecastIntegrity() {
        driver.get(TARGET_CITY_URL);

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        cityPage.waitForCurrentCityTemperatureCVisible();

        cityPage.clickTenDayForecast();

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        Assert.assertTrue("URL does not contain '10-day-forecast' after navigation.",
                cityPage.getCurrentUrl().contains("10-day-forecast"));

        List<WebElement> tenDayRows = cityPage.tenDaysRows;

        Assert.assertTrue("At least 10 forecast lines were not found. Found: " + tenDayRows.size(),
                tenDayRows.size() >= 10);

        if (!tenDayRows.isEmpty()) {

            Assert.assertTrue("The first line does not contain a date (CSS: .date).",
                    cityPage.tenDaysRowsFirstRowDate.isDisplayed());

            Assert.assertTrue("The first line contains no temperature (CSS: .temp-box).",
                    cityPage.tenDaysRowsFirstRowTemp.isDisplayed());
        }
    }
}
