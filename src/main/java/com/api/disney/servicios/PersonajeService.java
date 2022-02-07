package com.api.disney.servicios;

import com.api.disney.entidades.Personaje;
import com.api.disney.repositorios.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private GuardadoArchivo guardadoArchivo;

    public Personaje guardar(Personaje personaje, MultipartFile image) throws IOException {
        guardadoArchivo.guardar(image);
        return personajeRepository.save(personaje);
    }


}
