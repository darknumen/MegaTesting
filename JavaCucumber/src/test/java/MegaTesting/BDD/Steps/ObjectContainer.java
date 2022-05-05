package MegaTesting.BDD.Steps;


import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;


public class ObjectContainer {

    private WebDriver driver;
    private Map<String, Object> context = new HashMap<String, Object>();

    public <V> void register(String key, V value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <V> V retrieve(String key) {
        return (V) context.get(key);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
