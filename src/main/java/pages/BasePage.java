package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Duration TIMEOUT = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void fillElement(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getNestedLocalStorageValue(String key, String nestedKey) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "const item = window.localStorage.getItem(arguments[0]);" +
                        "if (!item) return null;" +
                        "try {" +
                        "   let result = JSON.parse(item);" +
                        "   const path = arguments[1].split('.');" +
                        "   for (const segment of path) {" +
                        "       if (result && typeof result === 'object' && segment in result) {" +
                        "           result = result[segment];" +
                        "       } else {" +
                        "           return null;" +
                        "       }" +
                        "   }" +
                        "   return String(result);" +
                        "} catch (e) {" +
                        "   return null;" +
                        "}";
        return (String) js.executeScript(script, key, nestedKey);
    }

    public String getCurrentUrl() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        return driver.getCurrentUrl();
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public void handleVignetteAd() {
        if (!driver.getCurrentUrl().contains("#google_vignette")) {
            return;
        }

        final String ROBUST_IFRAME_CSS = "iframe[id*='/foreca/foreca_com-interstitial_d_0']";
        final String NESTED_IFRAME_CSS = "iframe[name='ad_iframe']";
        final String CLOSE_BUTTON_ID = "dismiss-button";

        try {
            System.out.println("Detektovana Vignette reklama (URL fragment), pokušavam zatvaranje kroz iframes...");

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(ROBUST_IFRAME_CSS)));

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(NESTED_IFRAME_CSS)));

            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CLOSE_BUTTON_ID)));
            closeButton.click();
            System.out.println("  Uspešno kliknuto na dugme za zatvaranje reklame.");

        } catch (Exception e) {
            System.out.println("Nije uspelo zatvaranje ugnežđene reklame: Iframe ili dugme nije pronađeno/vidljivo u zadatom roku.");

        } finally {
            driver.switchTo().defaultContent();
            System.out.println("Vraćen kontekst na glavni prozor.");
        }
    }

}
