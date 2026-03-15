package com.suporte.system.repository;

import com.suporte.system.model.HistoricoChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricoChamadoRepository extends JpaRepository<HistoricoChamado, Long> {
    List<HistoricoChamado> findByChamadoIdOrderByAlteradoEmAsc(Long chamadoId);
}
