package com.suporte.system.service;
import com.suporte.system.dto.ComentarioDTO;
import com.suporte.system.model.*;
import com.suporte.system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    public List<Comentario> listarPorChamado(Long chamadoId) { return comentarioRepository.findByChamadoIdOrderByCriadoEmAsc(chamadoId); }
    public Comentario criar(ComentarioDTO dto, String email) {
        Chamado chamado = chamadoRepository.findById(dto.getChamadoId()).orElseThrow();
        Usuario autor = usuarioRepository.findByEmail(email).orElseThrow();
        return comentarioRepository.save(Comentario.builder().texto(dto.getTexto()).chamado(chamado).autor(autor).build());
    }
}
