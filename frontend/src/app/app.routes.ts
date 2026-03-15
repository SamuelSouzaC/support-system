import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent) },
  { path: 'dashboard', loadComponent: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent), canActivate: [authGuard] },
  { path: 'chamados', loadComponent: () => import('./pages/chamados/chamados.component').then(m => m.ChamadosComponent), canActivate: [authGuard] },
  { path: 'projetos', loadComponent: () => import('./pages/projetos/projetos.component').then(m => m.ProjetosComponent), canActivate: [authGuard] },
  { path: 'usuarios', loadComponent: () => import('./pages/usuarios/usuarios.component').then(m => m.UsuariosComponent), canActivate: [authGuard] },
  { path: '**', redirectTo: 'dashboard' }
];
