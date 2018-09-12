package pageobjects.Components;

import Utilities.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FieldGroup {

    private WebElement fieldElement;

    private WebDriver driver;

    By infoIcon = By.cssSelector(".icon");

    By dropdown = By.cssSelector(".control.select-control");

    By textField = By.cssSelector("input");

    By radioField = By.cssSelector(".radio-group");

    By infoMessage = By.cssSelector(".message-info");


    public FieldGroup(WebDriver driver, WebElement fieldElement) {
        this.driver = driver;
        this.fieldElement = fieldElement;
        waitForFieldGroupToLoad();
    }

    public void waitForFieldGroupToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            //todo check if info is always present on all elements on different pages
            WebElement elem = fieldElement.findElement(infoIcon);
            wait.until(ExpectedConditions.elementToBeClickable(elem));
        } catch (Exception e) {
            throw new TimeoutException("Unable to load Kiwisaver Field group page", e);
        }

    }

    public boolean isInfoIconPresent() {
        return fieldElement.findElement(infoIcon).isDisplayed() && fieldElement.findElement(infoIcon).isEnabled();
    }

    public void clickInfoIcon() {
        WebDriverUtils.waitForElementToBeClickable(fieldElement.findElement(infoIcon));
        fieldElement.findElement(infoIcon).click();
    }

    public boolean isInfoTextDisplayed() {
        return fieldElement.findElement(infoMessage).isDisplayed() && fieldElement.findElement(infoMessage).isEnabled();
    }

    public String getInfoText() {
        return fieldElement.findElement(infoMessage).getText();
    }

    public void setTextFieldValue(String value) {
        fieldElement.findElement(textField).sendKeys(value);
    }

    public void setDropdownValue(String value) {
        WebElement elem = fieldElement.findElement(dropdown);
        WebDriverUtils.selectToggleEntryContainingText(elem, value);
    }

    public void setRadioValue(String value) {
        WebElement elem = fieldElement.findElement(radioField);
        WebDriverUtils.selectRadioEntryContainingText(elem, value);
    }


}
