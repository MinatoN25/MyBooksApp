import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router/src/interfaces';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthenticationService, private route: Router) { }

  canActivate(){
    if(!this.authService.isTokenExpired()){
      console.log('Active');
      return true;
    }
    this.route.navigate(['/login']);
    return false;
  }  
}
