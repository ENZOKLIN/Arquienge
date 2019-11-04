package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Etapa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer codEtapa;

    @NotEmpty(message = "Nome da Etapa não pode estar vazio!")
    String nomeEtapa;

    @NotEmpty(message = "Descrição da etapa é obrigatória!")
    String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = false, name = "id_obra")
    private Obra obra;

    //0 = Não Iniciado
    //1 = Em Andamento
    //2 = Concluido

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "integer default 0")
    private Status status;
    public enum Status{
        NaoIniciado,
        EmProgresso,
        Concluido
    }
}
