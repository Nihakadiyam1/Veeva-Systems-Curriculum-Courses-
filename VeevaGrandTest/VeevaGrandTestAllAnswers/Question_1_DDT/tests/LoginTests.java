package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;
import base.BaseClass;
import pages.LoginPage;

public class LoginTests extends BaseClass{
		
	@DataProvider(name = "loginData")
	public Object[][] getExcelData() throws Exception {
	    return ExcelUtils.getExcelData(
	        System.getProperty("user.dir") + "/TestDataFolder/testdata.xlsx",
	        "Sheet1"
	    );
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password) throws InterruptedException {
	    LoginPage lp = new LoginPage(driver);
	    lp.login(username, password);

	    String currentUrl = driver.getCurrentUrl();

	    if(username.equals("standard_user") || username.equals("problem_user")|| username.equals("performance_glitch_user") || username.equals("error_user") || username.equals("visual_user")) {
	        Assert.assertTrue(currentUrl.contains("inventory"));
	        System.out.println("Login sucsess for user->" + username);
	    } else {
	        Assert.assertTrue(lp.isErrorDisplayed());
	        System.out.println("Login failed for user-> "+username);
	    }
	}
}