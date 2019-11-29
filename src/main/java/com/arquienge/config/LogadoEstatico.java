package com.arquienge.config;

import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.model.Obra;
import com.arquienge.model.Proprietario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Date;
import java.util.List;

@NoRepositoryBean
@AllArgsConstructor
public abstract class LogadoEstatico {

    @Getter public static Integer id;
    @Getter public static String email;
    @Getter public static String telefone;
    @Getter public static String nome;
    @Getter public static String sobrenome;
    @Getter public static String cpf;
    @Getter public static String rg;
    @Getter public static Date nascimento;
    @Getter public static Endereco endereco;
    @Getter public static Obra obra;
    @Getter public static List<Engenheiro> engenheiros;

    public static void setEngenheiroLogado(Engenheiro engenheiro) {
        id = engenheiro.getId_engenheiro();
        nome = engenheiro.getNome();
        email = engenheiro.getEmail();
        sobrenome = engenheiro.getSobrenome();
        telefone = engenheiro.getTelefone();
        cpf = engenheiro.getCpf();
        rg = engenheiro.getRg();
        nascimento = engenheiro.getNascimento();
        endereco = engenheiro.getEndereco();
        obra = engenheiro.getObra();
    }

    public static void setProprietarioLogado(Proprietario proprietario) {
        id = proprietario.getId_proprietario();
        nome = proprietario.getNome();
        email = proprietario.getEmail();
        sobrenome = proprietario.getSobrenome();
        telefone = proprietario.getTelefone();
        cpf = proprietario.getCpf();
        rg = proprietario.getRg();
        nascimento = proprietario.getNascimento();
        endereco = proprietario.getEndereco();
        engenheiros = proprietario.getEngenheiros();
    }
    public static void desconectar() {
        id = 0;
        nome = null;
        email = null;
        sobrenome = null;
        telefone = null;
        cpf = null;
        rg = null;
        nascimento = null;
        endereco = null;
        obra = null;
        engenheiros = null;
    }

}
