import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../user-form/user-form.component';
import { Router } from '@angular/router';
import { UserDetailComponent } from '../user-detail/user-detail.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {

  public people: User[] = [];
  server: boolean = false;

  constructor(
    private userService: UserService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit() {
    if (!this.server) {
      this.listPeople();
    }
  }

  viewUserDetails(user: User): void {
    this.dialog.open(UserDetailComponent, {
      width: '400px',
      data: user
    });
    this.router.navigate(['/user']);
  }

  openUserDialog(user?: User) {
    const dialogRef = this.dialog.open(UserFormComponent, {
      data: user ? { ...user } : {}
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (user) {
          // Edit existing user
          const id: number = user?.iduser ?? 0;
          this.userService.modify(result, id).subscribe(() => {
            this.listPeople();
          },
          (error) => {
            console.error('Error al modificar usuario', error);
          });
        } else {
          // Add new user
          this.userService.add(result).subscribe(() => {
            this.listPeople();
          },
          (error) => {
            console.error('Error al agregar usuario', error);
          }
        );
        }
      }
      this.router.navigate(['/user']);
    });
  }
  

  listPeople(): void {    
    this.userService.all().subscribe(
      (data: User[]) => {
        this.people = data;
        this.server = true;
        this.router.navigate(['/user']);
      },
      (error) => {
        console.error('Error al cargar usuarios', error);
        this.server = false;
      }
    );
  }

  onDelete(id: number | any): void {
    if (confirm('¿Estás seguro?')) {
      this.userService.delete(id).subscribe(data => {
        this.people = data;
      });
      this.router.navigate(['/user']);
    }
  }

}
