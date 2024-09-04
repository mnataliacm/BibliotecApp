import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Genre } from '../models/genre';

const header = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private genreURL = 'http://localhost:8080/api/library/genre/';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Genre[]> {
    return this.httpClient.get<Genre[]>(this.genreURL + 'all', header);
  }

  public detail(id: number): Observable<Genre> {
    return this.httpClient.get<Genre>(this.genreURL + `detail/${id}`, header);
  }

  public add(genre: Genre): Observable<any> {
    return this.httpClient.post<any>(this.genreURL + 'add', genre, header);
  }

  public modify(genre: Genre, id: number): Observable<any> {
    return this.httpClient.put<any>(this.genreURL + `modify/${id}`, genre, header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.genreURL + `delete/${id}`, header);
  }

}