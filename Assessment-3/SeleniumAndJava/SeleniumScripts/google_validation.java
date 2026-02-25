package Veeva_Integration_Program_Assesssment_3;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class google_validation {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.google.com");

        //title
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedTitle = "Google";

        // validation
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title Verification Passed");
        } else {
            System.out.println("Title Verification Failed");
        }

        Thread.sleep(2000);
        //Current URL
        String currentURL = driver.getCurrentUrl();

        // validating that the redirected URL contains google.co.in
        if (currentURL.contains("google.co.in")) {
            System.out.println("URL Redirect Verification Passed");
        } else {
            System.out.println("URL Redirect Verification Failed");
        }

        driver.quit();
    }
}