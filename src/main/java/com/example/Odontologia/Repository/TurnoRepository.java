package com.example.Odontologia.Repository;

import com.example.Odontologia.Entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {
}
