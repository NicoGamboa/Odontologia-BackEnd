package com.example.Odontologia.servicio;

import com.example.Odontologia.Repository.OdontologoRepository;
import com.example.Odontologia.Entities.Odontologo;
import com.example.Odontologia.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public List<Odontologo> listarOdontologos(){
        logger.info("Buscando todos los odontologos");
        return odontologoRepository.findAll();}
    public Odontologo cargarOdontologo(Odontologo odontologo){
        logger.info("Cargando el odontologo matricula: "+odontologo.getMatricula());
        return  odontologoRepository.save(odontologo);}
    public Odontologo buscarOdontologoXId (Long id) {
        logger.info("Buscando odontologo con id: "+id);
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        Odontologo odontologo1 = null;
        if (odontologo.isPresent()) {
            odontologo1=odontologo.get();
        }
        return odontologo1;
    }
    public void eliminarOdontologo (Long id) throws ResourceNotFoundException {
        Odontologo odontologo=buscarOdontologoXId(id);
        if(odontologo!=null) {
            logger.info("Lo encontramos, entonces lo eliminamos");
            odontologoRepository.deleteById(id);
        }
        else {
            logger.error("No existe el odontologo con id= " +id);
            throw new ResourceNotFoundException("No existe el odontologo con id= "+id);
        }
    }
    public Odontologo actualizarOdontologo (Odontologo odontologo) throws ResourceNotFoundException {
        Odontologo odontologo1 = buscarOdontologoXId(odontologo.getId());
        if (odontologo != null) {
            logger.info("Lo encontramos, entonces lo actualizamos");
            cargarOdontologo(odontologo);
            return odontologo;
        } else {
            logger.error("No existe el odontologo con id= " + odontologo.getId());
            throw new ResourceNotFoundException("No existe el odontolog con id= " + odontologo.getId());
        }
    }
}
