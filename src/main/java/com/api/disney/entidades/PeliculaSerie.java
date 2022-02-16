package com.api.disney.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE peliculaserie SET alta = false WHERE id = ?")
public class PeliculaSerie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String titulo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;
    private Integer calificacion;

    @ManyToMany(mappedBy="peliculasSerie")
    private List<Personaje> personajes;

    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
    private Boolean alta;
}
