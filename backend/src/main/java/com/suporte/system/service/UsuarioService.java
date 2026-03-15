package com.suporte.system.service;
import com.suporte.system.dto.UsuarioDTO;
import com.suporte.system.model.Usuario;
import com.suporte.system.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Usuario> listarTodos() { return usuarioRepository.findAll(); }
    public Usuario buscarPorId(Long id) { return usuarioRepository.findById(id).orElseThrow(); }
    public Usuario criar(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) throw new RuntimeException("Email já cadastrado");
        return usuarioRepository.save(Usuario.builder().nome(dto.getNome()).email(dto.getEmail())
            .senha(passwordEncoder.encode(dto.getSenha())).perfil(dto.getPerfil()).ativo(true).build());
    }
    public Usuario atualizar(Long id, UsuarioDTO dto) {
        Usuario u = buscarPorId(id);
        u.setNome(dto.getNome()); u.setPerfil(dto.getPerfil()); u.setAtivo(dto.isAtivo());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) u.setSenha(passwordEncoder.encode(dto.getSenha()));
        return usuarioRepository.save(u);
    }
    public void deletar(Long id) { usuarioRepository.deleteById(id); }
}
