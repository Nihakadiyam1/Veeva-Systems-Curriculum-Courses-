package Veeva_Integration_Program_Assesssment_3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HidingNonMatchingItemsIntheListbox {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://google.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("Selenium");
		Thread.sleep(5000);
		
		List<WebElement> suggestionsList = driver.findElements(By.xpath("//ul[@role='listbox']//li//div[@role='option']"));
		
		System.out.println(suggestionsList.size());
		
		for(WebElement suggestion : suggestionsList) {
			System.out.println(suggestion.getText());
			
			if(suggestion.getText().equals("selenium java")) {
				suggestion.click();
				break;
			}
		}
		
		
	}

}
