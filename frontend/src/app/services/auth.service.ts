import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/usuario.model';
import { environment } from '../../environments/environment';
@Injectable({ providedIn: 'root' })
export class AuthService {
  private userSubject = new BehaviorSubject<LoginResponse|null>(this.getStored());
  currentUser$ = this.userSubject.asObservable();
  constructor(private http: HttpClient, private router: Router) {}
  login(req: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.apiUrl}/auth/login`, req).pipe(
      tap(r => { localStorage.setItem('user', JSON.stringify(r)); this.userSubject.next(r); })
    );
  }
  logout() { localStorage.removeItem('user'); this.userSubject.next(null); this.router.navigate(['/login']); }
  getToken(): string|null { return this.getStored()?.token || null; }
  getPerfil(): string { return this.getStored()?.perfil || ''; }
  isLoggedIn(): boolean { return !!this.getToken(); }
  private getStored(): LoginResponse|null { const u = localStorage.getItem('user'); return u ? JSON.parse(u) : null; }
}
