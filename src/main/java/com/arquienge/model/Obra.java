package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

//ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

//CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter @Setter

@NoArgsConstructor @AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA OBRA:

public class Obra {

    // ATRIBUTOS DA CLASSE MÁQUINA:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    @Id
    // DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(OBRA 1, OBRA 2, OBRA 3):
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DETERMINANDO O NOME DA COLUNA NO BD "ID" E QUE A MESMA É ÚNICA(NÃO PODE REPETIR):
    @Column(unique = true, name = "ID")
    private Integer id_obra;

    // DETERMINANDO O NOME DA COLUNA NO BD "NOME-OBRA" E QUE É ÚNICA:
    @Column(unique = true, name = "Nome_Obra")
    private String nome_obra;

    // USANDO A ANOTAÇÃO DATETIMEFORMAT PARA FORMATAR A DATA QUE SERÁ RECEBIDA NO ATRIBUTO:
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotNull(message = "Data de inicio da obra não pode estar vazia!")
    private Date dt_inicio;

    // USANDO A ANOTAÇÃO DATETIMEFORMAT PARA FORMATAR A DATA QUE SERÁ RECEBIDA NO ATRIBUTO:
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotNull(message = "Data de entrega da obra não pode estar vazia!")
    private Date dt_entrega;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA MUITAS ETAPAS):
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Etapa> etapas;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA UM ENDEREÇO):
    // USANDO O JOIN E PASSANDO COMO NOME DA COLUNA "ID_ENDERECO":
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<DiarioDeObra> diariosDeObra;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA UM ENGENHEIRO):
    // USANDO O JOIN E PASSANDO COMO NOME DA COLUNA "ID_ENGENHEIRO":
    @OneToOne
    @JoinColumn(name = "id_engenheiro")
    private Engenheiro engenheiro;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA MUITAS FERRAMENTAS):
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Ferramenta> ferramentas;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA MUITAS MÁQUINAS):
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Maquina> maquinas;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE OBRA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(UMA OBRA PARA MUITOS FUNCIONÁRIOS):
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

}
