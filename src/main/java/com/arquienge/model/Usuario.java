package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Email(message = "Email não cumpre os requisitos")
    String email;

    @Column(length = 16, nullable = false)
    @Size(max = 16, min = 8, message = "Senha não cumpre os requisitos!")
    @NotEmpty(message = "Senha não pode estar vazia!")
    String senha;

    @Size(max = 14, min = 14)
    @Pattern(regexp = ("[0-9]{2} [0-9]{5}-[0-9]{4}"))
    @NotNull(message = "O telefone não estar vazio!")
    private String telefone;

    @NotEmpty(message = "Nome não pode estar vazio!")
    private String nome;

    @NotEmpty(message = "CPF não pode estar vazio!")
    @Size(max = 14, min = 14, message = "CPF incorreto!")
    private String cpf;

    @NotEmpty(message = "Sobrenome não pode estar vazio!")
    private String sobrenome;

    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data de nascimento não pode estar vazia!")
    private Date nascimento;
}
