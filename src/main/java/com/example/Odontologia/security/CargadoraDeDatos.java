package com.example.Odontologia.security;

import com.example.Odontologia.Entities.RolUsuario;
import com.example.Odontologia.Entities.Usuario;
import com.example.Odontologia.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String passHashUsuario=passwordEncoder.encode("usuario");
        Usuario usuario= new Usuario();
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@gmail.com");
        usuario.setPassword(passHashUsuario);
        usuario.setUsuarioRole(RolUsuario.ROLE_USER);
        usuarioRepository.save(usuario);
        String passHashAdmin=passwordEncoder.encode("admin");
        Usuario usuarioAdmin= new Usuario();
        usuarioAdmin.setNombre("Nicolas");
        usuarioAdmin.setEmail("nicolas@gmail.com");
        usuarioAdmin.setPassword(passHashAdmin);
        usuarioAdmin.setUsuarioRole(RolUsuario.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
}
