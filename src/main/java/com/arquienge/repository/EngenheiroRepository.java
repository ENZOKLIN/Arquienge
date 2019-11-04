package com.arquienge.repository;

import com.arquienge.model.Engenheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.stream.events.EndElement;

public interface EngenheiroRepository extends JpaRepository<Engenheiro, Integer> {

    @Query(value = "SELECT ENGENHEIRO.* from ENGENHEIRO,ENDERECO,OBRA" +
            " WHERE OBRA.ID_ENGENHEIRO = ?1 and OBRA.ID_ENDERECO = ENDERECO.ID;", nativeQuery = true)
    Engenheiro findEngenheiroByObraId(Integer Id);

    @Query(value = "SELECT ENGENHEIRO.* from ENGENHEIRO WHERE ENGENHEIRO.EMAIL = ?1 AND ENGENHEIRO.SENHA = ?2", nativeQuery = true)
    Engenheiro findEngenheiroByEmailAndSenha(String email, String senha);

    @Query(value = "SELECT ENGENHEIRO.* from ENGENHEIRO WHERE ENGENHEIRO.CPF = ?1", nativeQuery = true)
    Engenheiro findEngenheiroByCpf(String cpf);

    @Query(value = "SELECT ENGENHEIRO.* from ENGENHEIRO WHERE ENGENHEIRO.TELEFONE = ?1", nativeQuery = true)
    Engenheiro findEngenheiroByTelefone(String telefone);

    @Query(value = "SELECT ENGENHEIRO.* FROM ENGENHEIRO WHERE ENGENHEIRO.NOME = ?1 AND ENGENHEIRO.SOBRENOME = ?2", nativeQuery = true)
    Engenheiro findEngenheiroByNomeAndSobrenome(String nome, String sobrenome);

}
