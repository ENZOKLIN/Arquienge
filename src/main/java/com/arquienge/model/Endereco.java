package com.arquienge.model;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

//CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER E TO STRING)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA ENDEREÇO:
public class Endereco {

    // ATRIBUTOS DA CLASSE ENDEREÇO:

    //DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID)
    @Id

    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(ENDEREÇO 1, ENDEREÇO 2, ENDEREÇO 3):
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //DETERMINANDO O TAMANHO DA COLUNA(LENGTH):
    @Column(length = 9)

    //DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    private String cep;

    //ATRIBUTOS COMUNS:
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;

    //INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENDEREÇO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "endereco")
    private Funcionario funcionario;

    //INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE CARTEIRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "endereco")
    private Engenheiro engenheiro;

    //INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENDEREÇO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "endereco")
    private Proprietario proprietario;

    //INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENDEREÇO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToOne(mappedBy = "endereco")
    private Obra obra;


}
