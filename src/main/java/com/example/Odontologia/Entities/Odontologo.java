package com.example.Odontologia.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String matricula;
    @Column
    @NotNull
    private String nombre;
    @Column
    @NotNull
    private String apellido;
    @OneToMany(mappedBy = "odontologo",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turno=new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<Turno> getTurno() {
        return turno;
    }

    public void setTurno(Set<Turno> turno) {
        this.turno = turno;
    }

}
