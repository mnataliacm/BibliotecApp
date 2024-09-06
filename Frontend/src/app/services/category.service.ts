import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseURL = 'http://localhost:8080/api/library/genre/';
  private header = { headers: new HttpHeaders({ 'Content-TYpe': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.baseURL + 'all', this.header);
  }

  public detail(id: number): Observable<Category> {
    return this.httpClient.get<Category>(this.baseURL + `detail/${id}`, this.header);
  }

  public add(genre: Category): Observable<Category> {
    return this.httpClient.post<Category>(this.baseURL + 'add', genre, this.header);
  }

  public modify(genre: Category, id: number): Observable<any> {
    return this.httpClient.put<Category>(this.baseURL + `modify/${id}`, genre, this.header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.baseURL + `delete/${id}`, this.header);
  }

}