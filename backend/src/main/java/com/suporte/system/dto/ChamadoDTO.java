package com.suporte.system.dto;
import com.suporte.system.model.Prioridade;
import com.suporte.system.model.StatusChamado;
import lombok.Data;
@Data
public class ChamadoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private StatusChamado status;
    private Prioridade prioridade;
    private Long projetoId;
    private Long analistaId;
}
