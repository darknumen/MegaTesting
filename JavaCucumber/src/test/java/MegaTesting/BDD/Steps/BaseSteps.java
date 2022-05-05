package MegaTesting.BDD.Steps;

import org.openqa.selenium.WebDriver;

public class BaseSteps {

    protected WebDriver driver;
    protected ObjectContainer container;

    public BaseSteps(ObjectContainer container) {
        this.driver = container.getDriver();
        this.container = container;
    }

}
