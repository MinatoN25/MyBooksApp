import { TestBed, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { MatSnackBar, MatSnackBarModule, MatButtonModule, MatCardModule, MatDialog, MatDialogModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BookService } from '../../book.service';
import { Book } from '../../book';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { ThumbnailComponent } from '../thumbnail/thumbnail.component';
import { By } from '@angular/platform-browser';

const testConfig = {
    book: {
        id: 2,
        cover_i: "http://image2",
        title: "Book2",
        author_name: ["Author2"],
        first_publish_year: 2019,
        edition_count: 1
    }
}

describe('Thumbnail component', () => {

    let fixture: ComponentFixture<ThumbnailComponent>;
    let thumbnailComponent: ThumbnailComponent;
    let bookService: BookService;
    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [ThumbnailComponent],
            imports: [MatSnackBarModule, BrowserAnimationsModule, MatCardModule, MatButtonModule, MatDialogModule],
            providers: [MatDialog]
        }).compileComponents;
        fixture = TestBed.createComponent(ThumbnailComponent);
        thumbnailComponent = fixture.componentInstance;
    });

    it('should create thumbnail component instance', () => {
        expect(thumbnailComponent).toBeTruthy();
    });

    it('should contain add to favourites button if isFavourites property is false', () => {
        thumbnailComponent.isFavourites = false;
        fixture.detectChanges();
        let addButton = fixture.debugElement.query(By.css('.addBookButton'));
        let deleteButton = fixture.debugElement.query(By.css('.deleteBookButton'))
        expect(addButton).toBeTruthy();
        expect(deleteButton).toBeFalsy();

    });
    
    it('should contain delete from favourites button if isFavourites property is true', () => {
        thumbnailComponent.isFavourites = true;
        fixture.detectChanges();
        let addButton = fixture.debugElement.query(By.css('.addBookButton'));
        let deleteButton = fixture.debugElement.query(By.css('.deleteBookButton'))
        expect(addButton).toBeFalsy();
        expect(deleteButton).toBeTruthy();

    });

    it('should emit the book to add when clicking add to favourites', () => {
        let book: Book;
        thumbnailComponent.book = testConfig.book;
        thumbnailComponent.isFavourites = false;
        thumbnailComponent.addBook.subscribe((data) => book = data);
        fixture.detectChanges();
        let addButton = fixture.debugElement.query(By.css('.addBookButton'));
        fixture.detectChanges();
        addButton.triggerEventHandler('click', null);
        expect(book).toBe(testConfig.book)
    });

    it('should emit the book to delete when clicking delete button', () => {
        let book: Book;
        thumbnailComponent.book = testConfig.book;
        thumbnailComponent.isFavourites = true;
        thumbnailComponent.deleteBook.subscribe((data) => book = data);
        fixture.detectChanges();
        let deleteButton = fixture.debugElement.query(By.css('.deleteBookButton'));
        fixture.detectChanges();
        deleteButton.triggerEventHandler('click', null);
        expect(book).toBe(testConfig.book)
    });
});