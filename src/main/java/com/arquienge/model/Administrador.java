package com.arquienge.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Data
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String nome;
    private String sobrenome;

    @Column(length = 9)
    @Size(max = 9, min = 9)
    private String usuario;

    @Column(length = 10)
    @Size(max = 10, min = 10)
    private String senha;

}
