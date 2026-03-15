package com.suporte.system.service;
import com.suporte.system.dto.*;
import com.suporte.system.model.Usuario;
import com.suporte.system.repository.UsuarioRepository;
import com.suporte.system.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    public LoginResponse login(LoginRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getSenha()));
        UserDetails ud = userDetailsService.loadUserByUsername(req.getEmail());
        String token = jwtService.gerarToken(ud);
        Usuario u = usuarioRepository.findByEmail(req.getEmail()).orElseThrow();
        return new LoginResponse(token, u.getNome(), u.getEmail(), u.getPerfil().name());
    }
}
