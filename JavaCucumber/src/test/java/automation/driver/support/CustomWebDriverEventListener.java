package automation.driver.support;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomWebDriverEventListener implements WebDriverEventListener {

    private static final Logger logger = Logger.getLogger(CustomWebDriverEventListener.class.getName());
    private static final Level level = Level.FINE;
    private final String testClass;
    private final String testScenario;


    public CustomWebDriverEventListener(String testClass, String testScenario) {
        this.testClass = testClass;
        this.testScenario = testScenario;

        for (Handler handler : Logger.getLogger("").getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.parse(Optional.ofNullable(System.getProperty("logging_level")).orElse("INFO")));
            }
        }
        logger.setLevel(level);
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.logp(level, testClass, testScenario, ()-> "Trying to find element located "+by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        var found = element == null ? "Couldn't find" : "Found";
        logger.logp(level, testClass, testScenario, ()-> found + " element located "+by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.logp(level, testClass, testScenario, ()-> "Clicked on element");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        logger.logp(level, testClass, testScenario, ()-> "Sent text: '"+String.join("", keysToSend)+"' to element");
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        logger.logp(level, testClass, testScenario, throwable::getMessage);
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        logger.logp(level, testClass, testScenario, ()-> "Read text: '"+text+"' from element");
    }

}
