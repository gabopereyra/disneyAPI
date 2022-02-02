package com.api.disney.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Personaje {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

    @ManyToMany
    @JoinTable(name = "personajes_por_cinematografia",
            joinColumns={
                    @JoinColumn(name="id_personaje")},
            inverseJoinColumns={
                    @JoinColumn(name="id_pelicula")})
    private List<Cinematografia> peliculasSerie;
}