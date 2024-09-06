import { Component, Inject } from '@angular/core';
import { BookService } from '../../services/books.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthorComponent } from '../author/author.component';
import { Category } from '../../models/category';
import { CategoryService } from '../../services/category.service';
import { EditorialService } from '../../services/editorial.service';
import { Author } from '../../models/author';
import { Editorial } from '../../models/editorial';
import { AuthorService } from '../../services/author.service';
import { Book } from '../../models/book';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrl: './book-form.component.css'
})
export class BookFormComponent {

  bookForm: any;
  genres: Category[] = [];
  authors: Author[] = [];
  editorials: Editorial[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private bookService: BookService,
    private genreService: CategoryService,
    private authorService: AuthorService,
    private editorialService: EditorialService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<AuthorComponent>,
    public dialog: MatDialog,
    private router: Router,
  ) {
    this.bookForm = this.formBuilder.group({
      isbn: [data?.isbn || null],
      title: [data?.title || '', [Validators.required]],
      year: [data?.year || ''],
      idgenre: [data?.idgenre || ''],
      idauthor: [data?.idauthor || ''],
      ideditorial: [data?.ideditorial || ''],
      image: [data?.image || ''],
    });
    this.genreService.all().subscribe((categories: Category[]) => {
      this.genres = categories;
    });
    this.authorService.all().subscribe((authors: Author[]) => {
      this.authors = authors;
    });
    this.editorialService.all().subscribe((editorials: Editorial[]) => {
      this.editorials = editorials;
    });
    console.log("listas en books", this.genres, this.authors, this.editorials);
  }

  onSubmit(): void {
    if (this.bookForm.valid) {
      console.log('Formulario válido', this.bookForm.value);
      if (this.bookForm.value.isbn) {
        this.bookService.modify(this.bookForm.value, this.bookForm.value.isbn).subscribe(
          (response: Book) => {
            console.log('Libro actualizado correctamente', response);
            this.router.navigate(['/books']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error al actualizar libro', error);
          }
        );
      } else {
        this.bookService.add(this.bookForm.value).subscribe(
          (response: Book) => {
            console.log('Libro añadido correctamente', response);
            this.router.navigate(['/books']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error al añadir libro', error);
          }
        );
      }
    } else {
      console.error('Formulario no válido');
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  selectGenre(genre: Category): void {
    this.bookForm.controls.idgenre.setValue(genre.idgenre);
  }

  selectAuthor(author: Author): void {
    this.bookForm.controls.idauthor.setValue(author.idauthor);
  }

  selectEditorial(editorial: Editorial): void {
    this.bookForm.controls.ideditorial.setValue(editorial.ideditorial);
  }

}
