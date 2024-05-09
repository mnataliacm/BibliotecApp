import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {

  people: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.listPeople();
  }

  listPeople(): void {
    this.userService.all().subscribe(data => {
      this.people = data;
    },
      (err: any) => {
        console.log(err);
      }
    );
  }

}
