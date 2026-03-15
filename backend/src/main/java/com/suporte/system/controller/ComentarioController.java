package com.suporte.system.controller;
import com.suporte.system.dto.ComentarioDTO;
import com.suporte.system.model.Comentario;
import com.suporte.system.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/comentarios") @RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioService comentarioService;
    @GetMapping("/chamado/{chamadoId}")
    public List<Comentario> listar(@PathVariable Long chamadoId) { return comentarioService.listarPorChamado(chamadoId); }
    @PostMapping
    public ResponseEntity<Comentario> criar(@RequestBody ComentarioDTO dto, Authentication auth) { return ResponseEntity.ok(comentarioService.criar(dto, auth.getName())); }
}
