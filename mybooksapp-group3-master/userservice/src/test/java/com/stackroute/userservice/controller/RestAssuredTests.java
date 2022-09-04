package com.stackroute.userservice.controller;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.stackroute.userservice.domain.User;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.RestAssured;

public class RestAssuredTests {

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8081/api/books";
	}

	@Test(priority = 1)
	public void testPostMethod() {
		given().contentType("application/json").with().body(new User("light", "misa", "amane", "yagami")).when()
				.post("/registerUser").then().assertThat().statusCode(201);
		
	}

	@Test(priority = 2)
	public void EnteredValidDataUserLogin() {
		given().contentType("application/json").with().body(new User("light", "yagami")).when().post("/login").then()
				.assertThat().statusCode(200);
	}

	@Test(priority = 3)
	public void enteredValidDataUserRegistered() {
		given().contentType("application/json").with().body(new User("hey", "this", "is", "me")).when()
				.post("/registerUser").then().body(equalTo("User successfully registered to the App"));
	}
	@Test(priority = 4)
	public void enteredExistingDataErrorMessage() {
		given().contentType("application/json").with().body(new User("hey", "this", "is", "me")).when()
				.post("/registerUser").then().body(equalTo("User id already exists"));
	}
	@Test(priority = 5)
	public void updateDataSuccess() {
		given().contentType("application/json").with().body(new User("hey", "this", "isn't", "me")).when()
				.put("/my-profile").then().body(equalTo("User successfully updated"));
	}
	@Test(priority = 6)
	public void lastNameEnteredEmptyThenErrorMessage() {
		given().contentType("application/json").with().body(new User("4", "asc", "", "dafsvdb")).when()
				.post("/registerUser").then().body(equalTo("Last name is mandatory"));
	}

	@Test(priority = 7)
	public void firstNameEnteredEmptyThenErrorMessage() {
		given().contentType("application/json").with().body(new User("4", "", "x", "dafsvdb")).when()
				.post("/registerUser").then().body(equalTo("First name is mandatory"));
	}

	@Test(priority = 8)
	public void enteredInvalidCredentialsShowErrorMessage() {
		given().contentType("application/json").with().body(new User("1", "")).when().post("/login").then()
				.body(equalTo("User Id and Password are mandatory"));
	}

}
