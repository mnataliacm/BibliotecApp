import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookComponent } from './components/book/book.component';
import { UserComponent } from './components/user/user.component';
import { HomeComponent } from './home/home/home.component';
import { AuthorComponent } from './components/author/author.component';
import { CategoryComponent } from './components/category/category.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { EditorialComponent } from './components/editorial/editorial.component';

const routes: Routes = [ 
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'user', component: UserComponent},
  {path: 'book', component: BookComponent},
  {path: 'author', component: AuthorComponent},
  {path: 'genre', component: CategoryComponent},
  {path: 'editorial', component: EditorialComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
