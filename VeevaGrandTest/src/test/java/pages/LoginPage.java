package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginbtn = By.id("login-button");
    private By errormsg = By.xpath("//h3[@data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Reusable wait method
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Actions
    public void enterUsername(String uname) {
        WebElement userField = waitForElement(username);
        userField.clear();
        userField.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        WebElement passField = waitForElement(password);
        passField.clear();
        passField.sendKeys(pwd);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginbtn)).click();
    }

    public void login(String uname, String pwd) {
        enterUsername(uname);
        enterPassword(pwd);
        clickLogin();
    }

    // Safe error handling
    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errormsg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMsg() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errormsg)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}