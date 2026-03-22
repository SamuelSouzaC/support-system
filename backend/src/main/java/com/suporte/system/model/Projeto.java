package com.suporte.system.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projetos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "criado_por_id")
    @JsonIgnoreProperties({"senha", "chamadosAtribuidos", "chamadosAbertos", "hibernateLazyInitializer"})
    private Usuario criadoPor;

    @JsonIgnore
    @OneToMany(mappedBy = "projeto")
    private List<Chamado> chamados;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}