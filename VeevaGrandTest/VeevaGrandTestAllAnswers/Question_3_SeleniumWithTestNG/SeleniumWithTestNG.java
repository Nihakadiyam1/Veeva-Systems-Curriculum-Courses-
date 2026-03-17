package SeleniumWithTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumWithTestNG {

    WebDriver driver;

    // Runs before each test method
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    // Test case 1
    @Test(priority=1)
    public void loginTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String url = driver.getCurrentUrl();
        if (url.contains("inventory")) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
    }

    // Test case 2
    @Test(priority=2)
    public void invalidLoginTest() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        System.out.println("Error Message: " + errorMsg);
    }

    // Runs after each test method
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}