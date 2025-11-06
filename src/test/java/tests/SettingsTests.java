package tests;

import org.junit.Assert;
import org.junit.Test;

public class SettingsTests extends BaseTest {

    private final String TARGET_CITY_URL = "https://www.foreca.com/100792680/Belgrade-Serbia";
    private final String LOCAL_STORAGE_KEY = "fcaSettings-v2";
    private final String TEMP_UNIT_PATH = "units.temp";
    private final String TIME_FORMAT_PATH = "time";

    @Test
    public void testS501_ToggleTemperatureUnit() {
        final String EXPECTED_UNIT = "F";

        driver.get(TARGET_CITY_URL);

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        cityPage.waitForCurrentCityTemperatureCVisible();

        cityPage.clickHamburgerMenuButton();
        cityPage.toggleToFahrenheit();

        String tempUnit = cityPage.getNestedLocalStorageValue(LOCAL_STORAGE_KEY, TEMP_UNIT_PATH);
        Assert.assertNotNull("Local Storage value not found for key: " + TEMP_UNIT_PATH, tempUnit);
        Assert.assertTrue("The temperature unit is not changed to 'F'. Found: " + tempUnit,
                tempUnit.contains(EXPECTED_UNIT));

        Assert.assertTrue("The temperature in F is not visible after changing the unit.",
                cityPage.currentCityTemperatureF.isDisplayed());
    }

    @Test
    public void testS502_ToggleTimeFormat() {
        final String EXPECTED_FORMAT = "12";

        driver.get(TARGET_CITY_URL);

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        cityPage.waitForCurrentCityTemperatureCVisible();

        cityPage.clickHamburgerMenuButton();
        cityPage.toggleTo12h();

        String timeFormat = cityPage.getNestedLocalStorageValue(LOCAL_STORAGE_KEY, TIME_FORMAT_PATH);

        Assert.assertNotNull("Local Storage value not found for key: " + TIME_FORMAT_PATH, timeFormat);
        Assert.assertTrue("The time format has not been changed to '12h'. Found: " + timeFormat,
                timeFormat.contains(EXPECTED_FORMAT));
    }

}
