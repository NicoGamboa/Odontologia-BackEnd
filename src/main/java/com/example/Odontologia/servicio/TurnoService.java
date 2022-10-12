package com.example.Odontologia.servicio;


import com.example.Odontologia.Entities.Odontologo;
import com.example.Odontologia.Entities.Paciente;
import com.example.Odontologia.Entities.Turno;
import com.example.Odontologia.Repository.TurnoRepository;
import com.example.Odontologia.dto.TurnoDTO;
import com.example.Odontologia.exceptions.BadRequestException;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

   private final TurnoRepository turnoRepository;
   private final OdontologoService odontologoService;
   private final PacienteService pacienteService;
   private static final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    public TurnoDTO cargarTurno(TurnoDTO turno) throws BadRequestException {
        logger.info("Verificamos si el existen el odontologo y el paciente que se trata asignar");
        if (odontologoService.buscarOdontologoXId(turno.getOdontologo_id())!=null
                &&pacienteService.buscar(turno.getPaciente_id())!=null) {
            logger.info("El odontologo y el paciente existen, entoces cargamos el turno");
            Turno turnoEntity = new Turno();
            turnoEntity.setFecha(turno.getFecha());

            Paciente paciente = new Paciente();
            paciente.setId(turno.getPaciente_id());
            Odontologo odontologo = new Odontologo();
            odontologo.setId(turno.getOdontologo_id());

            turnoEntity.setPaciente(paciente);
            turnoEntity.setOdontologo(odontologo);

            Turno turnoGuardado = turnoRepository.save(turnoEntity);
            //alternativa A
            TurnoDTO turnoADevolver = new TurnoDTO();
            turnoADevolver.setFecha(turnoGuardado.getFecha());
            turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
            turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
            turnoADevolver.setId(turnoGuardado.getId());
            return turnoADevolver;
        }
        else{
            logger.error("El dontologo o el paciente no existen");
            throw new BadRequestException("El dontologo o el paciente no existen");
        }
    }
    public Optional<TurnoDTO> buscar(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        Optional<TurnoDTO> turnoDTOBuscado=Optional.empty();
        logger.info("Buscamos el turno con id: "+id);
        if (turnoBuscado.isPresent()){
            Turno turnoRealBuscado=turnoBuscado.get();
            TurnoDTO turnoDTOADevolver= new TurnoDTO();
            turnoDTOADevolver.setId(turnoRealBuscado.getId());
            turnoDTOADevolver.setFecha(turnoRealBuscado.getFecha());
            turnoDTOADevolver.setPaciente_id(turnoRealBuscado.getPaciente().getId());
            turnoDTOADevolver.setOdontologo_id(turnoRealBuscado.getOdontologo().getId());
            turnoDTOBuscado=Optional.of(turnoDTOADevolver);
        }
        return turnoDTOBuscado;
    }
    public List<TurnoDTO> listarTurno(){
        List<Turno> turnoList = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOList = new ArrayList<>();
        logger.info("Buscamos todos turno");
        if (turnoList!=null){
            for (Turno turno:
                 turnoList) {
                TurnoDTO turnoDTOADevolver= new TurnoDTO();
                turnoDTOADevolver.setId(turno.getId());
                turnoDTOADevolver.setFecha(turno.getFecha());
                turnoDTOADevolver.setPaciente_id(turno.getPaciente().getId());
                turnoDTOADevolver.setOdontologo_id(turno.getOdontologo().getId());
                turnoDTOList.add(turnoDTOADevolver);
            }
        }
        return turnoDTOList;
    }
    public void eliminarTurno (Long id) throws ResourceNotFoundException {
       Optional<TurnoDTO> turnoAEliminar = buscar(id);
       if (turnoAEliminar.isPresent()) {
           logger.info("Lo encontramos, entonces lo eliminamos");
           turnoRepository.deleteById(id);
       }
       else {
           logger.error("No existe el tuerno con id= "+id);
           throw new ResourceNotFoundException("No existe el tuerno con id= "+id);
       }
   }
    public TurnoDTO actualizarTurno (TurnoDTO turno) throws ResourceNotFoundException, BadRequestException {
        Optional<TurnoDTO> turnoAEliminar = buscar(turno.getId());
       if (turnoAEliminar.isPresent()) {
           logger.info("Lo encontramos");
           logger.info("Verificamos si el existen el odontologo y el paciente que se trata de actualizar");
           if (odontologoService.buscarOdontologoXId(turno.getOdontologo_id())!=null
                   &&pacienteService.buscar(turno.getPaciente_id())!=null) {
               logger.info("El odontologo y el paciente existen, entoces actualizamos el turno");

               Turno turnoEntity = new Turno();
               turnoEntity.setId(turno.getId());
               turnoEntity.setFecha(turno.getFecha());

               Paciente paciente = new Paciente();
               paciente.setId(turno.getPaciente_id());
               Odontologo odontologo = new Odontologo();
               odontologo.setId(turno.getOdontologo_id());

               turnoEntity.setPaciente(paciente);
               turnoEntity.setOdontologo(odontologo);

               Turno turnoGuardado = turnoRepository.save(turnoEntity);

               TurnoDTO turnoADevolver = new TurnoDTO();
               turnoADevolver.setFecha(turnoGuardado.getFecha());
               turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
               turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
               turnoADevolver.setId(turnoGuardado.getId());
               return turnoADevolver;
           }else {
               logger.error("El dontologo o el paciente no existen");
               throw new BadRequestException("El dontologo o el paciente no existen");
           }
       }else {
           logger.error("No existe el tuerno con id= "+ turno.getId());
           throw new ResourceNotFoundException("No existe el tuerno con id= "+ turno.getId());
       }
    }
}
