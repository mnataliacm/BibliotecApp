import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../models/author';

const header = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class EditorialService {

  private editorialURL = 'http://localhost:8080/api/library/editorial/';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Author[]> {
    return this.httpClient.get<Author[]>(this.editorialURL + 'all', header);
  }

  public detail(id: number): Observable<Author> {
    return this.httpClient.get<Author>(this.editorialURL + `detail/${id}`, header);
  }

  public add(author: Author): Observable<any> {
    return this.httpClient.post<any>(this.editorialURL + 'add', author, header);
  }

  public modify(author: Author, id: number): Observable<any> {
    return this.httpClient.put<any>(this.editorialURL + `modify/${id}`, author, header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.editorialURL + `delete/${id}`, header);
  }

}