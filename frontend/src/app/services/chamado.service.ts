import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chamado, Comentario } from '../models/chamado.model';
import { environment } from '../../environments/environment';
@Injectable({ providedIn: 'root' })
export class ChamadoService {
  private url = `${environment.apiUrl}/chamados`;
  constructor(private http: HttpClient) {}
  listar(): Observable<Chamado[]> { return this.http.get<Chamado[]>(this.url); }
  buscar(id: number): Observable<Chamado> { return this.http.get<Chamado>(`${this.url}/${id}`); }
  criar(d: any): Observable<Chamado> { return this.http.post<Chamado>(this.url, d); }
  atualizar(id: number, d: any): Observable<Chamado> { return this.http.put<Chamado>(`${this.url}/${id}`, d); }
  metricas(): Observable<any> { return this.http.get<any>(`${this.url}/metricas`); }
  comentarios(id: number): Observable<Comentario[]> { return this.http.get<Comentario[]>(`${environment.apiUrl}/comentarios/chamado/${id}`); }
  addComentario(d: any): Observable<Comentario> { return this.http.post<Comentario>(`${environment.apiUrl}/comentarios`, d); }
}
