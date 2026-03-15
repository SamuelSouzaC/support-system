import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';
import { environment } from '../../environments/environment';
@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private url = `${environment.apiUrl}/usuarios`;
  constructor(private http: HttpClient) {}
  listar(): Observable<Usuario[]> { return this.http.get<Usuario[]>(this.url); }
  criar(d: any): Observable<Usuario> { return this.http.post<Usuario>(this.url, d); }
  atualizar(id: number, d: any): Observable<Usuario> { return this.http.put<Usuario>(`${this.url}/${id}`, d); }
  deletar(id: number): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
