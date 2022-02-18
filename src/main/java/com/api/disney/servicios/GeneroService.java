package com.api.disney.servicios;

import com.api.disney.entidades.Genero;
import com.api.disney.repositorios.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ArchivoService fileService;

    public List<Genero> listadoGenero() {
        return generoRepository.findAll();
    }

    public Genero guardar(Genero genero, MultipartFile image) {
        fileService.uploadFile(image);
        genero.setImagen(image.getOriginalFilename());
        genero.setAlta(true);
        return generoRepository.save(genero);

    }

    public Genero modificar(Genero genero, MultipartFile image, Integer id) {
        if(generoRepository.findById(id).isPresent()){
            fileService.uploadFile(image);
            genero.setImagen(image.getOriginalFilename());
            return generoRepository.save(genero);
        }
        return null;
    }

    public void borrar(Integer id) {
        generoRepository.deleteById(id);
    }
}
