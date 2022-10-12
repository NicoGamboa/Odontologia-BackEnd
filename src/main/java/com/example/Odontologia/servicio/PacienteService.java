package com.example.Odontologia.servicio;

import com.example.Odontologia.Repository.DomicilioRepository;
import com.example.Odontologia.Repository.PacienteRepository;
import com.example.Odontologia.Entities.Paciente;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final DomicilioRepository domicilioRepository;
    private static final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, DomicilioRepository domicilioRepository) {
        this.pacienteRepository = pacienteRepository;
        this.domicilioRepository = domicilioRepository;
    }

    public Paciente registrar(Paciente paciente){
        logger.info("Registrando al paciente "+paciente.getApellido()+" "+paciente.getNombre());
        return pacienteRepository.save(paciente);
    }
    public Paciente buscar(Long id){
        logger.info("Buscando paciente con id: "+id);
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        Paciente paciente1 = null;
        if (paciente.isPresent()) {
            paciente1 = paciente.get();
        }
        return paciente1;
    }
    public List<Paciente> buscarTodos(){
        logger.info("Buscando todos los pacientes");
        return pacienteRepository.findAll();
    }
    public void borrarPaciente(Long id) throws ResourceNotFoundException {
        Paciente paciente= buscar(id);
        if (paciente!=null) {
            logger.info("Lo encontramos, entonces lo eliminamos");
            pacienteRepository.deleteById(id);
        }
        else {
            logger.error("No existe el pacientes con id= " +id);
            throw new ResourceNotFoundException("No existe el paciente con id= "+id);
        }
    }
    public Paciente actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Paciente paciente1= buscar(paciente.getId());
        if (paciente1!=null) {
            logger.info("Lo encontramos, entonces lo actualizamos");
            if (paciente.getDomicilio()==null||paciente.getDomicilio().getId()==null) {
                logger.info("Si no tiene un domicilio o id de domicilio le seteamos un domicilio");
                paciente.setDomicilio(paciente1.getDomicilio());
                return registrar(paciente);
            }else{
                return registrar(paciente);
            }
        }
        else {
            logger.error("No existe el pacientes con id= " +paciente.getId());
            throw new ResourceNotFoundException("No existe el paciente con id= "+paciente1.getId());
        }
    }
}
