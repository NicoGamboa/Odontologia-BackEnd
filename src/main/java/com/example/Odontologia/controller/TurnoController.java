package com.example.Odontologia.controller;

import com.example.Odontologia.dto.TurnoDTO;
import com.example.Odontologia.exceptions.BadRequestException;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import com.example.Odontologia.servicio.OdontologoService;
import com.example.Odontologia.servicio.PacienteService;
import com.example.Odontologia.servicio.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {
            return ResponseEntity.ok(turnoService.cargarTurno(turno));
    }

    @GetMapping()
    public ResponseEntity<List<TurnoDTO>> buscarAllTurnos(){
        return ResponseEntity.ok(turnoService.listarTurno());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado=turnoService.buscar(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno con id= "+id);
    }
    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

}
