package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class CarteiradeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "ID")
    private Integer cod_carteira;

    @NotEmpty(message = "PIS não pode estar vazio!")
    @Column(length = 14, nullable = false, unique = true, name = "PIS")
    @Pattern(regexp = "[0-9]{3}.[0-9]{5}.[0-9]{2}-[0-9]{1}", message = "O número do PIS não corresponde ao padrao esperado!")
    private String pis;

    @NotEmpty(message = "O número de carteira não pode estar vazio!")
    @Column(length = 7, name = "Carteira", unique = true)
    @Pattern(regexp = "[0-9]{7}" , message = "O número de carteira não cumpre o padrao!")
    private String numero_carteira;

    @NotEmpty(message = "O número de série não pode estar vazio!")
    @Column(length = 5, name = "Serie", unique = true)
    @Pattern(regexp = "[0-9]{3}-[0-9]{1}", message = "O número de série não cumpre o padrão")
    private String serie;

    @NotEmpty(message = "O UF da carteira não pode estar vazio!")
    @Column(length = 2, name = "UF")
    @Pattern(regexp = "[A-Z]{2}", message = "O UF é a abreviação do seu Estado, corrija!")
    private String uf;

    @OneToOne(mappedBy = "carteira")
    private Funcionario funcionario;

}
