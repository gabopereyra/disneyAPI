package com.api.disney.entidades;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE pelicula_serie SET alta = false WHERE id = ?")
public class PeliculaSerie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String titulo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;
    private Integer calificacion;

    @ManyToMany
    @JoinTable(name = "personajes_por_pelicula",
            joinColumns={
                    @JoinColumn(name="id_pelicula")},
            inverseJoinColumns={
                    @JoinColumn(name="id_personaje")})
    private List<Personaje> personajes;

    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
    private Boolean alta;
}
