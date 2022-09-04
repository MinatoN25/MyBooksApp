package com.stackroute.favouriteservice.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.stackroute.favouriteservice.exception.BookAlreadyExistException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.repository.BookRepository;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	private Book book;

	Optional<Book> options;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		String[] str = { "hello", "worl" };
		book = new Book("2", "new2", "another 2", str, 1939, 5);
		options = Optional.of(book);

	}

	@Test
	public void testSave() {
		assertEquals(true,bookServiceImpl.saveBookToMyFavourites(book));
		verify(bookRepository, times(1)).findByUserIdAndTitle(book.getUserId(), book.getTitle());
	}

	@Test(expected = BookAlreadyExistException.class)
	public void testSaveFail(){
		when(bookRepository.findByUserIdAndTitle(book.getUserId(), book.getTitle())).thenReturn(options);
		boolean flag = bookServiceImpl.saveBookToMyFavourites(book);
		assertEquals(false, flag);
		verify(bookRepository, times(0)).save(book);
		verify(bookRepository, times(1)).findByUserIdAndTitle(book.getUserId(), book.getTitle());
	}

	@Test
	public void testDelete() throws BookNotFoundException {
		when(bookRepository.findById(book.getId())).thenReturn(options);
		doNothing().when(bookRepository).delete(book);
		boolean flag = bookServiceImpl.deleteBookFromMyFavourites(book.getId());
		assertEquals(true, flag);
		verify(bookRepository, times(1)).delete(book);
		verify(bookRepository, times(1)).findById(book.getId());
	}

	@Test(expected = BookNotFoundException.class)
	public void testList() throws BookNotFoundException {
		List<Book> booksList = new ArrayList<>();
		when(bookRepository.findByUserId("2")).thenReturn(booksList);
		List<Book> booksList2 = bookServiceImpl.getMyFavouriteBooks("2");
		assertEquals(booksList, booksList2);
		verify(bookRepository, times(1)).findByUserId("2");
	}
}
