package com.arquienge.config;

import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.model.Obra;
import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;
import java.util.List;

@Getter
@NoRepositoryBean
public class Logado {
    public  Integer id;
    public  String email;
    public  String telefone;
    public  String nome;
    public  String sobrenome;
    public  String cpf;
    public  String rg;
    public  Date nascimento;
    public  Endereco endereco;
    public  List<Obra> obras;
    public  List<Engenheiro> engenheiros;

    public Logado(boolean isProprietario) {
        if(isProprietario) {
        setProprietario();
        }
        else {
            setEngenheiro();
        }
    }

    public boolean isLogged() {
        return this.id != 0;
    }

    private void setEngenheiro() {
        this.id = LogadoEstatico.getId();
        this.nome = LogadoEstatico.getNome();
        this.sobrenome = LogadoEstatico.getSobrenome();
        this.telefone = LogadoEstatico.getTelefone();
        this.cpf = LogadoEstatico.getCpf();
        this.rg = LogadoEstatico.getRg();
        this.email = LogadoEstatico.getEmail();
        this.nascimento = LogadoEstatico.getNascimento();
        this.endereco = LogadoEstatico.getEndereco();
        this.obras = LogadoEstatico.getObras();
    }
    private void setProprietario() {
        this.id = LogadoEstatico.getId();
        this.nome = LogadoEstatico.getNome();
        this.sobrenome = LogadoEstatico.getSobrenome();
        this.telefone = LogadoEstatico.getTelefone();
        this.cpf = LogadoEstatico.getCpf();
        this.rg = LogadoEstatico.getRg();
        this.email = LogadoEstatico.getEmail();
        this.nascimento = LogadoEstatico.getNascimento();
        this.endereco = LogadoEstatico.getEndereco();
        this.engenheiros = LogadoEstatico.getEngenheiros();
    }
}

