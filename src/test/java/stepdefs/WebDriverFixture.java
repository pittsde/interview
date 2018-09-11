package stepdefs;

import Utilities.WebDriverUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class WebDriverFixture {

    //todo implement
    @BeforeClass
    public static void setupBe() {
        WebDriverUtils.getDriver().get("http://www.westpac.co.nz");
    }

    @Before
    public static void setup() {
        WebDriverUtils.getDriver().get("http://www.westpac.co.nz");
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                //todo screenshot management
                byte[] screenShot = ((TakesScreenshot) WebDriverUtils.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenShot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        WebDriverUtils.tearDown();
    }

    //todo implement
    @AfterClass
    public static void tearDown() {
        WebDriverUtils.tearDown();
    }

}

