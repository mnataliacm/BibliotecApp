import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {

  public people: User[] = [];
  server: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit() {
    if (!this.server) {
      this.listPeople();
    }
  }

  listPeople(): void {
    this.userService.all().subscribe(data => {
      this.people = data;
      this.server = true;
    }),
    (err: any) => {
      console.log(err);
    }
  }

  onDelete(id: number | any): void {
    if (confirm('¿Estás seguro?')) {
      this.userService.delete(id).subscribe(data => {
        this.people = data;
      });
    }
  }
}

// P91VD-TET8-8RM4-EFBY philips
// VERANO10 bosh y balay