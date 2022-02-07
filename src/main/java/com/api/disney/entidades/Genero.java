package com.api.disney.entidades;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE genero SET alta = false WHERE id = ?")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String imagen;
    private Boolean alta;

    @OneToMany(mappedBy = "genero")
    private List<PeliculaSerie> peliculasSerie;
}
