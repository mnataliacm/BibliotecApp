import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { CdkTableModule } from '@angular/cdk/table';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home/home.component';
import { UserComponent } from './components/user/user.component';
import { AuthorComponent } from './components/author/author.component';
import { CategoryComponent } from './components/category/category.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';
import { AuthorFormComponent } from './components/author-form/author-form.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import {MatCardModule} from '@angular/material/card';
import { BookComponent } from './components/book/book.component';
import { EditorialComponent } from './components/editorial/editorial.component';
import { EditorialFormComponent } from './components/editorial-form/editorial-form.component';
import { BorrowComponent } from './components/borrow/borrow.component';

@NgModule({
  declarations: [
    AppComponent, 
    HomeComponent, 
    UserComponent, 
    BookComponent,
    AuthorComponent, 
    CategoryComponent, 
    RegisterComponent, 
    LoginComponent, 
    UserFormComponent, 
    BookFormComponent, 
    CategoryFormComponent, 
    AuthorFormComponent, 
    UserDetailComponent, 
    BookDetailComponent, EditorialComponent, EditorialFormComponent, BorrowComponent,

  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CdkTableModule,
    AppRoutingModule,
    MatCardModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule);