import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProjetoService } from '../../services/projeto.service';
import { Projeto } from '../../models/projeto.model';
@Component({
  selector: 'app-projetos', standalone: true, imports: [CommonModule, FormsModule],
  template: `
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:20px">
      <h2>📁 Projetos</h2>
      <button class="btn btn-primary" (click)="showForm=!showForm">+ Novo Projeto</button>
    </div>
    <div class="card" *ngIf="showForm" style="margin-bottom:20px">
      <div class="form-group"><label>Nome</label><input [(ngModel)]="form.nome"></div>
      <div class="form-group"><label>Descrição</label><textarea [(ngModel)]="form.descricao" rows="2"></textarea></div>
      <button class="btn btn-primary" (click)="criar()">Salvar</button>
    </div>
    <div class="card">
      <table>
        <thead><tr><th>ID</th><th>Nome</th><th>Descrição</th><th>Status</th></tr></thead>
        <tbody>
          <tr *ngFor="let p of projetos">
            <td>#{{p.id}}</td><td>{{p.nome}}</td><td>{{p.descricao}}</td>
            <td><span class="badge" [style.background]="p.ativo?'#d1fae5':'#fee2e2'" [style.color]="p.ativo?'#065f46':'#991b1b'">{{p.ativo?'Ativo':'Inativo'}}</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class ProjetosComponent implements OnInit {
  projetos: Projeto[] = []; showForm = false; form: any = { nome:'', descricao:'' };
  constructor(private projetoService: ProjetoService) {}
  ngOnInit() { this.projetoService.listar().subscribe(p => this.projetos = p); }
  criar() { this.projetoService.criar(this.form).subscribe(p => { this.projetos.unshift(p); this.showForm = false; }); }
}
