package stepdefs;

import Utilities.WebDriverUtils;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.pages.BasePage;

public class NavigationStepDefinitions {

    private WebDriver driver = WebDriverUtils.getDriver();

    @When("^I click the \"([^\"]*)\" link under the \"([^\"]*)\" heading in the menu bar$")
    public void clickLinkWithNameUnderHeadingWithName(String linkText, String heading) {
        BasePage basePage = new BasePage(driver);
        basePage.getMenuSection().clickSectionLink(heading, linkText);
    }

    @When("^I click the link with text \"([^\"]*)\"$")
    public void clickLinkWithText(String linkText) {
        WebDriverUtils.clickLinkText(linkText);
    }
}
