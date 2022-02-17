package com.api.disney.controladores;

import com.api.disney.entidades.PeliculaSerie;
import com.api.disney.entidades.Personaje;
import com.api.disney.servicios.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public String listadoPersonajes(){
        return personajeService.listadoPersonajes();
    }

    @PostMapping
    public Personaje guardar(@ModelAttribute Personaje personaje, @RequestParam("file") MultipartFile image ) {
        return personajeService.guardar(personaje, image);
    }

    @PutMapping("/{id}")
    public Personaje modificar(@ModelAttribute Personaje personaje, @RequestParam("file") MultipartFile image, @PathVariable("id") Integer id) {
        return personajeService.modificar(personaje, image, id);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable("id") Integer id){
        personajeService.borrar(id);
    }

    @GetMapping(params = "id")
    public Optional<Personaje> obtenerPorId(@RequestParam("id") Integer id){
        return personajeService.obtenerPorId(id);
    }

    @GetMapping(params = "name")
    public ArrayList<Personaje> obtenerPorNombre(@RequestParam("name") String nombre){
        return personajeService.obtenerPorNombre(nombre);
    }

    @GetMapping(params = "age")
    public ArrayList<Personaje> obtenerPorEdad(@RequestParam("age") Integer edad){
        return personajeService.obtenerPorEdad(edad);
    }

    @GetMapping(params = "weight")
    public ArrayList<Personaje> obtenerPorPeso(@RequestParam("weight") Double peso){
        return personajeService.obtenerPorPeso(peso);
    }

    @GetMapping(params = "movies")
    public ArrayList<Personaje> obtenerPorPelicula(@RequestParam("movies") Integer movie){
        return personajeService.obtenerPorPelicula(movie);
    }
    
}
