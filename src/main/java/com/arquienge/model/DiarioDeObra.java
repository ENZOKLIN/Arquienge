package com.arquienge.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString

public class DiarioDeObra {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "data_criacao")
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data de criação não pode estar vazia!")
    private java.time.LocalDate dataCriacao = LocalDate.now();

    @Column(name = "hora_criacao")
    @NotNull(message = "Hora de criação não pode estar vazia!")
    private java.time.LocalTime horaCriacao = LocalTime.now();

    @Column(name = "clima")
    @NotEmpty(message = "O campo Clima não pode estar vazio!")
    private String clima;

    @Column(name = "descricao")
    @NotEmpty(message = "O campo de descrições do Diário não pode estar vazio!")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_obra")
    private Obra obra;

    @OneToMany(mappedBy = "diarioDeObra")
    private List<Presenca> presencas;

    @OneToMany(mappedBy = "diarioDeObra")
    private List<MaquinasUsadas> maquinasUsadas;

    @OneToMany(mappedBy = "diarioDeObra")
    private List<FerramentasUsadas> ferramentasUsadas;

}
