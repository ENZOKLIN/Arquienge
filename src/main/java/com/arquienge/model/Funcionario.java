package com.arquienge.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

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

public class Funcionario {

    // ATRIBUTOS DA CLASSE FUNCIONÁRIO:

    // DETERMINANDO ATRAVÉS DE UMA ANOTAÇÃO QUE ESTE ATRIBUTO É UMA PRIMARY KEY(ID):
    // DETERMINANDO QUE ESTA KEY(ID) É UM VALOR GERADO DO TIPO IDENTITY(FUNCIONÁRIO 1, FUNCIONÁRIO 2, FUNCIONÁRIO 3):
    // DETERMINANDO O NOME DA COLUNA E QUE ELA É ÚNICA:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    // USANDO A ANOTAÇÃO SIZE PARA DETERMINAR E VALIDAR O TAMANHO DO ATRIBUTO NO CONSTRUTOR(MIN 12, MAX 14):
    @Size(max = 14, min = 12)
    // DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = ("[0-9]{2} [0-9]{5}-[0-9]{4}"))
    // USANDO A ANOTAÇÃO NOT NULL PARA VALIDAR O ATRIBUTO NO CONSTRUTOR(SE ELE ESTÁ NULO OU NÃO):
    @NotNull(message = "O telefone não estar vazio!")
    private String telefone;

    // USANDO A ANOTAÇÃO NOT EMPTY PARA VALIDAR O ATRIBUTO NO CONSTRUTOR(SE ESTÁ OU NÃO VAZIO):
    @NotEmpty(message = "Nome não pode estar vazio!")
    private String nome;

    // USANDO A ANOTAÇÃO NOT EMPTY PARA VALIDAR O ATRIBUTO NO CONSTRUTOR (SE ESTÁ OU NÃO VAZIO):
    @NotEmpty(message = "CPF não pode estar vazio!")
    // DETERMINANDO QUE A COLUNA É ÚNICA:
    @Column(unique = true)
    // USANDO A ANOTAÇÃO SIZE PARA DETERMINAR E VALIDAR O TAMANHO DO ATRIBUTO NO CONSTRUTOR(MIN 14, MAX 14):
    @Size(max = 14, min = 14, message = "CPF incorreto!")
    private String cpf;

    // USANDO A ANOTAÇÃO NOT EMPTY PARA VALIDAR O ATRIBUTO NO CONSTRUTOR (SE ESTÁ OU NÃO VAZIO):
    @NotEmpty(message = "Sobrenome não pode estar vazio!")
    private String sobrenome;

    // USANDO A ANOTAÇÃO DATETIMEFORMAT PARA FORMATAR A DATA QUE SERÁ RECEBIDA NO ATRIBUTO:
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    // USANDO A ANOTAÇÃO NOT NULL PARA VALIDAR O ATRIBUTO NO CONSTRUTOR(SE ELE ESTÁ NULO OU NÃO):
    @NotNull(message = "Data de nascimento não pode estar vazia!")
    private java.util.Date nascimento;

//    @Lob
//    @Column(name = "foto", unique = true)
//    private byte[] foto;

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

    @OneToMany(mappedBy = "funcionario")
    private List<Presenca> presencas;

}
