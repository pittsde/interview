package stepdefs;


import Utilities.WebDriverUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.pages.BasePage;
import pageobjects.pages.CurrencyConverterPage;

public class CurrencyConverterStepDefinitions {

    private WebDriver driver = WebDriverUtils.getDriver();

    @When("^I select different source and destination currencies$")
    public void selectDifferentCurrencies() {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        converterPage.selectSourceCurrency("New Zealand Dollar");
        converterPage.selectDestinationCurrency("Euro");
    }

    @When("^I select ([^\"]*) and ([^\"]*) as the currencies$")
    public void verifyErrorMessage(String source, String destination) {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        converterPage.selectSourceCurrency(source.trim());
        converterPage.selectDestinationCurrency(destination.trim());
    }
    @When("^I enter \"([^\"]*)\" as the amount$")
    public void enterAmount(String amount) {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        converterPage.enterAmount(amount);
    }

    @When ("^I click the convert button$")
    public void clickConvert() {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        converterPage.clickConvert();
    }

    @Then("^I see a currency conversion error stating \"([^\"]*)\"$")
    public void verifyErrorMessage(String msg) {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        Assert.assertEquals(msg, converterPage.getErrorMessage());
    }

    @Then("^I see the conversion has been successful$")
    public void verifyConversion() {
        CurrencyConverterPage converterPage = PageFactory.initElements(driver, CurrencyConverterPage.class);
        Assert.assertEquals(true, converterPage.isResultsSectionDisplayed());
    }


}
