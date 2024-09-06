import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseURL = 'http://localhost:8080/api/library/book/';
  private header = { headers: new HttpHeaders({ 'Content-TYpe': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.baseURL + 'all', this.header);
  }

  public detail(id: number): Observable<Book> {
    return this.httpClient.get<Book>(this.baseURL + `detail/${id}`, this.header);
  }

  public add(book: Book): Observable<Book> {
    return this.httpClient.post<Book>(this.baseURL + 'add', book, this.header);
  }

  public modify(book: Book, id: number): Observable<Book> {
    return this.httpClient.put<Book>(this.baseURL + `modify/${id}`, book, this.header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.baseURL + `delete/${id}`, this.header);
  }

}