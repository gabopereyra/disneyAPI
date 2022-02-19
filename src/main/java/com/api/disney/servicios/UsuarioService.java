package com.api.disney.servicios;

import com.api.disney.entidades.Usuario;
import com.api.disney.repositorios.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Usuario guardar(Usuario usuario) throws Exception {
        if(existeMail(usuario.getMail())){
            throw new Exception("El email ya se encuentra registrado");
        }
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setAlta(true);

        emailService.sendThread(usuario.getMail());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public boolean validarPassword(String pass1, String pass2) {
        if (encoder.matches(pass1, pass2)) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Optional<Usuario> loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByMail(email);
    }

    @Transactional
    public Boolean existeMail(String email){
        return usuarioRepository.findByMail(email).isPresent();
    }

    public String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("apiJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2700000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
