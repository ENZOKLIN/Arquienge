package com.arquienge.repository;

import com.arquienge.model.Ferramenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, Integer> {

    @Query(value = "SELECT FERRAMENTA.* FROM FERRAMENTA,OBRA WHERE FERRAMENTA.ID_OBRA = ?1 AND OBRA.ID = ?1", nativeQuery = true)
    List<Ferramenta> findFerramentasByObraId (Integer id);

    @Query(value = "SELECT FERRAMENTA.* FROM FERRAMENTA WHERE FERRAMENTA.FERRAMENTA = ?1", nativeQuery = true)
    Ferramenta findFerramentaByName(String nome);

    @Query(value = "SELECT SUM(QUANTIDADE) FROM FERRAMENTA WHERE FERRAMENTA.ID_OBRA = ?1", nativeQuery = true)
    Integer findTotaldeFerramentasByObraId(Integer id);
}
