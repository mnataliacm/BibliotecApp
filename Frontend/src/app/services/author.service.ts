import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../models/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private baseURL = 'http://localhost:8080/api/library/author/';
  private header = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Author[]> {
    return this.httpClient.get<Author[]>(this.baseURL + 'all', this.header);
  }

  public detail(id: number): Observable<Author> {
    return this.httpClient.get<Author>(this.baseURL + `detail/${id}`, this.header);
  }

  public add(author: Author): Observable<Author> {
    return this.httpClient.post(this.baseURL + 'add', author, this.header);
  }

  public modify(author: Author, id: number): Observable<Author> {
    return this.httpClient.put(this.baseURL + `modify/${id}`, author, this.header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.baseURL + `delete/${id}`, this.header);
  }

}