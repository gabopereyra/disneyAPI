package com.api.disney.repositorios;

import com.api.disney.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    public ArrayList<Personaje> findByNombre(String nombre);

    public ArrayList<Personaje> findByEdad(Integer edad);

    public ArrayList<Personaje> findByPeso(Double peso);
    
}
