package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

//CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER E TO STRING)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA ENGENHEIRO:
public class Engenheiro extends Usuario {

    // ATRIBUTOS DA CLASSE CARTEIRA DE TRABALHO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    @Id

    //DETERMINANDO QUE O NOME DA COLUNA DESSE ATRIBUTO NO BD É "ID":
    @Column(name = "id")

    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(ENGENHEIRO 1, ENGENHEIRO 2, ENGENHEIRO 3):
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_engenheiro;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENGENHEIRO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne
    //CHAMANDO O JOIN ATRAVÉS DE ANOTAÇÃO, PASSANDO COMO PARÂMETRO O NOME DA COLUNA "ENDERECO_ID":
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENGENHEIRO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "engenheiro")
    private Obra obra;

    @JoinColumn(name = "id_proprietario")
    @ManyToOne
    private Proprietario proprietario;
}
