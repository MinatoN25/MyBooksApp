package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.service.BookService;
import com.stackroute.favouriteservice.util.BookConstants;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParam;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(BookConstants.ROOT_PATH)
public class BookController {

	@Autowired
	private BookService bookService;	
	
	@ApiOperation(value="getMyFavouriteBooks", notes="Get all books from my favourites")
	@ApiImplicitParam(name = "authorization", dataType = "string", paramType = "header", required=true, value = "authorization")
	@GetMapping(path = BookConstants.GET_PATH) 
	public ResponseEntity<?>  getMyFavouriteBooks(@ApiIgnore @RequestAttribute(name= BookConstants.CLAIMS) Claims claims){
		ResponseEntity<List<Book>> reponse;
		try {
		 String userId=claims.getSubject();
		 reponse=new ResponseEntity<>(bookService.getMyFavouriteBooks(userId), HttpStatus.OK);
		}catch(BookNotFoundException exception) {
			return new ResponseEntity<String>(exception.getErrorMessage(), HttpStatus.CONFLICT);
		}catch(Exception exception) {
			return new ResponseEntity<String>(BookConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
		return reponse;
	}
	
	@ApiOperation(value="saveBookToMyFavourites", notes="Adds book to my favourites")
	@ApiImplicitParam(name = "authorization", dataType = "string", paramType = "header", required=true, value = "authorization")
	@PostMapping(path = BookConstants.SAVE_PATH) 
	public ResponseEntity<?>  saveBookToMyFavourites(@ApiParam(name="Book", value="Book", required=true) @RequestBody Book book, @ApiIgnore @RequestAttribute(name= BookConstants.CLAIMS) Claims claims){
		ResponseEntity<String> reponse;
		try {
		String userId=claims.getSubject();
		book.setUserId(userId);
		bookService.saveBookToMyFavourites(book);
		reponse = new ResponseEntity<>(BookConstants.SAVE_MESSAGE, HttpStatus.CREATED);		
		}catch(BookAlreadyExistException exception) {
			return new ResponseEntity<String>(exception.getErrorMessage(), HttpStatus.CONFLICT);
		}catch(Exception exception) {
			return new ResponseEntity<String>(BookConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
		return reponse;
	}
	
	@ApiOperation(value="deleteBookFromMyFavourites", notes="Deletes book from my favourites")
	@ApiImplicitParam(name = "authorization", dataType = "string", paramType = "header", required=true, value = "authorization")
	@DeleteMapping(BookConstants.DELETE_PATH) 
	public ResponseEntity<?>  deleteBookFromMyFavourites(@ApiParam(name="Id", value="Id of the book", required=true, defaultValue="0") @PathVariable("id") int id){
		ResponseEntity<String> reponse;
		try {
			bookService.deleteBookFromMyFavourites(id);
		reponse = new ResponseEntity<>(BookConstants.DELETE_MESSAGE, HttpStatus.OK);
		}catch(BookNotFoundException e) {
			return new ResponseEntity<String>(e.getErrorMessage(), HttpStatus.CONFLICT);
		}catch(Exception exception) {
			return new ResponseEntity<String>(BookConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		}
		return reponse;
	}	
}
