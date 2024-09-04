import { Component, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthorService } from '../../services/author.service';
import { AuthorComponent } from '../author/author.component';

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrl: './author-form.component.css'
})
export class AuthorFormComponent {

  authorForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authorService: AuthorService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<AuthorComponent>,
    public dialog: MatDialog,
    private router: Router,
  ) {
    this.authorForm = this.formBuilder.group({
      idauthor: [data?.idauthor || null],
      name: [data?.name || '', [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.authorForm.valid) {
      if (this.authorForm.value.idauthor) {
        this.authorService.modify(this.authorForm.value, this.authorForm.value.idauthor).subscribe(
          (response: any) => {
            console.log('Author modified successfully', response);
            this.router.navigate(['/author']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error modifying author', error);
          }
        );
      } else {
        this.authorService.add(this.authorForm.value).subscribe(
          (response: any) => {
            console.log('Author added successfully', response);
            this.router.navigate(['/author']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error adding author', error);
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
