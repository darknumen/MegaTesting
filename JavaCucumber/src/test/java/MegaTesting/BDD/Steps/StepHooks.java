package MegaTesting.BDD.Steps;

import automation.driver.AbstractDriverFactory;
import automation.driver.support.CustomWebDriverEventListener;
import automation.environment.EnvironmentVariables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;


public class StepHooks {

    private final ObjectContainer objectContainer;


    public StepHooks(ObjectContainer objectContainer) {
        this.objectContainer = objectContainer;
    }


    @Before
    public void setup(Scenario scenario) throws Exception {
        WebDriver driver = new EventFiringWebDriver(
                new AbstractDriverFactory()
                        .withHeadless(EnvironmentVariables.isHeadless())
                        .withGridUrl(EnvironmentVariables.getGridUrl())
                        .getInstance(EnvironmentVariables.getBrowser())
        ).register(new CustomWebDriverEventListener(scenario.getClass().getName(), scenario.getName()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvironmentVariables.getImplicitWait()));
        driver.manage().window().maximize();
        driver.navigate().to(EnvironmentVariables.getUrl());

        this.objectContainer.setDriver(driver);
    }

    @After
    public void cleanup() {
        objectContainer.getDriver().quit();
    }

}

