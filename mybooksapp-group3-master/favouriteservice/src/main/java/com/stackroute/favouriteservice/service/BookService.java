package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;

public interface BookService {
	
	public boolean saveBookToMyFavourites(Book book) throws BookAlreadyExistException;
	
	public boolean deleteBookFromMyFavourites(int id) throws BookNotFoundException;
	
	public List<Book> getMyFavouriteBooks(String bookId) throws BookNotFoundException;		
}
