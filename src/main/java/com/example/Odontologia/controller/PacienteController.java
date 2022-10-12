package com.example.Odontologia.controller;



import com.example.Odontologia.Entities.Paciente;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import com.example.Odontologia.servicio.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registarPaciente (@RequestBody Paciente paciente){
       pacienteService.registrar(paciente);
       return  ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarAllPacientes (){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){return ResponseEntity.ok(pacienteService.buscar(id));}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente (@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.borrarPaciente(id);
        return ResponseEntity.ok("Se borró el paciente con ID: "+id);
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) throws ResourceNotFoundException {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó el paciente con : "+paciente.getApellido()+" "+paciente.getNombre());
    }
}
