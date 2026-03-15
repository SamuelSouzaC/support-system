package com.suporte.system.service;
import com.suporte.system.dto.ProjetoDTO;
import com.suporte.system.model.*;
import com.suporte.system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;
    public List<Projeto> listarAtivos() { return projetoRepository.findByAtivoTrue(); }
    public List<Projeto> listarTodos() { return projetoRepository.findAll(); }
    public Projeto buscarPorId(Long id) { return projetoRepository.findById(id).orElseThrow(); }
    public Projeto criar(ProjetoDTO dto, String email) {
        Usuario criador = usuarioRepository.findByEmail(email).orElseThrow();
        return projetoRepository.save(Projeto.builder().nome(dto.getNome()).descricao(dto.getDescricao()).ativo(true).criadoPor(criador).build());
    }
    public Projeto atualizar(Long id, ProjetoDTO dto) {
        Projeto p = buscarPorId(id); p.setNome(dto.getNome()); p.setDescricao(dto.getDescricao()); p.setAtivo(dto.isAtivo());
        return projetoRepository.save(p);
    }
    public void deletar(Long id) { projetoRepository.deleteById(id); }
}
