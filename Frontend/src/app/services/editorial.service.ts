import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Editorial } from '../models/editorial';

@Injectable({
  providedIn: 'root'
})
export class EditorialService {

  private baseURL = 'http://localhost:8080/api/library/editorial/';
  private header = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<Editorial[]> {
    return this.httpClient.get<Editorial[]>(this.baseURL + 'all', this.header);
  }

  public detail(id: number): Observable<Editorial> {
    return this.httpClient.get<Editorial>(this.baseURL + `detail/${id}`, this.header);
  }

  public add(editorial: Editorial): Observable<Editorial> {
    return this.httpClient.post<Editorial>(this.baseURL + 'add', editorial, this.header);
  }

  public modify(editorial: Editorial, id: number): Observable<Editorial> {
    return this.httpClient.put<Editorial>(this.baseURL + `modify/${id}`, editorial, this.header);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.baseURL + `delete/${id}`, this.header);
  }

}