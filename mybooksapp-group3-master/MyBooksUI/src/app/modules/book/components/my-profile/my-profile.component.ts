import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/authentication/user';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { LoginComponent } from 'src/app/modules/authentication/login/login.component';
import { BookService } from '../../book.service';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  user: User;
  constructor(private authService: AuthenticationService, private router: Router, private snackBar:MatSnackBar) {
    this.user = new User();
  }

  ngOnInit() {
  }
  updateProfile(){
    this.authService.updateUser(this.user).subscribe((data)=>{
      this.snackBar.open(`update successful`,'',{duration:2000});
      this.router.navigate(['/my-profile']);
    },(err)=>{this.snackBar.open(err.error,'',{duration:2000});
    })
  }
  
  resetInput(){
    this.user=new User();
  }

}
