import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const header = { headers: new HttpHeaders({ 'Content-TYpe': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userURL = 'http://localhost:8080/api/library/user/';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userURL + 'all', header);
  }

  public detail(id: number): Observable<User> {
    return this.httpClient.get<User>(this.userURL + `detail/${id}`, header);
  }

  public new(producto: User): Observable<any> {
    return this.httpClient.post<any>(this.userURL + 'add', producto, header);
  }

  public modify(producto: User, id: number): Observable<any> {
    return this.httpClient.put<any>(this.userURL + `modify/${id}`, producto, header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.userURL + `delete/${id}`, header);
  }

  // getListUsers() {
  //   this.all().subscribe(data => {
  //     const people = data;
  //     this.conteo = data.length;
  //     // return this.conteo = people.length;
  //     console.log("conteo en servicio", this.conteo);
  //   })
  // }

}