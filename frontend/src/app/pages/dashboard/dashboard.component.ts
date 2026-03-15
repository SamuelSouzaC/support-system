import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChamadoService } from '../../services/chamado.service';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2 style="margin-bottom:20px">📊 Dashboard</h2>
    <div class="cards">
      <div class="card"><div class="num">{{ metricas?.totalAbertos || 0 }}</div><div class="lbl">Abertos</div></div>
      <div class="card"><div class="num">{{ metricas?.totalEmAndamento || 0 }}</div><div class="lbl">Em Andamento</div></div>
      <div class="card"><div class="num">{{ (metricas?.tempoMedioHoras | number:'1.0-1') || '—' }}h</div><div class="lbl">Tempo médio de resolução</div></div>
    </div>
    <div class="card" style="margin-top:20px">
      <h3>Chamados por Prioridade</h3>
      <div *ngFor="let item of metricas?.porPrioridade" style="margin-top:12px">
        <span class="badge" [class]="'badge-'+item[0].toLowerCase()">{{ item[0] }}</span>
        <span style="margin-left:10px;font-size:14px">{{ item[1] }} chamado(s)</span>
      </div>
    </div>
  `,
  styles: [`.cards{display:grid;grid-template-columns:repeat(3,1fr);gap:16px}
    .card{background:white;border-radius:10px;padding:20px;box-shadow:0 1px 4px rgba(0,0,0,.08)}
    .num{font-size:36px;font-weight:700;color:#4f46e5}.lbl{font-size:13px;color:#888;margin-top:4px}
    h3{font-size:16px;margin-bottom:8px}`]
})
export class DashboardComponent implements OnInit {
  metricas: any;
  constructor(private chamadoService: ChamadoService, public auth: AuthService) {}
  ngOnInit() { this.chamadoService.metricas().subscribe(m => this.metricas = m); }
}
