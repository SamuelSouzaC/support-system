import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projeto } from '../models/projeto.model';
import { environment } from '../../environments/environment';
@Injectable({ providedIn: 'root' })
export class ProjetoService {
  private url = `${environment.apiUrl}/projetos`;
  constructor(private http: HttpClient) {}
  listar(): Observable<Projeto[]> { return this.http.get<Projeto[]>(this.url); }
  criar(d: any): Observable<Projeto> { return this.http.post<Projeto>(this.url, d); }
  atualizar(id: number, d: any): Observable<Projeto> { return this.http.put<Projeto>(`${this.url}/${id}`, d); }
  deletar(id: number): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
