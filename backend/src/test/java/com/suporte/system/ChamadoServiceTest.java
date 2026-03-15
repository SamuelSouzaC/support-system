package com.suporte.system;
import com.suporte.system.model.*;
import com.suporte.system.repository.*;
import com.suporte.system.service.ChamadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ChamadoServiceTest {
    @Mock ChamadoRepository chamadoRepository;
    @Mock UsuarioRepository usuarioRepository;
    @Mock ProjetoRepository projetoRepository;
    @Mock HistoricoChamadoRepository historicoRepository;
    @InjectMocks ChamadoService chamadoService;

    @Test
    void deveListarTodosChamados() {
        when(chamadoRepository.findAll()).thenReturn(List.of(new Chamado(), new Chamado()));
        assertEquals(2, chamadoService.listarTodos().size());
    }

    @Test
    void deveLancarExcecaoQuandoChamadoNaoEncontrado() {
        when(chamadoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> chamadoService.buscarPorId(99L));
    }
}
