package com.example.Odontologia.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String calle;
    @Column
    @NotNull
    private Integer numero;
    @Column
    @NotNull
    private String localidad;
    @Column
    @NotNull
    private String provincia;

    public Integer getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
