import { TestBed, ComponentFixture, fakeAsync, tick} from '@angular/core/testing';
import { DashboardComponent } from './dashboard.component';
import { BookService } from '../../book.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Book } from '../../book';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { MatFormFieldModule, MatCardModule, MatSelectModule, MatSnackBar, MatSnackBarModule, MatFormFieldControl, MatInputModule } from '@angular/material';
import { ContainerComponent } from '../container/container.component';
import { ThumbnailComponent } from '../thumbnail/thumbnail.component';
import { By } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

class BookServiceStub{
    searchBooks(book:Book){
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
describe('Dashboard Component',()=>{

    let fixture:ComponentFixture<DashboardComponent>;
    let dashboardComponent: DashboardComponent;
    let bookService: BookService;

    beforeEach(()=>{
        TestBed.configureTestingModule({
            declarations:[ DashboardComponent, ContainerComponent,ThumbnailComponent],
            imports:[BrowserAnimationsModule,MatFormFieldModule, FormsModule, MatCardModule, MatSelectModule,  MatSnackBarModule, MatInputModule],
            providers:[BookService]
        }).compileComponents;
    TestBed.overrideComponent(DashboardComponent,{set:{providers:[{provide:BookService,useClass:BookServiceStub}]}});
    
    fixture=TestBed.createComponent(DashboardComponent);
    dashboardComponent=fixture.componentInstance;
    bookService=fixture.debugElement.injector.get(BookService);
    });


    it('should create dashboard component instance',()=>{
        expect(dashboardComponent).toBeTruthy();
    });

    it('should contain the input field for search',()=>{
        fixture.detectChanges();
        let searchtext=fixture.debugElement.query(By.css('input[type=text]'));
        expect(searchtext).toBeTruthy();
    });

    it('should return matched results for the searched text',fakeAsync(()=>{
        fixture.detectChanges();
        let searchtext=fixture.debugElement.query(By.css('input[type=text]'));
        searchtext.nativeElement.value='super';
        fixture.detectChanges();
        let spy= spyOn(bookService,'searchBooks').and.returnValue(Observable.of(testConfig.books));
        fixture.detectChanges();
        searchtext.triggerEventHandler('keyup.enter',null);
        tick();
        expect(spy).toHaveBeenCalled();
        expect(dashboardComponent.books.length).toBe(2);
    }));
});