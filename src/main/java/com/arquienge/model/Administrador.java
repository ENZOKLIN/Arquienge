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
    @Column(unique = true)
    private Integer id ;

    @Column(unique = true)
    private String nome;
    @Column(unique = true)
    private String sobrenome;

    @Column(length = 9, unique = true)
    @Size(max = 9, min = 9)
    private String usuario;

    @Column(length = 10, unique = true)
    @Size(max = 10, min = 10)
    private String senha;

}
