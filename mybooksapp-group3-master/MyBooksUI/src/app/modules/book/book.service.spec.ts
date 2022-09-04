import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { BookService } from './book.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const apiKey = '?api_key=c01fc74c59190bccc1013bd3768dd1fb';


const testConfig = {
    books: {
        docs: [{
            id: 1,
            cover_i: "http://image1",
            title: "Book1",
            author_name: ["Author1"],
            first_publish_year: 2019,
            edition_count: 1
        }, {
            id: 2,
            cover_i: "http://image2",
            title: "Book2",
            author_name: ["Author2"],
            first_publish_year: 2019,
            edition_count: 1
        }]
    },
    book: {
        id: 2,
        cover_i: "http://image2",
        title: "Book2",
        author_name: ["Author2"],
        first_publish_year: 2019,
        edition_count: 1
    }
}

describe('Book service', () => {

    let bookService: BookService;
    let httpMock: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientModule, HttpClientTestingModule],
            providers: [BookService]
        });
        bookService = TestBed.get(BookService);
        httpMock = TestBed.get(HttpTestingController);
    });


    it('should be created', () => {
        expect(bookService).toBeTruthy();
    });

    it('should get books from favourites', () => {

        let newData = testConfig.books.docs;
        bookService.getBooksFromMyFavourites().subscribe((res) => {
            expect(res).toBeDefined;
            expect(res).toEqual(newData);
        });

        const mockReq = httpMock.expectOne(`${bookService.watchListEndpoint}getMyFavouriteBooks`);
        expect(mockReq.request.method).toEqual('GET');
        mockReq.flush(newData);
        httpMock.verify;

    });

    it('should add book to favourites', () => {

        let newData = testConfig.book;
        bookService.addBookToMyFavourites(newData).subscribe((res) => {
            expect(res).toBeDefined;
            expect(res).toEqual('Successfully added');
        });

        const mockReq = httpMock.expectOne(`${bookService.watchListEndpoint}saveBookToMyFavourites`);
        expect(mockReq.request.method).toEqual('POST');
        mockReq.flush('Successfully added');
        httpMock.verify;

    });

    it('should return books for the searched text ', () => {

        let newData = testConfig.books;
        let expectedData = testConfig.books.docs;
        let searchType = 'q';
        let searchBook = 'super';
        bookService.searchBooks(`${searchBook}`, `${searchType}`).subscribe((res) => {
            expect(res).toBeDefined;
            expect(res).toEqual(expectedData);
        });

        const mockReq = httpMock.expectOne(`${bookService.openLibraryEndpoint}${searchType}=${searchBook}&page=1`);
        expect(mockReq.request.method).toEqual('GET');
        mockReq.flush(newData);
        httpMock.verify;

    });
});