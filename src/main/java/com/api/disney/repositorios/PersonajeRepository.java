package com.api.disney.repositorios;

import com.api.disney.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    List<Personaje> findByPeso(Double peso);

    @Query(value = "SELECT * FROM personaje where id in (select id_personaje from personajes_por_pelicula where id_pelicula = ?)", nativeQuery = true)
    List<Personaje> findByPeliculaSerie(Integer movie);
    
}
