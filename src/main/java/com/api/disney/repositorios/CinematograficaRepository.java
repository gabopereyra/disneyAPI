package com.api.disney.repositorios;

import com.api.disney.entidades.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinematograficaRepository extends JpaRepository<PeliculaSerie, Integer> {
}
