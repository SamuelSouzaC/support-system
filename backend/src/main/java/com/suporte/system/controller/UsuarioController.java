package com.suporte.system.controller;
import com.suporte.system.dto.UsuarioDTO;
import com.suporte.system.model.Usuario;
import com.suporte.system.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/usuarios") @RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listar() { return usuarioService.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) { return ResponseEntity.ok(usuarioService.buscarPorId(id)); }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDTO dto) { return ResponseEntity.ok(usuarioService.criar(dto)); }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) { return ResponseEntity.ok(usuarioService.atualizar(id, dto)); }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) { usuarioService.deletar(id); return ResponseEntity.noContent().build(); }
}
