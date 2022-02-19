package com.api.disney.controladores;

import com.api.disney.entidades.Usuario;
import com.api.disney.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public Usuario guardar(@RequestBody Usuario usuario) throws Exception{
        return usuarioService.guardar(usuario);
    }


    @PostMapping("/login")
    public Usuario loginUser(@RequestParam("email") String email, @RequestParam("password") String pass) throws Exception {
        if(usuarioService.existeMail(email)){
            Usuario usuario = usuarioService.loadUserByUsername(email).get();
            if (usuarioService.validarPassword(pass, usuario.getPassword())) {
                String token = usuarioService.getJWTToken(usuario.getMail());
                usuario.setToken(token);
                return usuario;
            } else {
                throw new Exception("Las credenciales no son válidas.");
            }
        } else {
            throw new Exception("Las credenciales no son válidas.");
        }
    };
}
