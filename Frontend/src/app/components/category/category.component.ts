import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { GenreService } from '../../services/genre.service';
import { Genre } from '../../models/genre';
import { CategoryFormComponent } from '../category-form/category-form.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {

  public genres: Genre[] = [];
  server: boolean = false;

  constructor(
    private genreService: GenreService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit() {
    if (!this.server) {
      this.listGenres();
    }
  }

  listGenres(): void {    
    this.genreService.all().subscribe(
      (data: Genre[]) => {
        this.genres = data;
        // console.log('Géneros...', this.genres);
        this.server = true;
        this.router.navigate(['/category']);
      },
      (error) => {
        console.error('Error al cargar géneros', error);
        this.server = false;
      }
    );
  }

  openCategoryDialog(genre?: Genre) {
    const dialogRef = this.dialog.open(CategoryFormComponent, {
      data: genre ? { ...genre } : {}
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (genre) {
          const id: number = genre?.idgenre ?? 0;
          this.genreService.modify(result, id).subscribe(() => {
            this.listGenres();
          });
        } else {
          this.genreService.add(result).subscribe(() => {
            this.listGenres();
          });
        }
      }
    });
  }

  onDelete(id: number | any): void {
    if (confirm('¿Estás seguro?')) {
      this.genreService.delete(id).subscribe(data => {
        this.genres = data;
      });
      this.router.navigate(['/category']);
    }
  }
  
}
