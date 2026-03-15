package com.suporte.system.service;
import com.suporte.system.dto.ChamadoDTO;
import com.suporte.system.model.*;
import com.suporte.system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
@Service @RequiredArgsConstructor
public class ChamadoService {
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProjetoRepository projetoRepository;
    private final HistoricoChamadoRepository historicoRepository;
    public List<Chamado> listarTodos() { return chamadoRepository.findAll(); }
    public Chamado buscarPorId(Long id) { return chamadoRepository.findById(id).orElseThrow(); }
    public Chamado criar(ChamadoDTO dto, String email) {
        Usuario abertoPor = usuarioRepository.findByEmail(email).orElseThrow();
        Projeto projeto = projetoRepository.findById(dto.getProjetoId()).orElseThrow();
        return chamadoRepository.save(Chamado.builder().titulo(dto.getTitulo()).descricao(dto.getDescricao())
            .status(StatusChamado.ABERTO).prioridade(dto.getPrioridade()).projeto(projeto).abertoPor(abertoPor).build());
    }
    public Chamado atualizar(Long id, ChamadoDTO dto, String email) {
        Chamado c = buscarPorId(id);
        StatusChamado anterior = c.getStatus();
        c.setTitulo(dto.getTitulo()); c.setDescricao(dto.getDescricao()); c.setPrioridade(dto.getPrioridade());
        if (dto.getStatus() != null && dto.getStatus() != anterior) {
            c.setStatus(dto.getStatus());
            if (dto.getStatus() == StatusChamado.FECHADO) c.setFechadoEm(LocalDateTime.now());
            Usuario alt = usuarioRepository.findByEmail(email).orElseThrow();
            historicoRepository.save(HistoricoChamado.builder().chamado(c).alteradoPor(alt).statusAnterior(anterior).statusNovo(dto.getStatus()).build());
        }
        if (dto.getAnalistaId() != null) {
            c.setAnalista(usuarioRepository.findById(dto.getAnalistaId()).orElseThrow());
            if (c.getStatus() == StatusChamado.ABERTO) c.setStatus(StatusChamado.EM_ANDAMENTO);
        }
        return chamadoRepository.save(c);
    }
    public Map<String, Object> metricas() {
        return Map.of(
            "totalAbertos", chamadoRepository.findByStatus(StatusChamado.ABERTO).size(),
            "totalEmAndamento", chamadoRepository.findByStatus(StatusChamado.EM_ANDAMENTO).size(),
            "porAnalista", chamadoRepository.countChamadosPorAnalista(),
            "porPrioridade", chamadoRepository.countChamadosPorPrioridade(),
            "porMes", chamadoRepository.countChamadosPorMes(),
            "tempoMedioHoras", chamadoRepository.calcularTempoMedioResolucaoHoras()
        );
    }
}
