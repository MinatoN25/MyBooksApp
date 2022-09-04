package com.selenium.mybooksapp.POM;

import static org.testng.Assert.assertEquals;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.mybooksapp.cucumber.MyBooksAppCucumber.LoginPage;


public class LoginPageTests {
	static WebDriver webDriver;

	@BeforeClass
	public static void initDriver() {
		System.out.println("Inside the init() method.....");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		webDriver = new ChromeDriver();

		webDriver.get("http://localhost:4200/login");
		
		webDriver.manage().window().maximize();


	}
	
	@AfterClass
	public static void destroy() {
		webDriver.quit();
	}
	
	@Test
	public void loginUser() throws InterruptedException {
		LoginPage  lu = new LoginPage (webDriver);
		lu.loginUser();
		webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(5000);
		assertEquals(webDriver.getCurrentUrl(), "http://localhost:4200/books/home");
		
	}
	
}
