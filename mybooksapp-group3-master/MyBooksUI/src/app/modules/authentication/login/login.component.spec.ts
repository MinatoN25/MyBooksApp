import { TestBed, ComponentFixture, async, fakeAsync, tick } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../user';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatSnackBarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';

const testConfig ={
    usr:{
        userId:'testuser',
        password:'testpass'
    }
}
const TOKEN_NAME: string ='AuthHeader';

class AuthServiceStub{
    loginUser(user:User){
        if(user.userId==testConfig.usr.userId){
           return Observable.of(user.userId)
        }
        return Observable.of(false);
    }
    setToken(token:string){
        localStorage.setItem(TOKEN_NAME,token);
    }    
    getToken(){
        return localStorage.getItem(TOKEN_NAME);
    }
}

class Dummy{}


describe('Login Component',()=>{

    let fixture:ComponentFixture<LoginComponent>;
    let authService:AuthenticationService;
    let loginComponent: LoginComponent;
    let routes:Router;
    let location:Location;


beforeEach(()=>{

    TestBed.configureTestingModule(
       { declarations:[LoginComponent],
        imports:[FormsModule,BrowserAnimationsModule,HttpClientModule,MatFormFieldModule, MatInputModule, MatButtonModule,
            RouterTestingModule.withRoutes([{path:'books/home',component:Dummy},{path:'register',component: Dummy}]),MatSnackBarModule],
        providers:[AuthenticationService]
       }
    ).compileComponents;

    TestBed.overrideComponent(LoginComponent,{set:{providers:[{provide:AuthenticationService,useClass:AuthServiceStub}]}});

    fixture=TestBed.createComponent(LoginComponent);
    routes=TestBed.get(Router);
    location=TestBed.get(Location);
    loginComponent=fixture.componentInstance;
    fixture.detectChanges();
    authService= fixture.debugElement.injector.get(AuthenticationService);
});
    

it('should be created',()=>{
    const app=fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
});

it('should have two input fields for user id and password',()=>{
    let userId=fixture.debugElement.query(By.css('.login-userId'));
    let password=fixture.debugElement.query(By.css('.login-password'));    

    let userIdNative=userId.nativeElement;
    let passwordNative=password.nativeElement;    

    expect(userIdNative).toBeTruthy();
    expect(passwordNative).toBeTruthy();
});


it('should use mock auth service',()=>{
    expect(authService instanceof AuthServiceStub).toBeTruthy();
});

it('should login successfully for valid user id and password',async(()=>{
    
    let userId=fixture.debugElement.query(By.css('.login-userId'));
    let password=fixture.debugElement.query(By.css('.login-password'));
    let loginButton=fixture.debugElement.query(By.css('.login-btn'));
    let userIdNative=userId.nativeElement;
    let passwordNative=password.nativeElement;

    fixture.detectChanges();    
    
    fixture.whenStable().then(()=>{
        userIdNative.value='testuser';
        passwordNative.value='testpass';
        userIdNative.dispatchEvent(new Event('input'));
        passwordNative.dispatchEvent(new Event('input'));
        loginButton.triggerEventHandler('click',null);
    }).then(()=>{
        expect(authService.getToken()).toBe('testuser');
        expect(location.path()).toBe('/books/home');
    });

}));


it('should navigate to Register page on clicking register button',fakeAsync(()=>{

    let registerButton = fixture.debugElement.query(By.css('.login-register'));
    registerButton.triggerEventHandler('click',null);
    tick();
    expect(location.path()).toBe('/register');

}));
});