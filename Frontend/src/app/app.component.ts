import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'libraryF';
  conteoUsers = 0;

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.all().subscribe(data => {
      const people = data;
      this.conteoUsers = data.length;
      // console.log("conteo en componente", this.conteoUsers);

    })
  }

}
