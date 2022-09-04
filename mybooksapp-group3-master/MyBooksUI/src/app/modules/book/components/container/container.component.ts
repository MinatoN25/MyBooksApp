import { Component, OnInit, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Book } from '../../book';
import { BookService } from '../../book.service';

@Component({
  selector: 'book-container',
  templateUrl: './container.component.html'   ,
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input() books: Book[];
  @Input() isFavourites: boolean;

  constructor(private bookService: BookService , private snackBar: MatSnackBar ) { 
   
  }

  ngOnInit() {
     }

     addBookToMyFavourites(book: Book){
      this.bookService.addBookToMyFavourites(book).subscribe((resp)=>{
        this.snackBar.open(`${book.title} added to your favourites`,'',{duration:1000});
      },(err)=>{
        this.snackBar.open(err.error,'',{duration:1000})      
      });
    }

    deleteBookFromMyFavourites(book: Book){
      this.books.splice(this.books.indexOf(book),1);
      this.bookService.deleteBookFromMyFavourites(book.id).subscribe((resp)=>{
        this.snackBar.open(`${book.title} deleted from your favourites`,'',{duration:1000});
      });
    }

}
