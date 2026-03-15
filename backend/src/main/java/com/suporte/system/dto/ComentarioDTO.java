package com.suporte.system.dto;
import lombok.Data;
@Data
public class ComentarioDTO {
    private Long id;
    private String texto;
    private Long chamadoId;
    private String autorNome;
}
