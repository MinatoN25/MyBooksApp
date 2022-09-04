
 
package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.service.BookServiceImpl;


@RunWith(SpringRunner.class)

@WebMvcTest(BookController.class)
public class BookControllerTest {
	@Autowired
	private  MockMvc mockMvc;

	@MockBean
	private   BookServiceImpl bookServiceImpl;

	private  Book book;
	private  Book book1;
	@InjectMocks
	private BookController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		String [] str = {"hello", "world"};
		 book = new Book("2", "wdfg", "death note", str,1999,2);
		 book1 =new Book("olaf", null, null, null,0,0);
	}

	@Test
	public void testSaveSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNDMyNzQ2fQ.e7LCHxsLv320HCIoL_n3eZVuw7mDQfFVomfX8BnfBtM";
		when(bookServiceImpl.saveBookToMyFavourites(book)).thenReturn(true);
		mockMvc.perform(post("/api/books/saveBookToMyFavourites").header("Authorization","Bearer "+token).content(jsonToString(book))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

	}

	
	@Test
	public void testDelete() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNDMyNzQ2fQ.e7LCHxsLv320HCIoL_n3eZVuw7mDQfFVomfX8BnfBtM";
		when(bookServiceImpl.deleteBookFromMyFavourites(book.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/books/deleteBookFromMyFavourites/{id}", book.getId()).header("Authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void testGetFavouriteBooksByUserId() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjEyNDMyNzQ2fQ.e7LCHxsLv320HCIoL_n3eZVuw7mDQfFVomfX8BnfBtM";
		List<Book> book = new ArrayList<>();
		when(bookServiceImpl.getMyFavouriteBooks("2")).thenReturn(book);
		mockMvc.perform(get("/api/books/getMyFavouriteBooks").header("Authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	public static String jsonToString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
