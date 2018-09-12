package pageobjects.Components;

import Utilities.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MenuSection {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = ".sw-ubermenu-sections")
    private WebElement menubar;

    public MenuSection(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSectionLink(String sectionTitle, String linkTitle) {
        //todo add comments
        WebElement titleElement = driver.findElement(By.xpath("//a[contains(text(),'" + sectionTitle + "')]"));
        WebDriverUtils.hoverOverElement(titleElement);
        String fullXpath = "//a[contains(text(),'" + sectionTitle + "')]/..//a[contains(text(),'" + linkTitle + "')]";
        menubar.findElement(By.xpath(fullXpath)).click();
    }
}
