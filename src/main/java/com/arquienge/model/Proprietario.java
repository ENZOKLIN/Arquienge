package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

// DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

// CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA PROPRIETÁRIO QUE HERDA DE USUÁRIO:

public class Proprietario extends Usuario {

    // ATRIBUTOS DA CLASSE FUNCIONÁRIO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    // DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(PROPRIETÁRIO 1, PROPRIETÁRIO 2, PROPRIETÁRIO 3):
    // DETERMINANDO O NOME DA COLUNA E QUE ELA É ÚNICA:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id_proprietario;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE PROPRIETÁRIO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PROPRIETÁRIO PARA UM ENDEREÇO):
    @OneToOne
    // USANDO O JOIN E DETERMINANDO O NOME DA COLUNA COMO "ENDERECO_ID":
    @JoinColumn(unique = true, name = "endereco_id")
    private Endereco endereco;

}
