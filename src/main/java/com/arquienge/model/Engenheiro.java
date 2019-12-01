package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    //CHAMANDO O JOIN ATRAVÉS DE ANOTAÇÃO, PASSANDO COMO PARÂMETRO O NOME DA COLUNA "ENDERECO_ID":
    @OneToOne
    @JoinColumn(unique = true, name = "endereco_id")
    private Endereco endereco;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ENGENHEIRO, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UM PARA UM):
    @OneToMany(mappedBy = "engenheiro", cascade = CascadeType.ALL)
    private List<Obra> obras;

    @JoinColumn(name = "proprietario_id")
    @ManyToOne
    private Proprietario proprietario;

    @OneToMany(mappedBy = "engenheiro", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;
}
