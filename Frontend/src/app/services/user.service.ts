import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const header = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userURL = 'http://localhost:8080/api/library/user/';
 
  constructor(private httpClient: HttpClient) { }

  public all(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userURL + 'all', header);
  }

  public detail(id: number): Observable<User> {
    return this.httpClient.get<User>(this.userURL + `detail/${id}`, header);
  }

  public add(userData: User): Observable<any> {
    return this.httpClient.post<any>(this.userURL + 'add', userData, header);
  }

  public modify(userData: User, id: number): Observable<any> {
    return this.httpClient.put<any>(this.userURL + `modify/${id}`, userData, header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.userURL + `delete/${id}`, header);
  }

}