package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FerramentasUsadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "usada", updatable = false)
    private boolean usada;

    @NotNull
    @Column(name = "quantidade", updatable = false)
    private int qtd;

    @JoinColumn(name = "id_diario")
    @ManyToOne
    @NotNull
    private DiarioDeObra diarioDeObra;


    @JoinColumn(name = "id_ferramenta")
    @ManyToOne
    @NotNull
    private Ferramenta ferramenta;


}
