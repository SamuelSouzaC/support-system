import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';
@Component({
  selector: 'app-usuarios', standalone: true, imports: [CommonModule, FormsModule],
  template: `
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:20px">
      <h2>👥 Usuários</h2>
      <button class="btn btn-primary" (click)="showForm=!showForm">+ Novo Usuário</button>
    </div>
    <div class="card" *ngIf="showForm" style="margin-bottom:20px">
      <div class="form-group"><label>Nome</label><input [(ngModel)]="form.nome"></div>
      <div class="form-group"><label>Email</label><input type="email" [(ngModel)]="form.email"></div>
      <div class="form-group"><label>Senha</label><input type="password" [(ngModel)]="form.senha"></div>
      <div class="form-group"><label>Perfil</label>
        <select [(ngModel)]="form.perfil"><option value="ACIONAMENTO">Acionamento</option><option value="ANALISTA">Analista</option><option value="ADMIN">Admin</option></select>
      </div>
      <button class="btn btn-primary" (click)="criar()">Salvar</button>
    </div>
    <div class="card">
      <table>
        <thead><tr><th>Nome</th><th>Email</th><th>Perfil</th><th>Status</th><th>Ações</th></tr></thead>
        <tbody>
          <tr *ngFor="let u of usuarios">
            <td>{{u.nome}}</td><td>{{u.email}}</td><td>{{u.perfil}}</td>
            <td><span class="badge" [style.background]="u.ativo?'#d1fae5':'#fee2e2'" [style.color]="u.ativo?'#065f46':'#991b1b'">{{u.ativo?'Ativo':'Inativo'}}</span></td>
            <td><button class="btn btn-danger btn-sm" (click)="deletar(u.id)">Remover</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class UsuariosComponent implements OnInit {
  usuarios: Usuario[] = []; showForm = false; form: any = { nome:'', email:'', senha:'', perfil:'ANALISTA' };
  constructor(private usuarioService: UsuarioService) {}
  ngOnInit() { this.usuarioService.listar().subscribe(u => this.usuarios = u); }
  criar() { this.usuarioService.criar(this.form).subscribe(u => { this.usuarios.unshift(u); this.showForm = false; }); }
  deletar(id: number) { this.usuarioService.deletar(id).subscribe(() => this.usuarios = this.usuarios.filter(u => u.id !== id)); }
}
