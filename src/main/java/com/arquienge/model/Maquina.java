package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


//ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

//CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA MÁQUINA:

public class Maquina {

    // ATRIBUTOS DA CLASSE MÁQUINA:

    //DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID)
    @Id
    //DETERMINANDO O NOME DA COLUNA NO BD "ID":
    @Column(name = "id")
    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(MÁQUINA 1, MÁQUINA 2, MÁQUINA 3):
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_maquina;

    private String modelo;
    private int quantidade;
    private String tipoMaquina;

    //DETERMINANDO O TAMANHO DA COLUNA NO BD "8" E QUE ELA É ÚNICA:
    @Column(length = 8)

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[A-Za-z]{3}-[0-9]{4}", message = "Placa Inválida")
    private String placa;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE MÁQUINA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(MUITAS MÁQUINAS PARA UMA OBRA):
    @ManyToOne
    // USANDO O JOIN E DETERMINANDO O NOME DA COLUNA NO BD "ID_OBRA"
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @OneToMany(mappedBy = "maquina")
    private List<MaquinasUsadas> maquinasUsadas;

    //CRIANDO METÓDO TO STRING PRÓPRIO DA CLASSE(OPTANDO POR NÃO USAR O LOMBOK):
    @Override
    public String toString() {
        return "Máquina =[ Código da Máquina:" + this.getCod_maquina() + ",Tipo da Máquina:" + this.getTipoMaquina() + ", Modelo da Máquina:" + this.getModelo() + ", Placa da Máquina:" + this.getPlaca() + ", Quantidade da Máquina:" + this.getQuantidade() + "]";
    }
}
