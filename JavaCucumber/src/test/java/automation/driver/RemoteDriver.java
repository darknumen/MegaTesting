package automation.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Optional;

public abstract class RemoteDriver implements DriverFactory {

    protected boolean isHeadless;
    protected URL gridUrl;

    public RemoteDriver withHeadless(boolean isHeadless) {
        this.isHeadless = isHeadless;
        return this;
    }

    public RemoteDriver withGridUrl(URL gridUrl) {
        this.gridUrl = gridUrl;
        return this;
    }

    public WebDriver buildDriver() {
        return Optional.ofNullable(gridUrl).<WebDriver>map(u -> new RemoteWebDriver(u, getCapabilities()))
                .orElseGet(()->getDriver());
    }
}