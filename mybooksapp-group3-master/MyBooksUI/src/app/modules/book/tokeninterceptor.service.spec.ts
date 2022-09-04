import { TestBed, inject, fakeAsync} from '@angular/core/testing';
import { TokeninterceptorService } from './tokeninterceptor.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthenticationModule } from '../authentication/authentication.module';

describe('Token Interceptor service',()=>{

    let tokenService: TokeninterceptorService;
    
    beforeEach(()=>{
    TestBed.configureTestingModule({
        imports:[ AuthenticationModule ],
        providers:[TokeninterceptorService]
    });
        tokenService=TestBed.get(TokeninterceptorService);
    });

    it('should be created',()=>{
        expect(tokenService).toBeTruthy();
    });

});