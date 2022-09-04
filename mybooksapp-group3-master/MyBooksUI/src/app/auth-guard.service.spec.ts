import { TestBed } from '@angular/core/testing';
import { AuthGuardService } from './auth-guard.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from './modules/authentication/authentication.service';

class Dummy{}

describe('Auth Guard Service',()=>{

    let authService: AuthGuardService;
    let authenticationServcie:AuthenticationService;
    let route:Router;
    beforeEach(()=>{
    TestBed.configureTestingModule({
        imports:[ AuthenticationModule, RouterTestingModule.withRoutes([{path:'login',component:Dummy}]) ],
        providers:[AuthGuardService]
    });
    authService=TestBed.get(AuthGuardService);
    authenticationServcie=TestBed.get(AuthenticationService);
    route=TestBed.get(Router);
    });

    it('should be created',()=>{
        expect(authService).toBeTruthy();
    });

    it('should navigate to page if token is valid',()=>{
        spyOn(authenticationServcie,'isTokenExpired').and.returnValue(false);
        let isAuth = authService.canActivate();
        expect(isAuth).toBe(true);
    });

    it('should guard the route if token is expired',()=>{
        spyOn(authenticationServcie,'isTokenExpired').and.returnValue(true);
        let isAuth = authService.canActivate();
        expect(isAuth).toBe(false);
    });
});