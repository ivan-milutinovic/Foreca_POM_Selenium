package tests;

import org.junit.Assert;
import org.junit.Test;

public class NavigationTests extends BaseTest {

    private final String TARGET_CITY_URL = "https://www.foreca.com/100792680/Belgrade-Serbia";

    @Test
    public void testS401_MapsToCityNavigation() {
        driver.get(TARGET_CITY_URL);
//        cityPage.waitForCurrentCityTemperatureCVisible();

        homePage.waitForPageLoad();

        homePage.clickMapsLink();

        mapsPage.waitForPageLoad();
        mapsPage.handleVignetteAd();

        Assert.assertTrue("URL does not contain 'weathermap' after clicking Maps.", mapsPage.getCurrentUrl().contains("weathermap"));

        homePage.clickTodayLink();

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        Assert.assertTrue("The critical temperature element is not visible after returning to the 'Today' link.", cityPage.isCurrentCityTemperatureCVisible());
        Assert.assertTrue("We did not return to the city URL after clicking on the 'Today' link.",cityPage.getCurrentUrl().contains("Belgrade-Serbia"));
    }

    @Test
    public void testS402_DirectUrlNavigation() {
        driver.get(TARGET_CITY_URL);

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        String currentUrl = cityPage.getCurrentUrl();
        Assert.assertTrue("The current URL does not equal the expected city URL.", currentUrl.toLowerCase().contains(TARGET_CITY_URL.toLowerCase()));

        Assert.assertTrue("The critical temperature element is not visible after direct navigation.", cityPage.isCurrentCityTemperatureCVisible());
    }

    @Test
    public void testS403_NavigationToHourlyForecast() {
        final String EXPECTED_SECTION_URL = "hourly";

        driver.get(TARGET_CITY_URL);
//        cityPage.waitForCurrentCityTemperatureCVisible();

        cityPage.waitForPageLoad();

        homePage.clickHourlyLink();

        cityPage.waitForPageLoad();
        cityPage.handleVignetteAd();

        String currentUrl = cityPage.getCurrentUrl();
        Assert.assertTrue("URL does not contain 'hourly' after navigation.", currentUrl.toLowerCase().contains(EXPECTED_SECTION_URL));
    }

}
