package com.example.Odontologia.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TurnoDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long odontologo_id;
    @NotNull
    private Long paciente_id;
    @NotNull
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
