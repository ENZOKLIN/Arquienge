package com.arquienge.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

// ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

// DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

// CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA FUNCIONÁRIO QUE HERDA DE USUÁRIO:

public class Funcionario extends Usuario {

    // ATRIBUTOS DA CLASSE FUNCIONÁRIO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    // DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(FUNCIONÁRIO 1, FUNCIONÁRIO 2, FUNCIONÁRIO 3):
    // DETERMINANDO O NOME DA COLUNA E QUE ELA É ÚNICA:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;
    private double salario;

    // USANDO A ANOTAÇÃO DATETIMEFORMAT PARA FORMATAR A DATA QUE SERÁ RECEBIDA NO ATRIBUTO:
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    private Date dt_admissao;
    private String cargo;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE FUNCIONÁRIO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM FUNCIONÁRIO PARA UMA ENDEREÇO):
    // USANDO O JOIN E PASSANDO COMO NOME DA COLUNA "ID_ENDEREÇO":
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE FUNCIONÁRIO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM FUNCIONÁRIO PARA UMA CARTEIRA):
    // USANDO O JOIN E PASSANDO COMO NOME DA COLUNA "ID_CARTEIRA":
    @OneToOne
    @JoinColumn(unique = true, name = "id_carteira")
    private CarteiradeTrabalho carteira;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE FUNCIONÁRIO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(MUITOS FUNCIONÁRIOS PARA UMA OBRA):
    // USANDO O JOIN E PASSANDO COMO NOME DA COLUNA "ID_OBRA":
    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;

}
