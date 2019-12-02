package com.arquienge.repository;

import com.arquienge.model.Engenheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.stream.events.EndElement;
import java.util.List;

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

    @Query(value = "UPDATE ENGENHEIRO SET ENGENHEIRO.EMAIL = ?1, ENGENHEIRO.SENHA = ?2 WHERE ENGENHEIRO.ID = ?3", nativeQuery = true)
    Engenheiro updateEngenheiroById(String email,String senha,Integer id);

    @Query(value = "SELECT ENGENHEIRO.* FROM ENGENHEIRO, PROPRIETARIO WHERE ENGENHEIRO.ID_PROPRIETARIO = ?1 AND PROPRIETARIO.ID = ?1 AND ENGENHEIRO.ID_PROPRIETARIO = PROPRIETARIO.ID", nativeQuery = true)
    List<Engenheiro> findEngenheirosByProprietarioId(Integer id);

    Engenheiro findEngenheiroByRg(String rg);

    @Query(value = "SELECT * FROM ENGENHEIRO WHERE ENGENHEIRO.ID = ?1", nativeQuery = true)
    Engenheiro findEngenheiroById(Integer id);

    Engenheiro findEngenheiroByEmail(String email);

}
