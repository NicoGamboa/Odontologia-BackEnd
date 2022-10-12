package com.example.Odontologia.servicio;

import com.example.Odontologia.Entities.Odontologo;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(3)
    void listarOdontologos() {
        List<Odontologo> odontologosBuscado = odontologoService.listarOdontologos();
        assertNotNull(odontologosBuscado);
    }

    @Test
    @Order(1)
    void cargarOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Dientes");
        odontologo.setApellido("Sanos");
        odontologo.setMatricula("GP1689");
        Odontologo odontologoGuardado = odontologoService.cargarOdontologo(odontologo);
        assertEquals(1,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    void buscarOdontologoXId() {
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(1L);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(5)
    void eliminarOdontologo() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(1L);
        assertNull(odontologoBuscado);
    }

    @Test
    @Order(4)
    void actualizarOdontologo() throws ResourceNotFoundException {
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Dientes");
        odontologo.setApellido("Cariados");
        odontologo.setMatricula("GP1689");
        odontologoService.actualizarOdontologo(odontologo);
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(1L);
        assertEquals(odontologo.getApellido(),odontologoBuscado.getApellido());

    }
}