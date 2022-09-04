package com.stackroute.favouriteservice.controller;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.stackroute.favouriteservice.domain.Book;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.RestAssured;

public class RestAssuredTests {
	
	private Book book;

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		String[] str = { "manish", "kale" };
		book= new Book("2", "Manish Kale.jpg", "Testing Books", str, 1949, 3);
		RestAssured.baseURI = "http://localhost:8082/api/books";
	}

	@Test(priority = 1)
	public void testAddBookMethod() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNTEwMDM3fQ.YMnkuK47_X4SlsoIyFkuc6ItBw7PX0xkP-RJyANQ9rU";

		given().header("Authorization", "Bearer " + token).contentType("application/json").with().body(book).when()
				.post("/saveBookToMyFavourites").then().assertThat().statusCode(201).and()
				.body(equalTo("Book successfully added to your favourites"));
	}

	@Test(priority = 2)
	public void giveMessageOnDuplicateEntries() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNTEwMDM3fQ.YMnkuK47_X4SlsoIyFkuc6ItBw7PX0xkP-RJyANQ9rU";
		given().header("Authorization", "Bearer " + token).contentType("application/json").with().body(book).when()
				.post("/saveBookToMyFavourites").then().body(equalTo("Book already available in your Favourites"));
	}
	
	@Test(priority = 3)
	public void getMyFavouriteBooks() {

		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNTEwMDM3fQ.YMnkuK47_X4SlsoIyFkuc6ItBw7PX0xkP-RJyANQ9rU";
		given().header("Authorization", "Bearer " + token).contentType("application/json").when()
				.get("/getMyFavouriteBooks").then().assertThat().statusCode(200);
	}
	
	@Test(priority = 4)
	public void testDeleteBook() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNTEwMDM3fQ.YMnkuK47_X4SlsoIyFkuc6ItBw7PX0xkP-RJyANQ9rU";
		given().header("Authorization", "Bearer " + token).contentType("application/json").when()
				.delete("/deleteBookFromMyFavourites/45").then()
				.body(equalTo("Book successfully deleted from your favourites"));
	}

	@Test(priority = 5)
	public void givenIdDoesNotExistTestDeleteBook() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNTEwMDM3fQ.YMnkuK47_X4SlsoIyFkuc6ItBw7PX0xkP-RJyANQ9rU";
		given().header("Authorization", "Bearer " + token).contentType("application/json").when()
				.delete("/deleteBookFromMyFavourites/45").then().body(equalTo("Book not found in your Favourites"));
	}
}
