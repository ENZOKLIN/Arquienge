package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Presenca {

    @Id
    @Column(name = "id_presenca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "presenca", updatable = false)
    private boolean presenca;

    @JoinColumn(name = "id_funcionario")
    @ManyToOne
    private Funcionario funcionario;

    @JoinColumn(name = "id_diario")
    @ManyToOne
    private DiarioDeObra diarioDeObra;
}
