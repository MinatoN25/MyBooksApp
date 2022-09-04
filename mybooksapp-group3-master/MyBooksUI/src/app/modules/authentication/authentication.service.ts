import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import * as jwt_decode from "jwt-decode";
import { User } from './user';

const TOKEN_NAME: string ='AuthHeader';

@Injectable()
export class AuthenticationService {

  authEndpoint:string ;

  constructor(private http: HttpClient) { 
    this.authEndpoint = "http://localhost:8081/api/books/";

  }

  registerUser(user: User ):Observable<any>{
    return this.http.post(`${this.authEndpoint}registerUser`,user,{responseType:'text'});
  }
  updateUser(user: User ):Observable<any>{
    return this.http.put(`${this.authEndpoint}my-profile`,user,{responseType:'text'});
  }
  loginUser(user: User) : Observable<any>{
    return this.http.post(`${this.authEndpoint}login`,user,{responseType:'text'});
  }

  setToken(token:string){
    localStorage.setItem(TOKEN_NAME,token);
  }

  getToken(){
        return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken(){
    return localStorage.removeItem(TOKEN_NAME);
  }

  getTokenExpirationDate(token:string):Date{
    const decodedValue= jwt_decode(token);
    if(decodedValue.exp===undefined) return null;
    const date= new Date(0);
    date.setUTCSeconds(decodedValue.exp);
    return date;
  }

  isTokenExpired(token?:string):boolean{
    if(!token) token =this.getToken();
    if(!token) return true;
    const date=this.getTokenExpirationDate(token);
    if(date===undefined || date===null) return false;
    return !(date.valueOf() > new Date().valueOf());
  }
}
