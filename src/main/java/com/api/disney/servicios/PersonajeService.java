package com.api.disney.servicios;

import com.api.disney.entidades.Personaje;
import com.api.disney.repositorios.PersonajeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public String listadoPersonajes(){
        List<Personaje> listado = (ArrayList<Personaje>) personajeRepository.findAll();

        JSONArray dataPrincipal = new JSONArray();

        for (Personaje elemento: listado) {
            JSONObject objUnitario = new JSONObject();
            objUnitario.put("name", elemento.getNombre());
            objUnitario.put("imagen", elemento.getImagen());
            // Objeto dentro de objeto
            dataPrincipal.put(objUnitario);
        }
        
        return dataPrincipal.toString();
    }


}
