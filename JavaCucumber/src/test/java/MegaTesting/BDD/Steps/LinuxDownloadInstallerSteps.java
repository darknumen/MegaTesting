package MegaTesting.BDD.Steps;

import MegaTesting.Model.Pages.DownloadPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;

public class LinuxDownloadInstallerSteps extends BaseSteps{

    public LinuxDownloadInstallerSteps(ObjectContainer container) {
        super(container);
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver.navigate().to(url);
    }

    @Then("I should be able to download any Linux Distro installer successfully")
    public void iShouldBeAbleToDownloadAnyLinuxDistroInstallerSuccessfully() {
        DownloadPage download = new DownloadPage(driver);
        ArrayList<String> brokenLinuxLinks = download.VerifyDownloadDistros();
        Assert.assertTrue("Broken Linux Distro links are:" + brokenLinuxLinks.toString(), brokenLinuxLinks.isEmpty());
    }
}
