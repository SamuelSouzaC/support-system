package com.suporte.system.repository;

import com.suporte.system.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByChamadoIdOrderByCriadoEmAsc(Long chamadoId);
}
