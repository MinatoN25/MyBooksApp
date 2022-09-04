import { TestBed, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { MatSnackBar,   MatSnackBarModule,  MatButtonModule, MatCardModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContainerComponent } from './container.component';
import { BookService } from '../../book.service';
import { Book } from '../../book';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { ThumbnailComponent } from '../thumbnail/thumbnail.component';
import { By } from '@angular/platform-browser';


const testConfig={
    books:[{
        id: 1,
        cover_i:"http://image1",
        title: "Book1",
        author_name: ["Author1"],
        first_publish_year: 2019,
        edition_count: 1
    },{
        id: 2,
        cover_i:"http://image2",
        title: "Book2",
        author_name: ["Author2"],
        first_publish_year: 2019,
        edition_count: 1
    }],
    book:{
        id: 2,
        cover_i:"http://image2",
        title: "Book2",
        author_name: ["Author2"],
        first_publish_year: 2019,
        edition_count: 1
    }

}

class BookServiceStub{
    addBookToMyFavourites(book:Book){
        return Observable.of('Book added successfully');
    }
    deleteBookFromMyFavourites(book:Book){
        return Observable.of('Book deleted successfully');
    }
}

describe('Container Component',()=>{

let fixture:ComponentFixture<ContainerComponent>;
let containerComponent: ContainerComponent;
let bookService: BookService;
beforeEach(()=>{
    TestBed.configureTestingModule({
        declarations:[ContainerComponent,ThumbnailComponent],
        imports:[MatSnackBarModule,BrowserAnimationsModule,MatCardModule, MatButtonModule],
        providers:[MatSnackBar, BookService]
    }).compileComponents;
TestBed.overrideComponent(ContainerComponent,{set:{providers:[{provide:BookService,useClass:BookServiceStub}]}});

fixture=TestBed.createComponent(ContainerComponent);
containerComponent=fixture.componentInstance;
bookService=fixture.debugElement.injector.get(BookService);
});



it('should create Container component',()=>{
    expect(containerComponent).toBeTruthy();
});


it('should add book to favourites on clicking add to favourites button',()=>{
    containerComponent.isFavourites=false;
    containerComponent.books=testConfig.books;
    let spy= spyOn(bookService,"addBookToMyFavourites").and.returnValue(Observable.of("Success"));
    containerComponent.addBookToMyFavourites(testConfig.book);
    expect(spy).toHaveBeenCalled();
});

it('should delete book from favourites on clicking delete from favourites button',()=>{
    containerComponent.isFavourites=false;
    containerComponent.books=testConfig.books;
    let spy= spyOn(bookService,"deleteBookFromMyFavourites").and.returnValue(Observable.of("Success"));
    containerComponent.deleteBookFromMyFavourites(testConfig.book);
    expect(spy).toHaveBeenCalled();
});

});