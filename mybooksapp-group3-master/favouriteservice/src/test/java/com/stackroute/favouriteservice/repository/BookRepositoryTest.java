
package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Book;

@RunWith(SpringRunner.class)

@DataJpaTest
public class BookRepositoryTest {


	@Autowired
	private BookRepository bookRepository;

	private Book book;

	@Before
	public void setup() {

		String [] str = {"hello", "world"};
		 book = new Book("2", "wdfg", "death note", str,1999,2);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSave() throws Exception {
		bookRepository.save(book);
		assertTrue(bookRepository.existsById(book.getId()));
		bookRepository.delete(book);
	}
	
	@Test
	public void testDeleteById() {
		bookRepository.save(book);
		assertTrue(bookRepository.existsById(book.getId()));
		bookRepository.deleteById(book.getId());
		assertFalse(bookRepository.existsById(book.getId()));
	}

	@Test
	public void getGetABookById() {
		bookRepository.save(book); 
		Optional<Book> book2 =bookRepository.findById(book.getId());
		assertThat(book2.get().equals(book));
		bookRepository.delete(book);
		
	}
	
	
	@Test
	public void testList() {
		bookRepository.save(book);
		Book book2 = new Book();
		Book book3 = new Book();
		bookRepository.save(book2);
		bookRepository.save(book3);
		List<Book> bookList = bookRepository.findAll();
		assertEquals(3, bookList.size());
		bookRepository.deleteAll();
	}
}
