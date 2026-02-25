package Veeva_Integration_Program_Assesssment_3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserInstances {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String browserType = "edge";
		
		WebDriver driver = null;
		
		if(browserType.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserType.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserType.equalsIgnoreCase("firfox")) {
			driver = new FirefoxDriver();
		}
//		else if(browserType.equalsIgnoreCase("opera")) {
//			driver = new OperaChromiumDriver();
//		}
		else if(browserType.equalsIgnoreCase("safari")) {
			driver = new SafariDriver ();
		}
		else {
			System.out.println("Driver not supported");
		}
		
		//validation
		driver.get("https://www.youtube.com");
		driver.manage().window().maximize();
		
		System.out.println("Title of the Window is--> "+driver.getTitle());
	}

}
