package com.api.disney.repositorios;

import com.api.disney.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    public ArrayList<Personaje> findByNombre(String nombre);

    public ArrayList<Personaje> findByEdad(Integer edad);

    public ArrayList<Personaje> findByPeso(Double peso);

    @Query(value = "SELECT * FROM personaje where id in (select id_personaje from personajes_por_pelicula where id_pelicula = ?)", nativeQuery = true)
    ArrayList<Personaje> findByPeliculaSerie(Integer movie);
    
}
