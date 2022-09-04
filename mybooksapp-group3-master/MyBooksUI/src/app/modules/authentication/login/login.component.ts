import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'auth-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;
  
  constructor(private router: Router, private authService:AuthenticationService, private  snackBar:MatSnackBar) {
    this.user=new User();
   }

  ngOnInit() {
  }

  registerUser(){
    this.router.navigate(['/register']);
  }

  loginUser(){
    this.authService.loginUser(this.user).subscribe((data)=>{
      this.authService.setToken(data);    
      this.router.navigate(['/books/home']);
    },(err)=>{
      this.snackBar.open(err.error,'',{duration:1000})      
    });
  } 
}
