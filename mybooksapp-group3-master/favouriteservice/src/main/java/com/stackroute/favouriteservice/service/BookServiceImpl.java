package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	public boolean saveBookToMyFavourites(Book book) {

		Optional<Book> bookToSave = bookRepository.findByUserIdAndTitle(book.getUserId(),book.getTitle());
		if(bookToSave.isPresent()) {throw new BookAlreadyExistException("Book already available in your Favourites");}
		bookRepository.save(book);
		return true;

	}

	public boolean deleteBookFromMyFavourites(int id) {
		Optional<Book> book = bookRepository.findById(id);
		if(!book.isPresent()) {throw new BookNotFoundException("Book not found in your Favourites");}
		bookRepository.delete(book.get());
		return true;

	}

	public List<Book> getMyFavouriteBooks(String userId) {
		List<Book> book = bookRepository.findByUserId(userId);
		if(null == book || book.isEmpty()) {throw new BookNotFoundException("No Books found in your Favourites");}
		return 	book;
	}
}
