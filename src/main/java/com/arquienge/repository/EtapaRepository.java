package com.arquienge.repository;

import com.arquienge.model.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EtapaRepository extends JpaRepository<Etapa, Integer> {

    @Query(value = "SELECT ETAPA.* FROM ETAPA,OBRA WHERE ETAPA.ID_OBRA = ?1", nativeQuery = true)
    List<Etapa> findEtapasByObraId(Integer id);

    @Query(value = "SELECT * FROM ETAPA WHERE ETAPA.NOME_ETAPA = ?1", nativeQuery = true)
    Etapa findEtapaByName(String nome);

    @Query(value= "SELECT * FROM ETAPA WHERE ETAPA.STATUS = ?1", nativeQuery = true)
    /*
    0 = Não Iniciado
    1 = Em Andamento
    2 = Concluido
    */
    List<Etapa> findEtapasByStatus(Integer status);


}
