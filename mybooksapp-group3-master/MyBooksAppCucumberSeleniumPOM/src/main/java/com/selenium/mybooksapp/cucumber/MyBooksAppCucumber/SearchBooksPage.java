package com.selenium.mybooksapp.cucumber.MyBooksAppCucumber;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchBooksPage {
	static WebDriver driver;

	public SearchBooksPage(WebDriver driver) {

		this.driver = driver;
	}
	public void searchBooks() {
		driver.findElement(	By.xpath("/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input"))
				.sendKeys("Few Things Left Unsaid");
		driver.findElement(By.xpath("/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input")).sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver,100);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/book-dashboard/div/book-container/div/book-thumbnail[1]/mat-card/img")));
		}catch(org.openqa.selenium.TimeoutException e) {
			
		}


	}
}