package MegaTesting.Model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage<HomePage> {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void SetLoginAndEnter(String user, String password) {
        try
        {
            Thread.sleep(1000);
            LoginButton().click();
            UsernameField().sendKeys(user);
            PasswordField().sendKeys(password);
            ClickSubmitButton();
            Thread.sleep(6000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

    }

    public WebElement LoginButton(){
        return driver.findElement(By.xpath("//*[@id=\"startholder\"]/div[2]/div/div[1]/section[1]/div[4]/button[2]"));
    }

    public WebElement UsernameField(){
        return driver.findElement(By.id("login-name2"));
    }

    public WebElement PasswordField(){
        return driver.findElement(By.id("login-password2"));
    }

    public void ClickSubmitButton(){
        driver.findElement(By.xpath("//*[@id=\"login_form\"]/button")).click();
    }
}
