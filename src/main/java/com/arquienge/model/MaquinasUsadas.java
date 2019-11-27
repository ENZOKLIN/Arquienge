package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaquinasUsadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "usada", updatable = false)
    private boolean usada;

    @Column(name = "quantidade", updatable = false)
    private int qtd;

    @JoinColumn(name = "id_maquina")
    @ManyToOne
    @NotNull
    private Maquina maquina;

    @JoinColumn(name = "id_diario")
    @ManyToOne
    @NotNull
    private DiarioDeObra diarioDeObra;
}
