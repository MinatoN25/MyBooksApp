import { TestBed, ComponentFixture, fakeAsync, tick } from '@angular/core/testing';
import { RegisterComponent } from './register.component'; 
import { Router } from '@angular/router';
import { MatSnackBar, MatFormFieldModule, MatInputModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthenticationService } from '../authentication.service';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable } from 'rxjs/Observable';
import { User } from '../user';
import 'rxjs/add/observable/of';
import { Location } from '@angular/common';
import { By } from '@angular/platform-browser';
import { eventNames } from 'cluster';

class AuthServiceStub{
    registerUser(user:User):Observable<any>{
        return Observable.of('User registered Successfully');
    }
}
class Dummy{}

describe('Register Component ',()=>{

let fixture: ComponentFixture<RegisterComponent>;
let registerComponent: RegisterComponent;
let routes:Router;
let snackBar:MatSnackBar;
let authServcie:AuthenticationService;
let location:Location;

beforeEach(()=>{
    TestBed.configureTestingModule({
        declarations:[RegisterComponent],
        imports:[FormsModule,BrowserAnimationsModule,HttpClientModule,MatFormFieldModule, MatInputModule, MatButtonModule,
            RouterTestingModule.withRoutes([{path:'login',component:Dummy}]),MatSnackBarModule],
        providers:[AuthenticationService]
    }).compileComponents;

    TestBed.overrideComponent(RegisterComponent,{set:{providers:[{provide:AuthenticationService,useClass:AuthServiceStub}]}});
    
    fixture=TestBed.createComponent(RegisterComponent);
    registerComponent=fixture.componentInstance;    
    routes=TestBed.get(Router);
    location=TestBed.get(Location);
    fixture.detectChanges();
    authServcie=fixture.debugElement.injector.get(AuthenticationService);
    snackBar=fixture.debugElement.injector.get(MatSnackBar);
});

it('should create Register component instance',()=>{
expect(registerComponent).toBeTruthy();

});

it('should have four input fields and two buttons',()=>{
    let firstName=fixture.debugElement.query(By.css('#firstName'));
    let lastName=fixture.debugElement.query(By.css('#lastName'));
    let userId=fixture.debugElement.query(By.css('#userId'));
    let password=fixture.debugElement.query(By.css('#password'));
    let registerButton=fixture.debugElement.query(By.css('.register-btn'));
    let resetButton=fixture.debugElement.query(By.css('.register-reset'));
    
    expect(firstName).toBeTruthy();
    expect(lastName).toBeTruthy();
    expect(userId).toBeTruthy();
    expect(password).toBeTruthy();
    expect(registerButton).toBeTruthy();
    expect(resetButton).toBeTruthy();
    
    });

it('should register user on clicking register button',fakeAsync(()=>{
let firstName=fixture.debugElement.query(By.css('#firstName')).nativeElement;
let lastName=fixture.debugElement.query(By.css('#lastName')).nativeElement;
let userId=fixture.debugElement.query(By.css('#userId')).nativeElement;
let password=fixture.debugElement.query(By.css('#password')).nativeElement;


let registerButton=fixture.debugElement.query(By.css('.register-btn'));
firstName.value='test';
lastName.value='user';
userId.value='testuser';
password.value='test123';
fixture.detectChanges();
firstName.dispatchEvent(new Event('input'));
lastName.dispatchEvent(new Event('input'));
userId.dispatchEvent(new Event('input'));
password.dispatchEvent(new Event('input'));
spyOn(snackBar,'open').and.returnValue('success');
fixture.detectChanges();
registerButton.triggerEventHandler('click',null);
tick();
expect(location.path()).toBe('/login');

}));


it('should reset input on clicking reset button',()=>{
    let firstName=fixture.debugElement.query(By.css('#firstName')).nativeElement;
    let lastName=fixture.debugElement.query(By.css('#lastName')).nativeElement;
    let userId=fixture.debugElement.query(By.css('#userId')).nativeElement;
    let password=fixture.debugElement.query(By.css('#password')).nativeElement;

    let resetButton=fixture.debugElement.query(By.css('.register-reset'));

    firstName.value='test';
    lastName.value='user';
    userId.value='testuser';
    password.value='test123';

    fixture.detectChanges();

    firstName.dispatchEvent(new Event('input'));
    lastName.dispatchEvent(new Event('input'));
    userId.dispatchEvent(new Event('input'));
    password.dispatchEvent(new Event('input'));

    fixture.detectChanges();

    resetButton.triggerEventHandler('click',null);
    expect(registerComponent.newUser).toBeDefined();
    expect(registerComponent.newUser.firstName).toBeUndefined();

});
});