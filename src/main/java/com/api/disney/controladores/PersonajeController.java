package com.api.disney.controladores;

import com.api.disney.entidades.Personaje;
import com.api.disney.servicios.ArchivoService;
import com.api.disney.servicios.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

}
