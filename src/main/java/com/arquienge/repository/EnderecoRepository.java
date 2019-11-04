package com.arquienge.repository;

import com.arquienge.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query(value = "INSERT INTO ENDERECO VALUES(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void insertEndereco(Integer id,String bairro,String cep, String cidade, Integer numero, String rua);

    Optional<Endereco> findById(Integer id);

    List<Endereco> findByCep(String cep);

    @Query(value = "SELECT ENDERECO.* FROM ENDERECO,OBRA WHERE OBRA.ID = ?1 AND OBRA.ID_ENDERECO = ENDERECO.ID", nativeQuery = true)
    Endereco findEnderecoByObraId(Integer id);

    @Query(value = "SELECT ENDERECO.* FROM ENDERECO,FUNCIONARIO WHERE FUNCIONARIO.ID_ENDERECO = ENDERECO.ID AND FUNCIONARIO.ID = ?1", nativeQuery = true)
    Endereco findEnderecoByFuncionarioId(Integer id);

    @Query(value = "SELECT * FROM ENDERECO WHERE ENDERECO.BAIRRO = ?1", nativeQuery = true)
    List<Endereco> findEnderecosByBairro(String bairro);

    @Query(value = "SELECT * FROM ENDERECO WHERE ENDRECO.CIDADE = ?1", nativeQuery = true)
    List<Endereco> findEnderecosByCidade(String cidade);

    @Query(value = "SELECT ENDERECO.* FROM ENDERECO WHERE ENDERECO.RUA = ?1", nativeQuery = true)
    List<Endereco> findEnderecosByRua(String rua);

}
