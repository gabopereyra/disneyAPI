package com.api.disney.repositorios;

import com.api.disney.entidades.Cinematografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinematograficaRepository extends JpaRepository<Cinematografia, Integer> {
}
