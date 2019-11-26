package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/// CHAMANDO ATRAVÉS DE ANOTAÇÕES A CRIAÇÃO DOS METÓDOS FUNDAMENTAIS(GETTER,SETTER)
// PARA ESTA CLASSE E OS CONTRUTORES(VAZIO E COM TODOS ATRIBUTOS)
// DETERMINANDO QUE ESTA CLASSE É UMA SUPER-CLASSE:
@MappedSuperclass
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

// CRIANDO A SUPER-CLASSE USUÁRIO:

public class Usuario {

    // USANDO A ANOTAÇÃO DE VALIDAÇÃO PRONTA PARA EMAILS, PARA EVITAR QUE O ATRIBUTO DO CONSTRUTOR NÃO SEJA UM E-MAIL:
    @Email(message = "Email não cumpre os requisitos")
    // DETERMINANDO QUE A COLUNA NO BD É ÚNICA:
    @Column(unique = true)
    String email;

    // DETERMINANDO TAMANHO DA COLUNA NO BD (16) E QUE ELA NÃO PODE ESTAR NULA:
    @Column(length = 16, nullable = false)
    // USANDO A ANOTAÇÃO SIZE PARA DETERMINAR E VALIDAR O TAMANHO DO ATRIBUTO NO CONSTRUTOR(MIN 8, MAX 16):
    @Size(max = 16, min = 8, message = "Senha não cumpre os requisitos!")
    // USANDO A ANOTAÇÃO NOT EMPTY PARA VALIDAR O ATRIBUTO(SE ESTÁ OU NÃO VAZIO):
    @NotEmpty(message = "Senha não pode estar vazia!")
    String senha;

    // USANDO A ANOTAÇÃO SIZE PARA DETERMINAR E VALIDAR O TAMANHO DO ATRIBUTO NO CONSTRUTOR(MIN 12, MAX 14):
    @Size(max = 14, min = 12)
    // DETERMINANDO UMA FORMATAÇÃO DE DADOS QUE QUERO RECEBER NESTE ATRIBUTO ATRAVÉS DO REGEX:
    @Pattern(regexp = ("([0-9]{2}) [0-9]{5}-[0-9]{4}"))
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
    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}")
    private String cpf;

    @NotEmpty(message = "RG não pode estar vazio!")
    @Column(unique = true, name = "rg")
    @Size(max = 11, min = 11, message = "RG inválido!")
    @Pattern(regexp = "[0-9]{3}.[0-9]{3}.[0-9]{3}")
    private String rg;

    // USANDO A ANOTAÇÃO NOT EMPTY PARA VALIDAR O ATRIBUTO NO CONSTRUTOR (SE ESTÁ OU NÃO VAZIO):
    @NotEmpty(message = "Sobrenome não pode estar vazio!")
    private String sobrenome;

    // USANDO A ANOTAÇÃO DATETIMEFORMAT PARA FORMATAR A DATA QUE SERÁ RECEBIDA NO ATRIBUTO:
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    // USANDO A ANOTAÇÃO NOT NULL PARA VALIDAR O ATRIBUTO NO CONSTRUTOR(SE ELE ESTÁ NULO OU NÃO):
    @NotNull(message = "Data de nascimento não pode estar vazia!")
    private Date nascimento;

}
