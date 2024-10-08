import { Component, Inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {

  userForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<UserFormComponent>,
    public dialog: MatDialog,
    private router: Router,
  ) {
    this.userForm = this.formBuilder.group({
      iduser: [data?.iduser || null],
      name: [data?.name || '', [Validators.required]],
      surname: [data?.surname || '', [Validators.required]],
      email: [data?.email || '', [Validators.required, Validators.email]],
      mobile: [data?.mobile || ''],
      address: [data?.address || ''],
      num: [data?.num || ''],
      floor: [data?.floor || ''],
      cp: [data?.cp || ''],
      town: [data?.town || ''],
      city: [data?.city || ''],
      member: [data?.member || ''],
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      // console.log("IDuser", this.userForm.value.iduser, "Name", this.userForm.value.name);
      if (this.userForm.value.iduser) {
        this.userService.modify(this.userForm.value, this.userForm.value.iduser).subscribe(
          (response: User) => {
            console.log('Usuario modificado correctamente', response);
            this.router.navigate(['/user']);
            this.dialogRef.close(response);
          },
          (error: any) => {
            console.error('Error al modificar usuario', error);
          }
        );
      } else {
        this.userService.add(this.userForm.value).subscribe(
          (response: User) => {
            console.log('Usuario añadido correctamente', response);
            this.router.navigate(['/user']);
            this.dialogRef.close(true);
          },
          (error: any) => {
            console.error('Error al añadir usuario', error);
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

}
