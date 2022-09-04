package com.selenium.mybooksapp.cucumber.MyBooksAppCucumber;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;

public class UpdateProfilePage {
	static WebDriver driver;

	public UpdateProfilePage(WebDriver driver) {

		this.driver = driver;
	
	}
	
	public void updateUser() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/mat-toolbar/button[3]")).click();
		driver.findElement(By.id("firstName")).sendKeys("updated");
		driver.findElement(By.id("lastName")).sendKeys("Vaidya");
		driver.findElement(By.id("userId")).sendKeys("SePom");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.xpath("/html/body/app-root/app-my-profile/div/form/div[5]/button[1]")).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
	}

}
