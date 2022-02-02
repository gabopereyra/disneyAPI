package com.api.disney.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Cinematografia {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String imagen;
    private String titulo;

    @JsonFormat(pattern="dd/mm/yyyy")
    private LocalDate fechaCreacion;

    private Integer calificacion;

    @ManyToMany(mappedBy="peliculasSerie")
    private List<Personaje> personajes;

    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
}
