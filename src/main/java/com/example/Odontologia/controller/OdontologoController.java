package com.example.Odontologia.controller;


import com.example.Odontologia.Entities.Odontologo;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import com.example.Odontologia.servicio.OdontologoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarAllOdontologo(){return ResponseEntity.ok(odontologoService.listarOdontologos());}

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){return ResponseEntity.ok(odontologoService.buscarOdontologoXId(id));}

    @PostMapping
    public ResponseEntity<Odontologo> registarOdontologo (@RequestBody Odontologo odontologo){
        odontologoService.cargarOdontologo(odontologo);
        return ResponseEntity.ok(odontologo);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo (@PathVariable Long id) throws ResourceNotFoundException {

            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se borró el odontologo con ID: "+id);
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo ) throws ResourceNotFoundException {

            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó el odontologo matricula: "+odontologo.getMatricula());
    }
}
