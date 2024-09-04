import { Component } from '@angular/core';
import {MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthorService } from '../../services/author.service';
import { Author } from '../../models/author';
import { AuthorFormComponent } from '../author-form/author-form.component';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrl: './author.component.css'
})
export class AuthorComponent {

 public authors: Author[] = [];
  server: boolean = false;

  constructor(
    private authorService: AuthorService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit() {
    if (!this.server) {
      this.listAuthors();
    }
  }

  listAuthors(): void {    
    this.authorService.all().subscribe(
      (data: Author[]) => {
        this.authors = data;
        this.server = true;
        this.router.navigate(['/author']);
      },
      (error) => {
        console.error('Error al cargar autores', error);
        this.server = false;
      }
    );
  }

  openAuthorDialog(author?: Author) {
    const dialogRef = this.dialog.open(AuthorFormComponent, {
      data: author ? { ...author } : {}
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (author) {
          const id: number = author?.idauthor ?? 0;
          this.authorService.modify(result, id).subscribe(() => {
            this.listAuthors();
          });
        } else {
          this.authorService.add(result).subscribe(() => {
            this.listAuthors();
          });
        }
      }
    });
  }

  onDelete(id: number | any): void {
    this.authorService.delete(id).subscribe(() => {
      this.listAuthors();
    });
  }

}
