package pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.Components.MenuSection;

public class BasePage {

    protected WebDriver driver;
    private MenuSection menuSection;
    private By pageLocator = By.id("ubermenu-ps");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitForPageToLoad();
        this.menuSection = PageFactory.initElements(driver, MenuSection.class);
    }

    public MenuSection getMenuSection() {
        return this.menuSection;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.elementToBeClickable(pageLocator));
        } catch(Exception e) {
            throw new TimeoutException("Unable to load Westpac page");
        }
    }

}
