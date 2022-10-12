package com.example.Odontologia.Entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Paciente_id",nullable = false,referencedColumnName = "id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "Odontologo_id",nullable = false,referencedColumnName = "id")
    private Odontologo odontologo;
    private LocalDate fecha;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
