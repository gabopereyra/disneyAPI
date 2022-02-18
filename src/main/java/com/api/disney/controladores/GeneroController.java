package com.api.disney.controladores;

import com.api.disney.entidades.Genero;
import com.api.disney.servicios.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<Genero> listadoGeneros(){
        return generoService.listadoGenero();
    }

    @PostMapping
    public Genero guardar(@ModelAttribute Genero genero, @RequestParam("file") MultipartFile image ) {
        return generoService.guardar(genero, image);
    }

    @PutMapping("/{id}")
    public Genero modificar(@ModelAttribute Genero genero, @RequestParam("file") MultipartFile image, @PathVariable("id") Integer id) {
        return generoService.modificar(genero, image, id);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable("id") Integer id){
        generoService.borrar(id);
    }

}
