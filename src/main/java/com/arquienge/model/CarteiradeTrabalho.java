package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

//ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

//CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER,TO STRING)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

//CRIANDO A CLASSE PÚBLICA CARTEIRA DE TRABALHO:

public class CarteiradeTrabalho {

    // ATRIBUTOS DA CLASSE CARTEIRA DE TRABALHO:

    //DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID)
    @Id

    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(CARTEIRA 1, CARTEIRA 2, CARTEIRA 3):
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //DETERMINANDO QUE O NOME DA COLUNA É IGUAL A "ID" E QUE É ÚNICA(NÃO PODE REPETIR):
    @Column(unique = true, name = "ID")
    private Integer cod_carteira;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "PIS não pode estar vazio!")

    //DETERMINANDO O TAMANHO DA COLUNA, O NOME DA COLUNA, SE ELA É ÚNICA E SE ELA PODE SER NULA:
    @Column(length = 14, nullable = false, unique = true, name = "PIS")

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[0-9]{3}.[0-9]{5}.[0-9]{2}-[0-9]{1}", message = "O número do PIS não corresponde ao padrao esperado!")
    private String pis;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "O número de carteira não pode estar vazio!")

    //DETERMINANDO O TAMANHO DA COLUNA, O NOME DA COLUNA, E SE ELA É ÚNICA:
    @Column(length = 7, name = "Carteira", unique = true)

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[0-9]{7}" , message = "O número de carteira não cumpre o padrao!")
    private String numero_carteira;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "O número de série não pode estar vazio!")

    //DETERMINANDO O TAMANHO DA COLUNA, O NOME DA COLUNA E SE ELA É ÚNICA:
    @Column(length = 5, name = "Serie", unique = true)

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[0-9]{3}-[0-9]{1}", message = "O número de série não cumpre o padrão")
    private String serie;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "O UF da carteira não pode estar vazio!")

    //DETERMINANDO O TAMANHO DA COLUNA E O NOME DA COLUNA:
    @Column(length = 2, name = "UF")

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[A-Z]{2}", message = "O UF é a abreviação do seu Estado, corrija!")
    private String UF;

    //INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE CARTEIRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "carteira")
    private Funcionario funcionario;

}
