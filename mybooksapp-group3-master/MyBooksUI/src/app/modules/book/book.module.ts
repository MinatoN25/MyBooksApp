import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookRoutingModule } from './book-routing.module';
import { BookService } from './book.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TokeninterceptorService } from './tokeninterceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { MyProfileComponent } from './components/my-profile/my-profile.component';



@NgModule({
  declarations: [ThumbnailComponent, ContainerComponent, FavouriteComponent, DashboardComponent, MyProfileComponent ],
  imports: [
    CommonModule, FormsModule, BookRoutingModule, HttpClientModule, MatCardModule, MatButtonModule, MatSnackBarModule,
    MatInputModule, MatFormFieldModule, MatSelectModule
  ],
  providers:[BookService,  {provide:HTTP_INTERCEPTORS,useClass: TokeninterceptorService,multi:true}]
})
export class BookModule { }
