package tests;

import org.junit.Assert;
import org.junit.Test;

public class MapsTests extends BaseTest{

    private final String TARGET_CITY_URL = "https://www.foreca.com/100792680/Belgrade-Serbia";
    private final String MAPS_URL_PART = "weathermap/rain";

    @Test
    public void testS601_MapLoadingAndVisibility() {
        driver.get(TARGET_CITY_URL);

        homePage.waitForPageLoad();

        homePage.clickMapsLink();

        mapsPage.waitForPageLoad();
        mapsPage.handleVignetteAd();

        String currentUrl = mapsPage.getCurrentUrl();
        Assert.assertTrue("The folder container is not visible.",currentUrl.contains(MAPS_URL_PART));

        Assert.assertTrue(mapsPage.isMapContainerVisible());
    }

    @Test
    public void testS602_ZoomFunctionality() {
        driver.get(TARGET_CITY_URL);

        homePage.waitForPageLoad();

        homePage.clickMapsLink();

        mapsPage.waitForPageLoad();
        mapsPage.handleVignetteAd();
        mapsPage.isMapContainerVisible();

        mapsPage.clickZoomIn();

        mapsPage.clickZoomOut();

        Assert.assertTrue("The map disappeared after zooming.", mapsPage.isMapContainerVisible());
    }

}
