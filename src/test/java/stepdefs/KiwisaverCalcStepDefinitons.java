package stepdefs;

import Utilities.WebDriverUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.Components.FieldGroup;
import pageobjects.pages.BasePage;
import pageobjects.pages.KiwisaverCalculatorPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KiwisaverCalcStepDefinitons {

    private WebDriver driver = WebDriverUtils.getDriver();

    private String ageGroupLabel = "Current age";
    private String employmentStatusLabel = "Employment status";
    private String salaryLabel = "Salary or wages per year (before tax)";
    private String contribPercentLabel = "KiwiSaver member contribution";
    private String pirPercentLabel = "Prescribed investor rate (PIR)";
    private String currentBalanceLabel = "Current KiwiSaver balance";
    private String voluntaryContributionsLabel = "Voluntary contributions";
    private String riskProfileLabel = "Risk profile";
    private String savingsGoalLabel = "Savings goal at retirement";

    @Given("I have entered the KiwiSaver calculator inputs ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*) ([^\"]*)$")
    public void enterCalcInputs(String age, String status, String salary, String contribPercent, String pirRate, String profile, String balance, String contribAmount, String contribFreq, String savingsGoal) {
        KiwisaverCalculatorPage calcPage = PageFactory.initElements(driver, KiwisaverCalculatorPage.class);

        FieldGroup ageSection = calcPage.getFieldGroupWithLabel(ageGroupLabel);
        setTextValueOnFieldGroup(ageSection, age);

        FieldGroup empStatusSection = calcPage.getFieldGroupWithLabel(employmentStatusLabel);
        setDropdownValueOnFieldGroup(empStatusSection, status);

        if (!salary.equals("")) {
            FieldGroup salarySection = calcPage.getFieldGroupWithLabel(salaryLabel);
            setTextValueOnFieldGroup(salarySection, salary);
        }

        if (!contribPercent.equals("")) {
            FieldGroup contribPercentSection = calcPage.getFieldGroupWithLabel(contribPercentLabel);
            setRadioValueOnFieldGroup(contribPercentSection, contribPercent);
        }

        FieldGroup pirSection = calcPage.getFieldGroupWithLabel(pirPercentLabel);
        setDropdownValueOnFieldGroup(pirSection, pirRate);

        FieldGroup profileSection = calcPage.getFieldGroupWithLabel(riskProfileLabel);
        setRadioValueOnFieldGroup(profileSection, profile);

        FieldGroup balanceSection = calcPage.getFieldGroupWithLabel(currentBalanceLabel);
        setTextValueOnFieldGroup(balanceSection, balance);

        FieldGroup contribSection = calcPage.getFieldGroupWithLabel(voluntaryContributionsLabel);
        setTextValueOnFieldGroup(contribSection, contribAmount);
        setDropdownValueOnFieldGroup(contribSection, contribFreq);

        FieldGroup goalSection = calcPage.getFieldGroupWithLabel(savingsGoalLabel);
        goalSection.setTextFieldValue(savingsGoal);
    }

    public void setTextValueOnFieldGroup(FieldGroup fieldGroup, String value) {
        if (!value.equals("")) {
            fieldGroup.setTextFieldValue(value);
        }
    }

    public void setRadioValueOnFieldGroup(FieldGroup fieldGroup, String value) {
        if (!value.equals("")) {
            fieldGroup.setRadioValue(value);
        }
    }

    public void setDropdownValueOnFieldGroup(FieldGroup fieldGroup, String value) {
        if (!value.equals("")) {
            fieldGroup.setDropdownValue(value);
        }
    }

    @When("^I click the information icon beside the \"([^\"]*)\" field$")
    public void clickInfoIconOnAgeField(String groupLabel) {
        KiwisaverCalculatorPage calcPage = PageFactory.initElements(driver, KiwisaverCalculatorPage.class);
        FieldGroup ageSection = calcPage.getFieldGroupWithLabel(groupLabel);
        ageSection.clickInfoIcon();
    }

    @Then("^I see the \"([^\"]*)\" information message stating \"([^\"]*)\"$")
    public void verifyInfoMessage(String groupLabel, String expectedText) {
        KiwisaverCalculatorPage calcPage = PageFactory.initElements(driver, KiwisaverCalculatorPage.class);
        FieldGroup ageSection = calcPage.getFieldGroupWithLabel(groupLabel);
        assertEquals(expectedText, ageSection.getInfoText());
        assertTrue(ageSection.isInfoTextDisplayed());
    }

    @When("^I click the calculate button on the KiwiSaver calculator page$")
    public void clickCalculateButton() {
        KiwisaverCalculatorPage calcPage = PageFactory.initElements(driver, KiwisaverCalculatorPage.class);
        calcPage.clickCalculate();
    }

    @Then("^I see KiwiSaver calculator results are displayed$")
    public void verifyResultsDisplayed() {
        KiwisaverCalculatorPage calcPage = PageFactory.initElements(driver, KiwisaverCalculatorPage.class);
        assertTrue("Results set is displayed", calcPage.isResultsSetDisplayed());
    }

}
