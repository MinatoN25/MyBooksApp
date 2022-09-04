package com.selenium.mybooksapp.cucumber.MyBooksAppCucumber;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	static WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}
	public void loginUser() throws InterruptedException {
		driver.findElement(By.id("userId")).sendKeys("SePom");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[1]")).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
	}

}
