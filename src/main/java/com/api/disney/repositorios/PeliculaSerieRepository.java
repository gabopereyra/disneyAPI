package com.api.disney.repositorios;

import com.api.disney.entidades.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
    public List<PeliculaSerie> findByTitulo(String nombre);

    @Query(value = "SELECT * FROM pelicula_serie where id_genero = ?", nativeQuery = true)
    public List<PeliculaSerie> findByGenero(Integer genero);
}
