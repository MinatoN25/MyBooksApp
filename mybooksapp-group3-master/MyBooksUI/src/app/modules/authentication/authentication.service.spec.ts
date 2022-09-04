import { TestBed, inject, fakeAsync} from '@angular/core/testing';
import { AuthenticationService } from './authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

const testConfig ={
    addUser :{
        positive:{
            firstName:'first',
            lastName:'last',
            userId:'user',
            password:'1234'
        }
    },
    loginUser:{
        positive:{            
            userId:'user',
            password:'1234'
        }
    }
}
describe('Authentication service',()=>{

let authService: AuthenticationService;
let httpMock: HttpTestingController;

beforeEach(()=>{
TestBed.configureTestingModule({
    imports:[HttpClientModule, HttpClientTestingModule ],
    providers:[AuthenticationService]
});
    authService=TestBed.get(AuthenticationService);
    httpMock= TestBed.get(HttpTestingController);
});

it('should be created',inject([AuthenticationService],(service:AuthenticationService)=>{
    expect(service).toBeTruthy();
}));

it('should register user', ()=>{

let newData:any =testConfig.addUser.positive;

authService.registerUser(newData).subscribe((res)=>{   
expect(res).toBeDefined;
expect(res).toBe(JSON.stringify(newData));
});

const mockReq =httpMock.expectOne(`${authService.authEndpoint}registerUser`);
expect(mockReq.request.method).toEqual('POST');
mockReq.flush(newData);
httpMock.verify;

});

it('should login user', ()=>{

    let newData:any = testConfig.loginUser.positive;
    
    authService.loginUser(newData).subscribe((res)=>{   
    expect(res).toBeDefined;
    expect(res).toBe('as3dfre.fsferfd.adfgfgssf');
    });
    
    const mockReq =httpMock.expectOne(`${authService.authEndpoint}login`);
    expect(mockReq.request.method).toEqual('POST');
    mockReq.flush('as3dfre.fsferfd.adfgfgssf');
    httpMock.verify;
    
    });

})