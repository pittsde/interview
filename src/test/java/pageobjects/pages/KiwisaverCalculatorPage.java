package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.Components.FieldGroup;

public class KiwisaverCalculatorPage extends BasePage {


    @FindBy(how = How.CSS, using = ".wpnib-field-current-age field-group")
    private WebElement ageFieldGroup;

    @FindBy(how = How.CSS, using = ".btn-results-reveal")
    private WebElement calculateButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,\"field-group-set results\")]")
    private WebElement resultsSection;

    private String frameCssLocator = "#calculator-embed iframe";

    private String genericCssLocator = "div.field-group-set-frame div[label=\"placeholder\"]";

    private By pageLocator = By.cssSelector(".wpnib-field-current-age");

    public KiwisaverCalculatorPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad(pageLocator);
    }

    public void waitForPageToLoad(By by) {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            //we need to wait for the iframe to load before we can switch to it
            WebElement frame = driver.findElement(By.cssSelector(frameCssLocator));
            wait.until(ExpectedConditions.elementToBeClickable(frame));
        } catch(Exception e) {
            throw new TimeoutException("Unable to load Kiwisaver calc frame",e);
        }

        try {
            driver.switchTo().frame(driver.findElement(By.cssSelector(frameCssLocator)));
            WebElement elem = driver.findElement(by);
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        } catch(Exception e) {
            throw new TimeoutException("Unable to load Kiwisaver CalculatorPage page",e);
        }
    }


    public FieldGroup getAgeFieldGroup() {
         return new FieldGroup(driver, ageFieldGroup);
    }

    public FieldGroup getFieldGroupWithLabel(String label) {
        String locator = genericCssLocator.replace("placeholder",label);
        WebElement group = driver.findElement(By.cssSelector(locator));
        return new FieldGroup(driver, group);
    }

    public void clickCalculate() {
        calculateButton.click();
    }

    public boolean isResultsSetDisplayed() {
        return resultsSection.isDisplayed();
    }
}
