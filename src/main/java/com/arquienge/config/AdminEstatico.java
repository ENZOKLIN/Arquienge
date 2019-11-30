package com.arquienge.config;

import com.arquienge.model.Administrador;
import com.arquienge.model.Engenheiro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
@AllArgsConstructor
@Getter
public abstract class AdminEstatico {
   @Getter public static Integer id;
   @Getter public static String usuario;
   @Getter public static String nome;
   @Getter public static String sobrenome;
   @Getter public static String senha;

    public static void setAdminLogado(Administrador admin) {
        id = admin.getId();
        nome = admin.getNome();
        sobrenome = admin.getSobrenome();
        usuario = admin.getUsuario();
        senha = admin.getSenha();
    }
    public static void desconectar() {
        id = 0;
        nome = null;
        sobrenome = null;
        usuario = null;
        senha = null;
    }
}
