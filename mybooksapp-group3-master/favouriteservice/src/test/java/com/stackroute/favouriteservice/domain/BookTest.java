package com.stackroute.favouriteservice.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	private Book book;

	@Before
	public void setUp() throws Exception {
		String [] str = {"hello", "world"};
		 book = new Book();
		 book.setTitle("book");
		 book.setUserId("2");
		 book.setFirstPublishYear(111);
		 book.setEditionCount(2);
		 book.setAuthorName(str);
		 book.setCoverImage("aa");
	}

	@Test
	public void test() {
		assertEquals("book",book.getTitle());
	}
}
