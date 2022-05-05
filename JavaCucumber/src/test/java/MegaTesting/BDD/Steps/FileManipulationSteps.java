package MegaTesting.BDD.Steps;

import MegaTesting.Model.Pages.Data.LoginData;
import MegaTesting.Model.Pages.HomePage;
import MegaTesting.Model.Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FileManipulationSteps extends BaseSteps{

    public FileManipulationSteps(ObjectContainer container) {
        super(container);
    }

    @Given("I am registered user and logged in")
    public void aUserWithValidCredentials() {
        container.register("loginData", new LoginData().WithUsername(System.getenv("SELENIUM_USERNAME")).WithPassword(System.getenv("SELENIUM_PASSWORD")));

        LoginData loginData = container.retrieve("loginData");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.SetLoginAndEnter(loginData.Username, loginData.Password);
    }

    @When("the user creates a txt file on the site named {string} and containing text {string}")
    public void theUserCreatesATxtFileOnTheSite(String filename, String content) {
        HomePage home = new HomePage(driver);
        home.ClickFileCreate();
        home.CreateNewFile(filename);
        home.NewFileMessageAndSave(content);
    }

    @Then("the user should be able to successfully create it and verify the name on the cloud file list as {string}")
    public void theUserShouldBeAbleToSuccessfullyCreateIt(String filename) {
        HomePage home = new HomePage(driver);
        Assert.assertTrue("File should be present: " + filename, home.FileExist(filename, "cloud"));
    }

    @When("the user deletes a txt file named {string}")
    public void theUserDeletesATxtFileNamedATxt(String filename) {
        HomePage home = new HomePage(driver);
        Assert.assertTrue("File that should be deleted: " + filename, home.FileExist(filename, "cloud"));
        home.ClickFileOptions(filename, "cloud");
        home.DeleteFile();
    }

    @Then("the user should be able to successfully delete the file {string} from the cloud file list")
    public void theUserShouldBeAbleToSuccessfullyDeleteIt(String filename) {
        HomePage home = new HomePage(driver);
        Assert.assertFalse("File should not be present: " + filename, home.FileExist(filename, "cloud"));
    }

    @When("the user restores the deleted file {string} from the Rubbish Bin")
    public void theUserRestoresTheDeletedFileATxtFromTheRubbishBin(String filename) {
        HomePage home = new HomePage(driver);
        home.ClickRubbishBin();
        Assert.assertTrue("File should be present: " + filename, home.FileExist(filename, "rubbish"));
        home.ClickFileOptions(filename, "rubbish");
        home.RestoreFile();
    }

    @Then("the user should be able to successfully restore the file and seen on the cloud file list as {string}")
    public void theUserShouldBeAbleToSuccessfullyRestoreTheFile(String filename) {
        HomePage home = new HomePage(driver);
        Assert.assertTrue("File should be present: " + filename, home.FileExist(filename, "cloud"));
    }
}
