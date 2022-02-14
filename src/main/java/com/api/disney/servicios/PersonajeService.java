package com.api.disney.servicios;

import com.api.disney.entidades.Personaje;
import com.api.disney.repositorios.PersonajeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private ArchivoService fileService;

    @Transactional
    public Personaje guardar(Personaje personaje, MultipartFile image) {
        fileService.uploadFile(image);
        personaje.setImagen(image.getOriginalFilename());
        personaje.setAlta(true);
        return personajeRepository.save(personaje);
    }

    @Transactional
    public Personaje modificar(Personaje personaje, MultipartFile image, Integer id) {
        if(personajeRepository.findById(id).isPresent()){
            fileService.uploadFile(image);
            personaje.setImagen(image.getOriginalFilename());
            return personajeRepository.save(personaje);
        }
        return null;
    }

    @Transactional
    public void borrar(Integer id){
        personajeRepository.deleteById(id);
    }

    @Transactional
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

    @Transactional
    public ArrayList<Personaje> obtenerPorNombre(String nombre){
        return personajeRepository.findByNombre(nombre);
    }

    @Transactional
    public ArrayList<Personaje> obtenerPorEdad(Integer edad){
        return personajeRepository.findByEdad(edad);
    }

    @Transactional
    public ArrayList<Personaje> obtenerPorPeso(Double peso){
        return personajeRepository.findByPeso(peso);
    }

}
