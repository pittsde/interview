package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            setup();
        }
        return driver;
    }

    public static void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void tearDown() {
        driver.close();
        driver = null;
    }

    public static void hoverOverElement(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public static void selectDropdownEntryContainingText(WebElement element, String val) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(val);
    }

    //dropdown with KiwiSaver calculator is different to standard dropdown
    public static void selectToggleEntryContainingText(WebElement element, String val) {
        waitForElementToBeClickable(element);
        element.click();
        String xpath = "//*[contains(text(),'placeholder')]";
        xpath = xpath.replace("placeholder", val);
        element.findElement(By.xpath(xpath)).click();
    }

    //to be used on a radio within Kiwisaver calculator and screens that use similar components
    public static void selectRadioEntryContainingText(WebElement element, String val) {
        waitForElementToBeClickable(element);
        for (WebElement elem :element.findElements(By.cssSelector(".input-label"))){
            if (elem.getText().equals(val)) {
                elem.click();
            }
        }
    }

    public static void clickLinkText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public static void waitForElementToBeClickable(WebElement elem) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        } catch(Exception e) {
            throw new TimeoutException("Element did not become clickable",e);
        }
    }

    public static void waitForPageToLoad(By by) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement elem = driver.findElement(by);
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        } catch(Exception e) {
            throw new TimeoutException("Unable to load Westpac page",e);
        }
    }
}
