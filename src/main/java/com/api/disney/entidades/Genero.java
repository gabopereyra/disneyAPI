package com.api.disney.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String imagen;


    @OneToMany(mappedBy = "genero")
    private List<PeliculaSerie> peliculasSerie;
}
