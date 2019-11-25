package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

//DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

// CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

//CRIANDO A CLASSE PÚBLICA ETAPA:
public class Etapa {

    // ATRIBUTOS DA CLASSE CARTEIRA DE TRABALHO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(ETAPA 1, ETAPA 2, ETAPA 3):
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer codEtapa;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "Nome da Etapa não pode estar vazio!")
    String nomeEtapa;

    //DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    @NotEmpty(message = "Descrição da etapa é obrigatória!")
    String descricao;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE ETAPA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(MUITAS ETAPAS PARA UMA OBRA):
    @ManyToOne(cascade = CascadeType.ALL)

    //CHAMANDO O JOIN ATRAVÉS DE ANOTAÇÃO, DETERMINANDO O NOME DA COLUNA "ID_OBRA":
    @JoinColumn(unique = false, name = "id_obra")
    private Obra obra;

    //0 = Não Iniciado
    //1 = Em Andamento
    //2 = Concluido

    //CRIANDO UM OBJETO STATUS DO TIPO ENUM PARA SABER O STATUS DA ETAPA(NÃO INICIADA,EM ANDAMENTO E CONCLUÍDA):
    @Enumerated(EnumType.ORDINAL)
    //DETERMINANDO ATRAVÉS DO COLUMN UM VALOR DEFAULT(0 - NÃO INICIADO)
    @Column(columnDefinition = "integer default 0")
    private Status status;

    //CRIANDO O ENUM STATUS COM SEUS RESPECTIVOS STATUS NAS POSIÇÕES CORRESPONDENTES(0,1,2):
    public enum Status{
        NaoIniciado,
        EmProgresso,
        Concluido
    }

}
