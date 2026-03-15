package com.suporte.system.repository;

import com.suporte.system.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByAnalistaId(Long analistaId);
    List<Chamado> findByStatus(StatusChamado status);
    List<Chamado> findByProjetoId(Long projetoId);
    List<Chamado> findByPrioridade(Prioridade prioridade);

    @Query("SELECT c.analista.nome, COUNT(c) FROM Chamado c WHERE c.analista IS NOT NULL GROUP BY c.analista.nome")
    List<Object[]> countChamadosPorAnalista();

    @Query("SELECT c.prioridade, COUNT(c) FROM Chamado c GROUP BY c.prioridade")
    List<Object[]> countChamadosPorPrioridade();

    @Query("SELECT MONTH(c.abertoEm), COUNT(c) FROM Chamado c GROUP BY MONTH(c.abertoEm)")
    List<Object[]> countChamadosPorMes();

    @Query(value = "SELECT AVG(EXTRACT(EPOCH FROM (fechado_em - aberto_em))/3600) FROM chamados WHERE fechado_em IS NOT NULL", nativeQuery = true)
    Double calcularTempoMedioResolucaoHoras();
}