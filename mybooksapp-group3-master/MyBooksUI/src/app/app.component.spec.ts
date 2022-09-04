import { TestBed, ComponentFixture } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material';
import { Router } from '@angular/router';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { BookModule } from './modules/book/book.module';
import { Location } from '@angular/common';
import { By } from '@angular/platform-browser';

class Dummy{}

class AuthServiceStub{
    deleteToken(){
        localStorage.removeItem('authtoken');
    }
}
describe('Appcomponent',()=>{

let fixture:ComponentFixture<AppComponent>;
let authService:AuthenticationService;
    let appComponent: AppComponent;
    let routes:Router;
    let location:Location;


beforeEach(()=>{
TestBed.configureTestingModule(
    { declarations:[AppComponent],
     imports:[BrowserAnimationsModule,MatToolbarModule,
         RouterTestingModule.withRoutes([{path:'login',component:Dummy}]), AuthenticationModule, BookModule],
     providers:[AuthenticationService],
    }
 ).compileComponents;

 TestBed.overrideComponent(AppComponent,{set:{providers:[{provide:AuthenticationService,useClass:AuthServiceStub}]}});
 fixture=TestBed.createComponent(AppComponent);
 routes=TestBed.get(Router);
 location=TestBed.get(Location);
 appComponent=fixture.componentInstance;
 fixture.detectChanges();
 authService= fixture.debugElement.injector.get(AuthenticationService);
});

it('should create an instance of App component ',()=>{
    expect(appComponent).toBeTruthy();
});

it('should create three router links',()=>{
    let dashboardbtn=fixture.debugElement.query(By.css('.dashboardbtn'));
    let favouritebtn=fixture.debugElement.query(By.css('.favouritebtn'));
    let logoutbtn=fixture.debugElement.query(By.css('.logoutbtn'));
    
    expect(dashboardbtn).toBeTruthy();
    expect(favouritebtn).toBeTruthy();
    expect(logoutbtn).toBeTruthy();
});

it('should logout on clicking logout button',()=>{
    localStorage.setItem('authtoken','abc.fr33.iyyttt');
    let logoutBtn=fixture.debugElement.query(By.css('.logoutbtn'));
    fixture.detectChanges();
    logoutBtn.triggerEventHandler('click',null);
    expect(localStorage.getItem('authtoken')).toBeNull();
});

});