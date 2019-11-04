package com.arquienge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Funcionario extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double salario;
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    private Date dt_admissao;
    private String cargo;

    @OneToOne
    @JoinColumn(unique = true, name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(unique = true, name = "id_carteira")
    private CarteiradeTrabalho carteira;

    @ManyToOne
    @JoinColumn(unique = true, name = "id_obra")
    private Obra obra;

}
