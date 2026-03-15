import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChamadoService } from '../../services/chamado.service';
import { ProjetoService } from '../../services/projeto.service';
import { Chamado } from '../../models/chamado.model';
import { Projeto } from '../../models/projeto.model';
@Component({
  selector: 'app-chamados', standalone: true, imports: [CommonModule, FormsModule],
  template: `
    <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:20px">
      <h2>🎫 Chamados</h2>
      <button class="btn btn-primary" (click)="showForm=!showForm">+ Novo Chamado</button>
    </div>
    <div class="card" *ngIf="showForm" style="margin-bottom:20px">
      <h3 style="margin-bottom:16px">Novo Chamado</h3>
      <div class="form-group"><label>Título</label><input [(ngModel)]="form.titulo"></div>
      <div class="form-group"><label>Descrição</label><textarea [(ngModel)]="form.descricao" rows="3"></textarea></div>
      <div class="form-group"><label>Prioridade</label>
        <select [(ngModel)]="form.prioridade"><option value="BAIXA">Baixa</option><option value="MEDIA">Média</option><option value="ALTA">Alta</option><option value="CRITICA">Crítica</option></select>
      </div>
      <div class="form-group"><label>Projeto</label>
        <select [(ngModel)]="form.projetoId"><option *ngFor="let p of projetos" [value]="p.id">{{p.nome}}</option></select>
      </div>
      <button class="btn btn-primary" (click)="criar()">Salvar</button>
    </div>
    <div class="card">
      <table>
        <thead><tr><th>ID</th><th>Título</th><th>Status</th><th>Prioridade</th><th>Aberto em</th><th>Ações</th></tr></thead>
        <tbody>
          <tr *ngFor="let c of chamados">
            <td>#{{c.id}}</td><td>{{c.titulo}}</td>
            <td><span class="badge" [class]="'badge-'+c.status.toLowerCase()">{{c.status}}</span></td>
            <td><span class="badge" [class]="'badge-'+c.prioridade.toLowerCase()">{{c.prioridade}}</span></td>
            <td>{{c.abertoEm | date:'dd/MM/yyyy'}}</td>
            <td><button class="btn btn-sm" (click)="fechar(c)">Fechar</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  `
})
export class ChamadosComponent implements OnInit {
  chamados: Chamado[] = []; projetos: Projeto[] = []; showForm = false;
  form: any = { titulo:'', descricao:'', prioridade:'MEDIA', projetoId: null };
  constructor(private chamadoService: ChamadoService, private projetoService: ProjetoService) {}
  ngOnInit() {
    this.chamadoService.listar().subscribe(c => this.chamados = c);
    this.projetoService.listar().subscribe(p => this.projetos = p);
  }
  criar() { this.chamadoService.criar(this.form).subscribe(c => { this.chamados.unshift(c); this.showForm = false; }); }
  fechar(c: Chamado) { this.chamadoService.atualizar(c.id, {...c, status:'FECHADO'}).subscribe(u => { const i = this.chamados.findIndex(x=>x.id===c.id); this.chamados[i]=u; }); }
}
