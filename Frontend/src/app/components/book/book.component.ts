import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/books.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Book } from '../../models/book';
import { BookFormComponent } from '../book-form/book-form.component';
import { BookDetailComponent } from '../book-detail/book-detail.component';
import { AuthorService } from '../../services/author.service';
import { EditorialService } from '../../services/editorial.service';
import { GenreService } from '../../services/genre.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrl: './book.component.css'
})
export class BookComponent implements OnInit {

  public books: Book[] = [];
  server: boolean = false;
  
  constructor(
    private bookService: BookService,
    public dialog: MatDialog,
    private router: Router,
    private authorService: AuthorService,
    private genreService: GenreService,
    private editorialService: EditorialService,
  ) {}

  ngOnInit() {
    if (!this.server) {
      this.listBooks();
    }
  }

  listBooks() {
    this.bookService.all().subscribe(
      (data: Book[]) => {
      this.books = data;
      this.books.forEach((book) => {
        this.authorService.detail(book.idauthor!).subscribe((author) => {
          book.authorName = author.name;
        });
        this.genreService.detail(book.idgenre!).subscribe((genre) => {
          book.genreName = genre.name;
        });
        this.editorialService.detail(book.ideditorial!).subscribe((editorial) => {
          book.editorialName = editorial.name;
        });
      });
      console.log("listBooks", this.books);
      this.server = true;
      this.router.navigate(['/book']);
    },
    (error) => {
      console.error('Error al listar libros', error);
      this.server = false;
    });
  }

  viewBookDetails(book: Book): void {
    this.dialog.open(BookDetailComponent, {
      width: '400px',
      data: book
    });
    this.router.navigate(['/book']);
  }

  openBookDialog(book?: Book) {
    const dialogRef = this.dialog.open(BookFormComponent, {
      data: book ? { ...book } : {}
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (book) {
          // Edit existing book
          const id: number = book?.isbn ?? 0;
          this.bookService.modify(result, id).subscribe(() => {
            this.listBooks();
          },
          (error) => {
            console.error('Error al modificar libro', error);
          });
        } else {
          // Add new book
          this.bookService.add(result).subscribe(() => {
            this.listBooks();
          },
          (error) => {
            console.error('Error al añadir libro', error);
          });
        }
      }
    });
  }

  deleteBook(book: Book) {
    const id: number = book?.isbn ?? 0;
    this.bookService.delete(id).subscribe(() => {
      this.listBooks();
      this.router.navigate(['/book']);
    },
    (error) => {
      console.error('Error al eliminar libro', error);
    });
  }

  
}
