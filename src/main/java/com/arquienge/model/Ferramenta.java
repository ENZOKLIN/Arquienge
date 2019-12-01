package com.arquienge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


// ANOTAÇÕES DA CLASSE(LOMBOK,ETC)

// DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE ESTA CLASSE É UMA ENTIDADE(BD):
@Entity

// CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS):
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

// CRIANDO A CLASSE PÚBLICA FERRAMENTA:

public class Ferramenta {

    // ATRIBUTOS DA CLASSE CARTEIRA DE TRABALHO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    //DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(ETAPA 1, ETAPA 2, ETAPA 3):
    // DETERMINANDO ATRAVÉS DE ANOTAÇÃO QUE O NOME DA COLUNA NO BD DESTE ATRIBUTO É "ID":
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_ferramenta;

    // DETERMINANDO UMA MENSAGEM DE VALIDAÇÃO CASO ESTE ATRIBUTO SEJA CONSTRUIDO COMO NULO:
    // DETERMINANDO QUE ESTA COLUNA NÃO PODE SER NULA NO BD:
    // DETERMINANDO O TAMANHO MÁXIMO E  MÍNIMO DESTE ATRIBUTO( MIN 5 E MAX 60),
    // CASO NÃO CORRESPONDA A ESSE TAMANHO UMA MENSAGEM É RETORNADA:
    @Column(nullable = false)
    @Size(min = 2, max = 60, message = "Ferramenta Inválida!")
    @NotEmpty(message = "Nome da Ferramenta não pode estar vazio!")
    private String ferramenta;

    // DETERMINANDO QUE ESTA COLUNA NÃO PODE SER NULA NO BD:
    @Column(nullable = false)
    private int quantidade;

    // INSTANCIANDO UM OBJETO COMO ATRIBUTO NA CLASSE FERRAMENTA, PARA DETERMINAR A RELAÇÃO TANTO NA CLASSE,
    // QUANTO NO BANCO DE DADOS(MUITAS FERRAMENTAS PARA UMA OBRA):
    @ManyToOne
    //CHAMANDO O JOIN E DETERMINANDO O NOME DA COLUNA RELACIONADA, "ID_OBRA":
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @OneToMany(mappedBy = "ferramenta")
    private List<FerramentasUsadas> ferramentasUsadas;

    // METÓDO TOSTRING PRÓPRIO DA CLASSE(OPTEI POR NÃO USAR O PADRÃO DO LOMBOK):

    @Override
    public String toString()
    {
        return "Ferramenta =[Código da Ferramenta:"+this.getCod_ferramenta()+", Nome da Ferramenta:"+this.getFerramenta()+", Quantidade da Ferramenta:"+this.getQuantidade()+"]";
    }
}
