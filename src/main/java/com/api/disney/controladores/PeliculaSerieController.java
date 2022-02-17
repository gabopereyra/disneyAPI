package com.api.disney.controladores;

import com.api.disney.entidades.PeliculaSerie;
import com.api.disney.servicios.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {
    @Autowired
    private PeliculaSerieService peliculaSerieService;

    @GetMapping
    public String listadoPeliculas(){
        return peliculaSerieService.listadoPeliculas();
    }

    @PostMapping
    public PeliculaSerie guardar(@ModelAttribute PeliculaSerie peliculaSerie, @RequestParam("file") MultipartFile image ) {
        return peliculaSerieService.guardar(peliculaSerie, image);
    }

    @PutMapping("/{id}")
    public PeliculaSerie modificar(@ModelAttribute PeliculaSerie peliculaSerie, @RequestParam("file") MultipartFile image, @PathVariable("id") Integer id) {
        return peliculaSerieService.modificar(peliculaSerie, image, id);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable("id") Integer id){
        peliculaSerieService.borrar(id);
    }

    @GetMapping(params = "id")
    public Optional<PeliculaSerie> obtenerPorId(@RequestParam("id") Integer id){
        return peliculaSerieService.obtenerPorId(id);
    }

    @GetMapping(params = "name")
    public List<PeliculaSerie> obtenerPorNombre(@RequestParam("name") String nombre){
        return peliculaSerieService.obtenerPorNombre(nombre);
    }
    @GetMapping(params = "genre")
    public List<PeliculaSerie> obtenerPorGenero(@RequestParam("genre") Integer genre){
        return peliculaSerieService.obtenerPorGenero(genre);
    }
    @GetMapping(params = "order")
    public List<PeliculaSerie> ordenarPorFecha(@RequestParam("order") String order){
        return peliculaSerieService.ordenarPorFecha(order);
    }

}
