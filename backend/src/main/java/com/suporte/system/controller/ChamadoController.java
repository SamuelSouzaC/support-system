package com.suporte.system.controller;
import com.suporte.system.dto.ChamadoDTO;
import com.suporte.system.model.Chamado;
import com.suporte.system.service.ChamadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/chamados") @RequiredArgsConstructor
public class ChamadoController {
    private final ChamadoService chamadoService;
    @GetMapping
    public List<Chamado> listar() { return chamadoService.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscar(@PathVariable Long id) { return ResponseEntity.ok(chamadoService.buscarPorId(id)); }
    @PostMapping
    public ResponseEntity<Chamado> criar(@RequestBody ChamadoDTO dto, Authentication auth) { return ResponseEntity.ok(chamadoService.criar(dto, auth.getName())); }
    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(@PathVariable Long id, @RequestBody ChamadoDTO dto, Authentication auth) { return ResponseEntity.ok(chamadoService.atualizar(id, dto, auth.getName())); }
    @GetMapping("/metricas")
    public ResponseEntity<Map<String, Object>> metricas() { return ResponseEntity.ok(chamadoService.metricas()); }
}
