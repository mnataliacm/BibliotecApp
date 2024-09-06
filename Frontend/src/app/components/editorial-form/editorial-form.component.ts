import { Component, Inject } from '@angular/core';
import { EditorialService } from '../../services/editorial.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EditorialComponent } from '../editorial/editorial.component';

@Component({
  selector: 'app-editorial-form',
  templateUrl: './editorial-form.component.html',
  styleUrl: './editorial-form.component.css'
})
export class EditorialFormComponent {

  editorialForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private editorialService: EditorialService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditorialComponent>,
    public dialog: MatDialog,
    private router: Router,
  ) {
    this.editorialForm = this.formBuilder.group({
      ideditorial: [data?.ideditorial || ''],
      name: [data?.name || '', [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.editorialForm.valid) {
      if (this.editorialForm.value.ideditorial) {
        this.editorialService.modify(this.editorialForm.value, this.editorialForm.ideditorial).subscribe(
          (response: any) => {
            console.log('Editorial modified successfully', response);
            this.dialogRef.close(true);
            this.router.navigate(['/editorial']);
          },
          (error: any) => {
            console.error('Error modifying editorial', error);
          }
        );
      } else {
        this.editorialService.add(this.editorialForm.value).subscribe(
          (response: any) => {
            console.log('Editorial added successfully', response);     
            this.dialogRef.close(true);
            this.router.navigate(['/editorial']);
          },
          (error: any) => {
            console.error('Error adding editorial', error);
          }
        );
      }
    } else {
      console.error('Invalid form');
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
  
}
