package com.api.disney.servicios;

import com.api.disney.entidades.PeliculaSerie;
import com.api.disney.repositorios.PeliculaSerieRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaSerieService {
    @Autowired
    private PeliculaSerieRepository peliculaSerieRepository;

    @Autowired
    private ArchivoService fileService;

    @Transactional
    public PeliculaSerie guardar(PeliculaSerie peliculaSerie, MultipartFile image) {
        fileService.uploadFile(image);
        peliculaSerie.setImagen(image.getOriginalFilename());
        peliculaSerie.setAlta(true);
        return peliculaSerieRepository.save(peliculaSerie);
    }

    @Transactional
    public PeliculaSerie modificar(PeliculaSerie peliculaSerie, MultipartFile image, Integer id) {
        if(peliculaSerieRepository.findById(id).isPresent()){
            fileService.uploadFile(image);
            peliculaSerie.setImagen(image.getOriginalFilename());
            return peliculaSerieRepository.save(peliculaSerie);
        }
        return null;
    }

    @Transactional
    public void borrar(Integer id){
        peliculaSerieRepository.deleteById(id);
    }

    @Transactional
    public String listadoPeliculas(){
        List<PeliculaSerie> listado = peliculaSerieRepository.findAll();

        JSONArray dataPrincipal = new JSONArray();

        for (PeliculaSerie elemento: listado) {
            JSONObject objUnitario = new JSONObject();
            objUnitario.put("titulo", elemento.getTitulo());
            objUnitario.put("imagen", elemento.getImagen());
            objUnitario.put("fechaCreacion", elemento.getFechaCreacion());
            // Objeto dentro de objeto
            dataPrincipal.put(objUnitario);
        }

        return dataPrincipal.toString();
    }

    public Optional<PeliculaSerie> obtenerPorId(Integer id){
        return peliculaSerieRepository.findById(id);
    }

    public List<PeliculaSerie> obtenerPorNombre(String nombre) {
        return peliculaSerieRepository.findByTitulo(nombre);
    }

    public List<PeliculaSerie> obtenerPorGenero(Integer genre) {
        return peliculaSerieRepository.findByGenero(genre);
    }

    @Transactional
    public List<PeliculaSerie> ordenarPorFecha(String order) {
        if (order.equals("ASC")) {
            return peliculaSerieRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
        } else if (order.equals("DESC")) {
            return peliculaSerieRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
        } else {
            return null;
        }
    }
}
