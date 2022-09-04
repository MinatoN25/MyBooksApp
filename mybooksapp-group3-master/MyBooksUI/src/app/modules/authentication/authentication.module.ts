import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material';
import { MatInputModule, MatButtonModule } from '@angular/material';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationRouterModule } from './authentication-router.module';
import { AuthenticationService } from './authentication.service';


@NgModule({
  imports: [
    CommonModule,FormsModule, HttpClientModule, AuthenticationRouterModule, MatFormFieldModule, MatInputModule, MatButtonModule
  ],
  declarations: [RegisterComponent, LoginComponent] ,
  exports: [RegisterComponent, LoginComponent],
  providers: [AuthenticationService]
})
export class AuthenticationModule { }
