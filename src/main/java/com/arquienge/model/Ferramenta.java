package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Ferramenta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_ferramenta;

    @Column(nullable = false)
    @Size(min = 5, max = 60, message = "Ferramenta Inválida!")
    @NotEmpty(message = "Nome da Ferramenta não pode estar vazio!")
    private String ferramenta;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(unique = true, name = "id_obra")
    private Obra obra;

    @Override
    public String toString()
    {
        return "Ferramenta =[Código da Ferramenta:"+this.getCod_ferramenta()+", Nome da Ferramenta:"+this.getFerramenta()+", Quantidade da Ferramenta:"+this.getQuantidade()+"]";
    }
}
