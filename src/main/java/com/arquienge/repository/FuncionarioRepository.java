package com.arquienge.repository;

import com.arquienge.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query(value = "SELECT FUNCIONARIO.* FROM FUNCIONARIO,OBRA WHERE FUNCIONARIO.ID_OBRA = ?1 AND OBRA.ID = ?1", nativeQuery = true)
    List<Funcionario> findFuncionariosByObraId(Integer id, String nomeobra);

    @Query(value = "SELECT * FROM FUNCIONARIO WHERE FUNCIONARIO.NOME= ?1 AND FUNCIONARIO.SOBRENOME = ?2", nativeQuery = true)
    Funcionario findFuncionarioByName(String nome, String sobrenome);

    @Query(value = "SELECT * FROM FUNCIONARIO WHERE FUNCIONARIO.CPF= ?1", nativeQuery = true)
    Funcionario findFuncionarioByCpf(String cpf);

    @Query(value= "SELECT * FROM FUNCIONARIO WHERE FUNCIONARIO.EMAIL= ?1", nativeQuery = true)
    Funcionario findFuncionarioByEmail(String email);

    @Query(value = "SELECT FUNCIONARIO.* WHERE FUNCIONARIO.CARGO = ?1", nativeQuery = true)
    List<Funcionario> findFuncionariosByCargo(String cargo);

    @Query(value = "SELECT FUNCIONARIO.* WHERE CARTEIRADE_TRABALHO.CARTEIRA = ?1 AND FUNCIONARIO.ID_CARTEIRA = CARTEIRADE_TRABALHO.ID", nativeQuery = true)
    Funcionario findFuncionarioByCarteira(String numerodacarteira);

    @Query(value = "SELECT FUNCIONARIO.* WHERE FUNCIONARIO.ID_ENDERECO = ENDERECO.ID AND ENDERECO.CEP = ?", nativeQuery = true)
    List<Funcionario> findFuncionariosByCep(String cep);

}