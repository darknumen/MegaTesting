package MegaTesting.Model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadPage extends BasePage<HomePage> {

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public WebElement DownloadLinux() {
        return driver.findElement(By.xpath("//*[@id=\"startholder\"]/div[2]/div[2]/div/div[2]/div/div[1]/div/div[4]/a[3]"));
    }

    //1 - 31
    public WebElement LinuxDistro(int num) {
        return driver.findElement(By.xpath("//*[@id=\"startholder\"]/div[2]/div[2]/div/div[2]/div/div[1]/div/div[6]/div/div[1]/div[2]/div/div/div/div[" + Integer.toString(num) + "]"));
    }

    public ArrayList<String> VerifyDownloadDistros() {
        DownloadLinux().click();

        ArrayList<String> brokenLinuxDistros = new ArrayList<String>();

        for(int num = 1; num <= 31; num++)
        {
            String linuxDistroUrl = LinuxDistro(num).getAttribute("data-link").toString();

            HttpURLConnection request = null;
            int respCode = 200;

            try {
                request = (HttpURLConnection) (new URL(linuxDistroUrl).openConnection());
                request.setRequestMethod("HEAD");
                request.connect();

                respCode = request.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(linuxDistroUrl + " is a broken link");
                    brokenLinuxDistros.add(linuxDistroUrl);
                } else {
                    System.out.println(linuxDistroUrl + " is a valid link");
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return brokenLinuxDistros;
    }
}
