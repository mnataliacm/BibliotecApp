import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../models/book';

const header = { headers: new HttpHeaders({ 'Content-TYpe': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookURL = 'http://localhost:8080/api/library/book/';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.bookURL + 'all', header);
  }

  public detail(id: number): Observable<Book> {
    return this.httpClient.get<Book>(this.bookURL + `detail/${id}`, header);
  }

  public add(producto: Book): Observable<any> {
    return this.httpClient.post<any>(this.bookURL + 'add', producto, header);
  }

  public modify(producto: Book, id: number): Observable<any> {
    return this.httpClient.put<any>(this.bookURL + `modify/${id}`, producto, header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.bookURL + `delete/${id}`, header);
  }

}