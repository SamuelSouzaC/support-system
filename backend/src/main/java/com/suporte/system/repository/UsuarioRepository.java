package com.suporte.system.repository;

import com.suporte.system.model.Usuario;
import com.suporte.system.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByPerfil(Perfil perfil);
    boolean existsByEmail(String email);
}
