import { TestBed, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { MatSnackBar,   MatSnackBarModule,  MatButtonModule, MatCardModule, MatDialogModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FavouriteComponent } from './favourite.component';
import { ContainerComponent } from '../container/container.component';
import { BookService } from '../../book.service';
import { Book } from '../../book';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { ThumbnailComponent } from '../thumbnail/thumbnail.component';
import { By } from '@angular/platform-browser';


class BookServiceStub{
    getBooksFromMyFavourites(){
        return Observable.of(testConfig.books);
    }
    
}
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
    }]
}

describe('Favourite Component',()=>{

    let fixture:ComponentFixture<FavouriteComponent>;
    let favouriteComponent: FavouriteComponent;
    let bookService: BookService;
    beforeEach(()=>{
        TestBed.configureTestingModule({
            declarations:[FavouriteComponent,ThumbnailComponent,ContainerComponent],
            imports:[MatSnackBarModule,BrowserAnimationsModule,MatCardModule, MatButtonModule, MatDialogModule],
            providers:[MatSnackBar, BookService]
        }).compileComponents;
    TestBed.overrideComponent(FavouriteComponent,{set:{providers:[{provide:BookService,useClass:BookServiceStub}]}});
    
    fixture=TestBed.createComponent(FavouriteComponent);
    favouriteComponent=fixture.componentInstance;
    bookService=fixture.debugElement.injector.get(BookService);
    });


    it('should create instance of favourite component',()=>{
        expect(favouriteComponent).toBeTruthy();
    });

    it('should set isFavourites property be true',()=>{
        fixture.detectChanges();
        expect(favouriteComponent.isFavourites).toBeTruthy();
    });
});