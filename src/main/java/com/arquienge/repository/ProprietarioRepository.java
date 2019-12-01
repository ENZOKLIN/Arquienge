package com.arquienge.repository;

import com.arquienge.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer> {

    @Query(value = "SELECT PROPRIETARIO.* WHERE PROPRIETARIO.NOME = ?1 AND PROPRIETARIO.SOBRENOME = ?2", nativeQuery = true)
    Proprietario findProprietarioByNomeAndSobrenome(String nome, String sobrenome);

    @Query(value = "SELECT PROPRIETARIO.* WHERE PROPRIETARIO.CPF = ?1", nativeQuery = true)
    Proprietario findProprietarioByCpf(String cpf);

    @Query(value = "SELECT * FROM PROPRIETARIO WHERE PROPRIETARIO.EMAIL = ?1 AND PROPRIETARIO.SENHA = ?2", nativeQuery = true)
    Proprietario findProprietarioByEmailAndSenha(String email, String senha);

    @Query(value = "SELECT PROPRIETARIO.* FROM PROPRIETARIO,ENDERECO WHERE PROPRIETARIO.ENDERECO_ID = ENDERECO.ID AND ENDERECO.CIDADE = ?1", nativeQuery = true)
    List<Proprietario> findProprietariosByCidade(String cidade);

    @Query(value = "SELECT PROPRIETARIO.* FROM PROPRIETARIO WHERE PROPRIETARIO.TELEFONE = ?1", nativeQuery = true)
    Proprietario findProprietarioByTelefone(String telefone);

    @Query(value = "SELECT PROPRIETARIO.* FROM PROPRIETARIO, ENGENHEIRO WHERE ENGENHEIRO.ID = ?1 AND PROPRIETARIO.ID = ENGENHEIRO.ID_PROPRIETARIO", nativeQuery = true)
    Proprietario findProprietarioByEngenheiroId(Integer id);

    @Query(value = "SELECT * FROM PROPRIETARIO WHERE PROPRIETARIO.ID_PROPRIETARIO = ?1", nativeQuery = true)
    Proprietario findProprietarioById(Integer id);
}
