package com.api.disney.entidades;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@SQLDelete(sql = "UPDATE Usuario SET alta = false WHERE id = ?")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "formato no valido")
    @Column(unique = true)
    private String mail;
    private String password;
    private Boolean alta;
    private String token;
}
