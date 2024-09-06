import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { BookService } from './services/books.service';
import { AuthorService } from './services/author.service';
import { CategoryService } from './services/category.service';
import { EditorialService } from './services/editorial.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'libraryF';
  countUsers = 0;
  countBooks = 0;
  countAuthors = 0;
  countCategories = 0;
  countEditorials = 0;

  constructor(
    private userService: UserService,
    private bookService: BookService,
    private authorService: AuthorService,
    private genreService: CategoryService,
    private editorialService: EditorialService,
  ) { }

  ngOnInit() {
    this.getUsers();
    this.getBooks();
    this.getAuthors();
    this.getCategories();
    this.getEditorials();
  }

  getUsers() {
    this.userService.all().subscribe(data => {
      const people = data;
      this.countUsers = data.length;
      // console.log("conteo en componente", this.conteoUsers);
    })
  }

  getBooks() {
    this.bookService.all().subscribe(data => {
      const books = data;
      this.countBooks = data.length;
      // console.log("conteo en componente", this.conteoBooks);
    })
  }
  
  getAuthors() {
    this.authorService.all().subscribe(data => {
      const authors = data;
      this.countAuthors = data.length;
      // console.log("conteo en componente", this.conteoAuthors);
    })
  }

  getCategories() {
    this.genreService.all().subscribe(data => {
      const categories = data;
      this.countCategories = data.length;
      // console.log("conteo en componente", this.conteoCategories);
    })
  }

  getEditorials() {
    this.editorialService.all().subscribe(data => {
      const editorials = data;
      this.countEditorials = data.length;
      // console.log("conteo en componente", this.conteoEditorials);
    })
  }

}
