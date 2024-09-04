import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { GenreService } from '../../services/genre.service';
import { CategoryComponent } from '../category/category.component';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrl: './category-form.component.css'
})
export class CategoryFormComponent {

  genreForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private genreService: GenreService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<CategoryComponent>,
    public dialog: MatDialog,
    private router: Router,
  ) {
    this.genreForm = this.formBuilder.group({
      idgenre: [data?.idgenre || null],
      name: [data?.name || '', [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.genreForm.valid) {
      if (this.genreForm.value.iduser) {
        this.genreService.modify(this.genreForm.value, this.genreForm.idgenre).subscribe(
          (response: any) => {
            console.log('Genre modified successfully', response);
            this.router.navigate(['/category']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error modifying genre', error);
          }
        );
      } else {
        this.genreService.add(this.genreForm.value).subscribe(
          (response: any) => {
            console.log('Genre added successfully', response);
            this.router.navigate(['/category']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error adding genre', error);
          }
        );
      }
    } else {
      console.error('Invalid form');
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
