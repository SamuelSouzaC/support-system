package com.suporte.system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_chamado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoChamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @ManyToOne
    @JoinColumn(name = "alterado_por_id")
    private Usuario alteradoPor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior")
    private StatusChamado statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_novo")
    private StatusChamado statusNovo;

    private String observacao;

    @Column(name = "alterado_em")
    private LocalDateTime alteradoEm;

    @PrePersist
    public void prePersist() {
        this.alteradoEm = LocalDateTime.now();
    }
}
