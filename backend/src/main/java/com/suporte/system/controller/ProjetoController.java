package com.suporte.system.controller;
import com.suporte.system.dto.ProjetoDTO;
import com.suporte.system.model.Projeto;
import com.suporte.system.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/projetos") @RequiredArgsConstructor
public class ProjetoController {
    private final ProjetoService projetoService;
    @GetMapping
    public List<Projeto> listar() { return projetoService.listarAtivos(); }
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Long id) { return ResponseEntity.ok(projetoService.buscarPorId(id)); }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Projeto> criar(@RequestBody ProjetoDTO dto, Authentication auth) { return ResponseEntity.ok(projetoService.criar(dto, auth.getName())); }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @RequestBody ProjetoDTO dto) { return ResponseEntity.ok(projetoService.atualizar(id, dto)); }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) { projetoService.deletar(id); return ResponseEntity.noContent().build(); }
}
