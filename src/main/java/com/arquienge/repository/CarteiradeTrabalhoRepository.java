package com.arquienge.repository;

import com.arquienge.model.CarteiradeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteiradeTrabalhoRepository extends JpaRepository<CarteiradeTrabalho, Integer> {

    @Query(value = "SELECT * FROM CARTEIRADE_TRABALHO WHERE CARTEIRADE_TRABALHO.CARTEIRA = ?1", nativeQuery = true)
    CarteiradeTrabalho findCarteiradeTrabalhoByNumerodaCarteira(String numerocarteira);

    @Query(value = "SELECT * FROM CARTEIRADE_TRABALHO WHERE CARTEIRADE_TRABALHO.PIS = ?1", nativeQuery = true)
    CarteiradeTrabalho findCarteiradeTrabalhoByPis(String pis);

    @Query(value = "SELECT * FROM CARTEIRADE_TRABALHO WHERE CARTEIRADE_TRABALHO.SERIE = ?1", nativeQuery = true)
    List<CarteiradeTrabalho> findCarteirasdeTrabalhoBySerie(String serie);

    @Query(value = "SELECT * FROM CARTEIRADE_TRABALHO WHERE CARTEIRADE_TRABALHO.UF = ?1", nativeQuery = true)
    List<CarteiradeTrabalho> findCarteiradeTrabalhoByUf(String uf);

    @Query(value = "SELECT CARTEIRADE_TRABALHO.* FROM CARTEIRADE_TRABALHO,FUNCIONARIO WHERE FUNCIONARIO.ID_CARTEIRA = ?1 AND CARTEIRADE_TRABALHO.ID = ?1", nativeQuery = true)
    CarteiradeTrabalho findCarteiradeTrabalhoByIdCarteiradoFuncionario(Integer id);
}
