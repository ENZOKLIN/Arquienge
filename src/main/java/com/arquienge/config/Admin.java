package com.arquienge.config;

import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;

@Getter
@NoRepositoryBean
public class Admin {
    public Integer id;
    public String nome;
    public String sobrenome;
    public String usuario;
    public String senha;

    public Admin(){
        setAdmin();
    }

    private void setAdmin() {
        this.id = AdminEstatico.getId();
        this.nome = AdminEstatico.getNome();
        this.sobrenome = AdminEstatico.getSobrenome();
        this.usuario = AdminEstatico.getUsuario();
        this.senha = AdminEstatico.getSenha();
    }
}
