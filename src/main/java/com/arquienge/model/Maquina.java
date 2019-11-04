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
public class Maquina {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_maquina;

    private String modelo;
    private int quantidade;
    private String tipoMaquina;

    @Column(length = 8, unique = true)
    @Pattern(regexp = "[A-Za-z]{3}-[0-9]{4}", message = "Placa Inválida")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;


    @Override
    public String toString()
    {
        return "Máquina =[ Código da Máquina:"+this.getCod_maquina()+",Tipo da Máquina:"+this.getTipoMaquina()+", Modelo da Máquina:"+this.getModelo()+", Placa da Máquina:"+this.getPlaca()+", Quantidade da Máquina:"+this.getQuantidade()+"]";
    }
}
