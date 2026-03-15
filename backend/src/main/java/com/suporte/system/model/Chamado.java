package com.suporte.system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chamados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusChamado status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "aberto_por_id")
    private Usuario abertoPor;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private Usuario analista;

    @Column(name = "aberto_em")
    private LocalDateTime abertoEm;

    @Column(name = "fechado_em")
    private LocalDateTime fechadoEm;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL)
    private List<HistoricoChamado> historico;

    @PrePersist
    public void prePersist() {
        this.abertoEm = LocalDateTime.now();
        if (this.status == null) this.status = StatusChamado.ABERTO;
    }
}
