import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  template: `
    <div class="login-wrap">
      <div class="login-card">
        <h1>Sistema de Suporte</h1>
        <p>Bem-vindo! Faça seu login</p>
        <div class="form-group"><label>Email</label><input type="email" [(ngModel)]="email" placeholder="seu@email.com"></div>
        <div class="form-group"><label>Senha</label><input type="password" [(ngModel)]="senha" placeholder="••••••••"></div>
        <div *ngIf="erro" class="erro">{{ erro }}</div>
        <button class="btn btn-primary" style="width:100%" (click)="login()" [disabled]="loading">
          {{ loading ? 'Entrando...' : 'Entrar' }}
        </button>
      </div>
    </div>
  `,
  styles: [`.login-wrap{display:flex;align-items:center;justify-content:center;min-height:100vh;background:#f5f6fa}
    .login-card{background:white;padding:40px;border-radius:12px;box-shadow:0 4px 24px rgba(0,0,0,.1);width:360px}
    h1{font-size:22px;margin-bottom:6px;color:#1e1b4b}p{color:#888;margin-bottom:24px;font-size:14px}
    .erro{background:#fee2e2;color:#991b1b;padding:10px;border-radius:6px;margin-bottom:12px;font-size:13px}`]
})
export class LoginComponent {
  email = ''; senha = ''; erro = ''; loading = false;
  constructor(private authService: AuthService, private router: Router) {}
  login() {
    this.loading = true; this.erro = '';
    this.authService.login({ email: this.email, senha: this.senha }).subscribe({
      next: () => this.router.navigate(['/dashboard']),
      error: () => { this.erro = 'Email ou senha inválidos.'; this.loading = false; }
    });
  }
}
