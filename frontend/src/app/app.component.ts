import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  template: `
    <div *ngIf="auth.isLoggedIn()" class="layout">
      <nav class="sidebar">
        <div class="logo">🎯 Suporte</div>
        <div class="nav-links">
          <a routerLink="/dashboard" routerLinkActive="active">📊 Dashboard</a>
          <a routerLink="/chamados" routerLinkActive="active">🎫 Chamados</a>
          <a routerLink="/projetos" routerLinkActive="active">📁 Projetos</a>
          <a *ngIf="isAdmin()" routerLink="/usuarios" routerLinkActive="active">👥 Usuários</a>
        </div>
        <button class="logout-btn" (click)="auth.logout()">Sair</button>
      </nav>
      <main class="content"><router-outlet /></main>
    </div>
    <div *ngIf="!auth.isLoggedIn()"><router-outlet /></div>
  `,
  styles: [`
    .layout { display: flex; min-height: 100vh; }
    .sidebar {
      width: 200px;
      background: #1e1b4b;
      display: flex;
      flex-direction: column;
      position: fixed;
      top: 0;
      left: 0;
      height: 100vh;
      z-index: 100;
    }
    .logo { color: white; font-size: 18px; font-weight: 700; padding: 20px 20px 24px; }
    .nav-links { display: flex; flex-direction: column; flex: 1; }
    .sidebar a { color: #a5b4fc; padding: 12px 20px; text-decoration: none; font-size: 14px; }
    .sidebar a.active, .sidebar a:hover { background: rgba(255,255,255,.1); color: white; }
    .logout-btn {
      margin: 0 20px 20px;
      background: rgba(255,255,255,.1);
      color: #a5b4fc;
      border: none;
      padding: 10px;
      border-radius: 6px;
      cursor: pointer;
      font-size: 14px;
    }
    .logout-btn:hover { background: rgba(255,255,255,.2); }
    .content { flex: 1; padding: 24px; margin-left: 200px; min-height: 100vh; overflow-y: auto; }
  `]
})
export class AppComponent {
  constructor(public auth: AuthService) {}
  isAdmin(): boolean { return this.auth.getPerfil() === 'ADMIN'; }
}