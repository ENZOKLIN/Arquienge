package com.arquienge.repository;

import com.arquienge.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {
    @Query(value = "SELECT * FROM OBRA WHERE OBRA.ID = ?1", nativeQuery = true)
    Obra findObraById_obra(Integer id);

    @Query(value = "SELECT * FROM OBRA WHERE OBRA.NOME_OBRA = ?1", nativeQuery = true)
    Obra findObraByName(String nome);

    @Query(value = "SELECT * FROM OBRA WHERE DT_ENTREGA = ?1", nativeQuery = true)
    List<Obra> findObrasByDatadeEntrega(Date date);

    @Query(value = "SELECT * FROM OBRA WHERE DT_INICIO = ?1", nativeQuery = true)
    List<Obra> findObrasByDatadeInicio(Date date);

    @Query(value = "SELECT * FROM OBRA WHERE OBRA.ID_ENGENHEIRO = ?1", nativeQuery = true)
    List<Obra> findObraByEngenheiro(Integer id);

    @Query(value = "SELECT OBRA.* FROM OBRA,ENGENHEIRO,PROPRIETARIO WHERE PROPRIETARIO.ID = ?1 AND ENGENHEIRO.PROPRIETARIO_ID = ?1 AND OBRA.ID_ENGENHEIRO = ENGENHEIRO.ID", nativeQuery = true)
    List<Obra> findObrasByProprietario(Integer id);

}
