package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 9)
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    private String cep;

    private String cidade;
    private String bairro;
    private String rua;
    private int numero;

    @OneToOne(mappedBy = "endereco")
    private Funcionario funcionario;

    @OneToOne(mappedBy = "endereco")
    private Engenheiro engenheiro;

    @OneToOne(mappedBy = "endereco")
    private Proprietario proprietario;

    @OneToOne(mappedBy = "endereco")
    private Obra obra;



}
