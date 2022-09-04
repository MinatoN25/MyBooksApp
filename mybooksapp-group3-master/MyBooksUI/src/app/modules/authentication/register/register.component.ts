import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'auth-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;
  selectedFile: File;
  
  constructor(private httpClient: HttpClient,private authService: AuthenticationService, private router: Router, private snackBar:MatSnackBar) {
    this.newUser = new User();
    
   }

  ngOnInit() {
  }
 
 public onFileChanged(event) {
  this.selectedFile = event.target.files[0];
}
 
 onUpload() {
  console.log(this.selectedFile);
  const uploadImageData = new FormData();
  uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
 }
  registerUser(){
    this.authService.registerUser(this.newUser).subscribe((data)=>{
      this.snackBar.open(`Registration successful`,'',{duration:1000});
      this.router.navigate(['/login']);
    },(err)=>{this.snackBar.open(err.error,'',{duration:1000});
    })
  }
  
  resetInput(){
    this.newUser=new User();
  }

}
