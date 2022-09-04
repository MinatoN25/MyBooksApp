package com.selenium.mybooksapp.cucumber.MyBooksAppCucumber;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {
	WebDriver driver;

	@Before
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@BeforeStep
	public void beforeEachStep() {
		System.out.println("Before each step");
	}

	@AfterStep
	public void afterEachStep() {
		System.out.println("After each step");
	}

	/*
	 * @After public void afterClass() { driver.quit();
	 * 
	 * }
	 */
	@Given("the user is on MyBooks home page")
	public void the_user_is_on_MyBooks_home_page() {
		driver.get("http://localhost:4200/login");
	}

	@When("user clicks on register")
	public void user_clicks_on_register() {
		driver.findElement(By.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[2]/span")).click();

	}

	@When("user enters the following details")
	public void user_enters_the_following_details() throws InterruptedException {
		driver.findElement(By.id("firstName")).sendKeys("Manish");
		driver.findElement(By.id("lastName")).sendKeys("Kale");
		driver.findElement(By.id("userId")).sendKeys("Pubg");
		driver.findElement(By.id("password")).sendKeys("1234");

	}

	@When("user clicks register user button")
	public void user_clicks_register_user_button() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/auth-register/div/form/div[7]/button[1]/span")).click();
	}

	@Then("user should be registered")
	public void user_should_be_registered() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), "http://localhost:4200/register");
		driver.quit();
	}

	@When("user enters user id and password")
	public void user_enters_user_id_and_password() throws InterruptedException {
		driver.findElement(By.id("userId")).sendKeys("Pubg");
		driver.findElement(By.id("password")).sendKeys("1234");

	}

	@When("user click log in button")
	public void user_click_log_in_button() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[1]")).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
	}

	@Then("user should be logged in")
	public void user_should_be_logged_in() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		assertTrue(driver.getCurrentUrl().contains("/books/home"));
		driver.quit();
	}
 
	@Given("user is logged in")
	public void user_is_logged_in() {
		driver.findElement(By.id("userId")).sendKeys("Pubg");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.xpath("/html/body/app-root/auth-login/div/form/div[3]/button[1]")).click();
		
	}
	@When("the user enters a bookname")
	public void the_user_enters_a_bookname() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(
				
				By.xpath("/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input"))
				.sendKeys("Few Things Left Unsaid");
		Thread.sleep(5000);
	}

	@When("presses enter button")
	public void clicks_the_search_button() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input")).sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver,300);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/book-dashboard/div/book-container/div/book-thumbnail[1]/mat-card/img")));
		}catch(org.openqa.selenium.TimeoutException e) {
			
		}
		}

	@Then("the basic search result should be displayed")
	public void the_basic_search_result_should_be_displayed() throws InterruptedException {
		
		assertTrue(driver.getPageSource().contains("Few Things Left Unsaid"));
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		//driver.quit();
	}
	
	
	@When("the user clicks on myprofile tab")
	public void the_user_clicks_on_myprofile_tab() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/app-root/mat-toolbar/button[3]")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
	}
	
	@When("changes user details")
	public void changes_user_details() {
		driver.findElement(By.id("firstName")).sendKeys("updated");
		driver.findElement(By.id("lastName")).sendKeys("Vaidya");
		driver.findElement(By.id("userId")).sendKeys("Pubg");
		driver.findElement(By.id("password")).sendKeys("1234");

	}
	
	@When("clicks on update profile button")
	public void clicks_on_update_profile_button() {
		driver.findElement(By.xpath("/html/body/app-root/app-my-profile/div/form/div[5]/button[1]")).click();
	}
	
	@Then("user details should be updated")
	public void user_details_should_be_updated() throws InterruptedException {
		assertTrue(driver.getPageSource().contains("updated"));
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		//driver.quit();
	}
	
	@When("clicks on add to favourite button")
	public void clicks_on_add_to_favourite_button() {
		driver.findElement(By.className("mat-button-wrapper")).click();
		
	}
	
	@Then("book should be added to favourites")
	public void book_should_be_added_to_favourites() throws InterruptedException {
		assertTrue(driver.getPageSource().contains("Few Things Left Unsaid"));
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
		//driver.quit();
	}
	@When("clicks on favourite tab")
	public void clicks_on_favourite_tab() {
		driver.findElement(By.xpath("/html/body/app-root/mat-toolbar/button[2]")).click();
	}
	@When("deletes a book from favourites")
	public void deletes_a_book_from_favourites() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/book-dashboard/div/book-container/div/book-thumbnail[1]/mat-card/mat-card-actions/button")));
			}catch(org.openqa.selenium.TimeoutException e) {
				
			}driver.findElement(By.className("mat-button-wrapper")).click();
	}
	
	@Then("book should be deleted from favourites")
	public void book_should_be_deleted_from_favourites() {
		assertFalse(driver.getPageSource().contains("Few Things Left Unsaid"));
		driver.quit();
	}
}
