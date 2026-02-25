package Veeva_Integration_Program_Assesssment_3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Next_Genartion_Automation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//1. URL
        driver.get("https://www.nextgenerationautomation.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //2.NAVIGATION
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Log In / SignUp']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Log In']"))).click();
        
        //Login page
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Log in with Email')]"))).click();

        //3. Enter credentials
        
        //Email
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        email.sendKeys("harikakadiyam852@gmail.com");

        //Password
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("Harika@123");

        //4. Click Login
        driver.findElement(By.xpath("//span[normalize-space()='Log In']")).click();

        //5. Validation
        
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Success!')]")));
        String actualText = successMessage.getText();
        
        if (actualText.contains("Your member signup request has been sent")) {
            System.out.println("Login Validation Successful");
        } else {
            System.out.println("Login Validation Failed");
        }
	}
}