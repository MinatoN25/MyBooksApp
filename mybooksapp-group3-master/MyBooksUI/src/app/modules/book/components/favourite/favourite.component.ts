import { Component, OnInit } from '@angular/core';
import { Book } from '../../book';
import { BookService } from '../../book.service';


@Component({
  selector: 'book-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: []
})
export class FavouriteComponent implements OnInit {
 
  books: Book[];
  isFavourites: boolean;

  constructor(private bookService: BookService) {
    this.books=[];
   }

  ngOnInit() {
    this.bookService.getBooksFromMyFavourites().subscribe((books)=>{
      
      this.books=books;
      this.isFavourites=true;

    });
  }

}
