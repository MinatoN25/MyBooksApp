import { Component, OnInit } from '@angular/core';
import { BookService } from '../../book.service';
import { Book } from '../../book';

export interface SearchValues {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'book-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  books: Book[];
  isFavourites: boolean;
  selectedValue: string;

  searchValues: SearchValues[] = [
    {value: 'q', viewValue: 'All'},
    {value: 'title', viewValue: 'Title'},
    {value: 'author', viewValue: 'Author'}
  ];

  constructor(private bookService: BookService) { 
    this.selectedValue='q';
  }

  ngOnInit() {
  }

  onEnter(searchKey){
    this.isFavourites=false;
    this.bookService.searchBooks(searchKey,this.selectedValue).subscribe(books=> this.books=books);
  }
}
