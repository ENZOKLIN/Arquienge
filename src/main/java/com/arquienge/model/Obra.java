package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Obra {

    @Id
    @GeneratedValue
    @Column(unique = true, name = "ID")
    private Integer id_obra;

    @Column(unique = true, name = "Nome_Obra")
    private String nome_obra;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data de inicio da obra não pode estar vazia!")
    private Date dt_inicio;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data de entrega da obra não pode estar vazia!")
    private Date dt_entrega;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Etapa> etapas;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_engenheiro")
    private Engenheiro engenheiro;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Ferramenta> ferramentas;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Maquina> maquinas;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

}
