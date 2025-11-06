package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utils.CityData;
import utils.CsvDataReader;

import java.util.Collection;

@RunWith(Parameterized.class)
public class CriticalPathTests  extends BaseTest {

    private final CityData cityData;

    public CriticalPathTests(CityData cityData) {
        this.cityData = cityData;
    }

    @Parameterized.Parameters(name = "Test S2.01 Grad: {0}")
    public static Collection<Object[]> getCityData() {
        return CsvDataReader.getTestData("testCities.csv");
    }

    @Test
    public void testS201_SuccessfulCitySearch() {
        String cityName = cityData.getCityName();
        String expectedUrlPart = cityData.getExpectedUrlPart();

        homePage.performSearch(cityName);
        cityPage.clickFirstSearchResult();

        cityPage.waitForPageLoad();

        String currentUrl = cityPage.getCurrentUrl();
        Assert.assertTrue("URL ne sadrzi ocekivani deo: " + expectedUrlPart, currentUrl.toLowerCase().contains(expectedUrlPart.toLowerCase()));

        String tempreratureText = cityPage.getCurrentTemperatureTextC();
        Assert.assertTrue(tempreratureText.trim().matches("^[+-]?\\d+°$"));
    }


    @Test
    public void testS205_CheckDefaultTempUnitInLocalStorage() {
        final String TARGET_CITY_URL = "https://www.foreca.com/100792680/Belgrade-Serbia";
        final String LOCAL_STORAGE_KEY = "fcaSettings-v2";
        final String NESTED_PATH = "units.temp";
        final String EXPECTED_UNIT = "C";

        driver.get(TARGET_CITY_URL);

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        cityPage.waitForCurrentCityTemperatureCVisible();

        String tempUnit = cityPage.getNestedLocalStorageValue(LOCAL_STORAGE_KEY, NESTED_PATH);

        Assert.assertNotNull("Local Storage vrednost nije pronadjena za ključ: " + NESTED_PATH, tempUnit);

        Assert.assertTrue("Podrazumevana jedinica nije '" + EXPECTED_UNIT + "'. Pronadjena: " + tempUnit, tempUnit.contains(EXPECTED_UNIT));
    }

}
