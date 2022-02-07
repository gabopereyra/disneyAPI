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
import org.hibernate.annotations.SQLDelete;

@Data
@Entity
@SQLDelete(sql = "UPDATE personaje SET alta = false WHERE id = ?")
public class Personaje {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private Boolean alta;

    @ManyToMany
    @JoinTable(name = "personajes_por_cinematografia",
            joinColumns={
                    @JoinColumn(name="id_personaje")},
            inverseJoinColumns={
                    @JoinColumn(name="id_pelicula")})
    private List<PeliculaSerie> peliculasSerie;
}