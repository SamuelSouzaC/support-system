package com.suporte.system.dto;
import com.suporte.system.model.Perfil;
import lombok.Data;
@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Perfil perfil;
    private boolean ativo;
}
