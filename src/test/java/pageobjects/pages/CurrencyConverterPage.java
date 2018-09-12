package pageobjects.pages;

import Utilities.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.pages.BasePage;


public class CurrencyConverterPage extends BasePage {

    @FindBy(id = "ConvertFrom")
    private WebElement sourceCurrency;

    @FindBy(id = "ConvertTo")
    private WebElement destinationCurrency;

    @FindBy(id = "Amount")
    private WebElement amount;

    @FindBy(id = "convert")
    private WebElement convertButton;

    @FindBy(id = "errordiv")
    private WebElement errorSection;

    @FindBy(id = "resultsdiv")
    private WebElement resultsSection;

    private By byPageLocator = By.name("ConvertFrom");

    public CurrencyConverterPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad(byPageLocator);
    }

    public void waitForPageToLoad(By by) {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            //we need to wait for the iframe to load before we can switch to it
            WebElement frame = driver.findElement(By.id("westpac-iframe"));
            wait.until(ExpectedConditions.elementToBeClickable(frame));
        } catch (Exception e) {
            throw new TimeoutException("Unable to load Westpac frame", e);
        }

        try {
            driver.switchTo().frame("westpac-iframe");
            WebElement elem = driver.findElement(by);
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        } catch (Exception e) {
            throw new TimeoutException("Unable to load FX converter page", e);
        }
    }

    public boolean isErrorMessageDisplayed() {
        return errorSection.isDisplayed() && errorSection.isEnabled();
    }

    public String getErrorMessage() {
        return errorSection.getText();
    }

    public boolean isResultsSectionDisplayed() {
        return resultsSection.isEnabled() && resultsSection.isDisplayed();
    }

    public String getResults() {
        return resultsSection.getText();
    }

    public void selectSourceCurrency(String currency) {
        WebDriverUtils.selectDropdownEntryContainingText(sourceCurrency, currency);
    }

    public void selectDestinationCurrency(String currency) {
        WebDriverUtils.selectDropdownEntryContainingText(destinationCurrency, currency);
    }

    public void enterAmount(String val) {
        amount.sendKeys(val);
    }

    public void clickConvert() {
        convertButton.click();
    }
}
