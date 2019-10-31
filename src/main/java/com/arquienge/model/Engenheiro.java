package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Engenheiro extends Usuario{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_engenheiro;

    @OneToOne
    @JoinColumn(unique = true, name = "endereco_id")
    private Endereco endereco;

}
