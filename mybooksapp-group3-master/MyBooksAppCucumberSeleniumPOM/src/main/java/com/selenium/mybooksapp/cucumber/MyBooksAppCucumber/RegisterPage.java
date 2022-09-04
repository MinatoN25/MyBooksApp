package com.selenium.mybooksapp.cucumber.MyBooksAppCucumber;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {
	static WebDriver driver;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;

	}
	public void registerUser() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[2]/span")).click();
		driver.findElement(By.id("firstName")).sendKeys("Riya");
		driver.findElement(By.id("lastName")).sendKeys("Vaidya");
		driver.findElement(By.id("userId")).sendKeys("SePom");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.xpath("/html/body/app-root/auth-register/div/form/div[7]/button[1]/span")).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
	}
}