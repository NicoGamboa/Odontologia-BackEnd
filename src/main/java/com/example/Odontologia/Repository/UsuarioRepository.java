package com.example.Odontologia.Repository;


import com.example.Odontologia.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findUsuarioByEmail(String email);
}
