import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BookService } from '../../book.service';
import { Book } from '../../book';

@Component({
  selector: 'book-thumbnail',
  templateUrl: './thumbnail.component.html' ,
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input() book: Book;
  
  @Input() isFavourites: boolean;

  @Output() addBook = new EventEmitter();

  @Output() deleteBook = new EventEmitter();

  constructor( ) {
    
   }

  ngOnInit() {
    
  }

  addBookToFavourites(){
    this.addBook.emit(this.book);
  }

  deleteBookFromFavourites(){
    this.deleteBook.emit(this.book);
  }
}
