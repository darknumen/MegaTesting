package MegaTesting.Model.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement CloudDrive(){
        return driver.findElement(By.xpath("//*[@id=\"fmholder\"]/div[4]/div[1]/div[5]/div[31]/div[2]/div"));
    }

    public WebElement NewTextFileOption(){
        return driver.findElement(By.xpath("//*[@id=\"fmholder\"]/div[3]/div[11]/a/span"));
    }

    public WebElement NewFilenameField(){
        return driver.findElement(By.name("dialog-new-file"));
    }

    public WebElement NewFileDialogCreateButton(){
        return driver.findElement(By.xpath("//*[@id=\"bodyel\"]/section[5]/div[9]/footer/div/button[2]"));
    }

    public WebElement CodeMirrorSave(){
        return driver.findElement(By.xpath("//*[@id=\"bodyel\"]/section[3]/div/footer/button[2]"));
    }

    public WebElement CodeMirrorClose(){
        return driver.findElement(By.xpath("//*[@id=\"bodyel\"]/section[3]/div/header/nav/ul[2]/li[2]/button/i"));
    }

    public WebElement RemoveFileOption(){
        return driver.findElement(By.xpath("//*[@id=\"fmholder\"]/div[3]/div[15]/a[2]/span"));
    }

    public WebElement RestoreFileOption(){
        return driver.findElement(By.xpath("//*[@id=\"fmholder\"]/div[3]/div[9]/a[5]/span"));
    }

    public WebElement ConfirmDeleteYes(){
        return driver.findElement(By.xpath("//*[@id=\"msgDialog\"]/footer/div/div/button[2]"));
    }

    public WebElement RubbishBin(){
        return driver.findElement(By.xpath("//*[@id=\"fmholder\"]/div[4]/div[1]/div[1]/section[1]/div[2]/div[1]/button[3]"));
    }

    public WebElement RubbishBinTable(){
        return driver.findElement(By.xpath("/html/body/div[7]/div[4]/div[1]/div[5]/div[31]/div[2]/div/table/tbody"));
    }

    public WebElement CloudDriveTable(){
        return driver.findElement(By.xpath("/html/body/div[7]/div[4]/div[1]/div[5]/div[31]/div[2]/div[1]/table/tbody"));
    }

    public void ClickFileCreate() {
        Actions actions = new Actions(driver);
        WebElement elementLocator = CloudDrive();
        actions.contextClick(elementLocator).perform();
    }

    public void CreateNewFile(String filename){
        NewTextFileOption().click();
        NewFilenameField().sendKeys(filename);
        NewFileDialogCreateButton().click();
    }

    public void NewFileMessageAndSave(String message){
        try
        {
            Thread.sleep(2000);
            WebElement codeMirror = driver.findElement(By.className("CodeMirror"));
            WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-line")).get(0);
            codeLine.click();
            WebElement textarea = codeMirror.findElement(By.cssSelector("textarea"));
            textarea.sendKeys(message);

            CodeMirrorSave().click();
            Thread.sleep(7000);
            CodeMirrorClose().click();
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void ClickFileOptions(String filename, String location){
        Actions actions = new Actions(driver);
        WebElement elementLocator = ReturnFileElement(filename, location);
        actions.contextClick(elementLocator).perform();
    }

    public void DeleteFile() {
        try
        {
            RemoveFileOption().click();
            ConfirmDeleteYes().click();
            Thread.sleep(2000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void ClickRubbishBin(){
        try
        {
            RubbishBin().click();
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void RestoreFile() {
        try
        {
            RestoreFileOption().click();
            Thread.sleep(2000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public boolean FileExist(String filename, String location)
    {
       if(ReturnFileElement(filename, location) == null)
           return false;
       else
           return true;
    }

    public WebElement ReturnFileElement(String filename, String location) {
        WebElement fileCell = null;

        List<WebElement> r_table = null;
        String b_xpath = "";
        String a_xpath = "]/td[3]/span[2]";
        int tableOffset = 1;

        if(location.contains("cloud")) {
            r_table = CloudDriveTable().findElements(By.tagName("tr"));
            b_xpath = "/html/body/div[7]/div[4]/div[1]/div[5]/div[31]/div[2]/div[1]/table/tbody/tr[";
            a_xpath = "]/td[3]/span[2]";
            tableOffset++;
        }
        else if(location.contains("rubbish"))
        {
            r_table = RubbishBinTable().findElements(By.tagName("tr"));
            b_xpath = "/html/body/div[7]/div[4]/div[1]/div[5]/div[31]/div[2]/div/table/tbody/tr[";
        }

        int rs_c = r_table.size() - tableOffset;

        for (int r = 2; r <= rs_c; r++) {
            String n = driver.findElement(By.xpath(b_xpath + r + a_xpath)).getText();
            if (n.contains(filename)) {
                // get text of matching cell
                fileCell = driver.findElement(By.xpath(b_xpath + r + a_xpath));
                break;
            }
        }

        return fileCell;
    }
}
