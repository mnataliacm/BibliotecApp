import { Component, Inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { FormBuilder, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from '@angular/material/dialog';
import { User } from '../../models/user';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {

  // items: any;
  userForm: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<UserFormComponent>,
    public dialog: MatDialog
  ) {
    this.userForm = this.formBuilder.group({
      iduser: [data?.iduser || null],
      name: [data?.name || ''],
      surname: [data?.surname || ''],
      email: [data?.email || '', [Validators.required, Validators.email]],
      mobile: [data?.mobile || ''],
      address: [data?.address || ''],
      num: [data?.num || ''],
      floor: [data?.floor || ''],
      cp: [data?.cp || ''],
      town: [data?.town || ''],
      city: [data?.city || ''],
      member: [data?.member || '']
    });
  }

  // openDialog() {
  //   const dialogRef = this.dialog.open(UserFormComponent);

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log(`Dialog result: ${result}`);
  //   });
  // }

  // onSubmit(userData: any) {
  //   this.userService.add(userData).subscribe(
  //     (response: any) => {
  //       console.log('Usuario añadido correctamente', response);
  //       this.userForm.reset();
  //     },
  //     (error: any) => {
  //       console.error('Error al añadir usuario', error);
  //     }
  //   );
  // }

  onSubmit(): void {
    if (this.userForm.valid) {
      // console.log("IDuser", this.userForm.value.iduser, "Name", this.userForm.value.name);
      if (this.userForm.value.iduser) {
        this.userService.modify(this.userForm.value, this.userForm.value.iduser).subscribe(
          (response: User) => {
            console.log('Usuario modificado correctamente', response);
            this.dialogRef.close(true); // Cierra el diálogo y pasa un valor indicando éxito
          },
          (error: any) => {
            console.error('Error al modificar usuario', error);
          }
        );
      } else {
        this.userService.add(this.userForm.value).subscribe(
          (response: User) => {
            console.log('Usuario añadido correctamente', response);
            this.dialogRef.close(true); // Cierra el diálogo y pasa un valor indicando éxito
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

  onCancel() {
    this.dialogRef.close();
  }

}
