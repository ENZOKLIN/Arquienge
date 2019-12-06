package com.arquienge.repository;

import com.arquienge.model.DiarioDeObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarioDeObraRepository extends JpaRepository<DiarioDeObra, Integer> {

    @Query(nativeQuery = true, value = "SELECT DIARIO_DE_OBRA.* FROM DIARIO_DE_OBRA, OBRA WHERE OBRA.ID = ?1 AND DIARIO_DE_OBRA.ID_OBRA = ?1 AND OBRA.ID = DIARIO_DE_OBRA.ID_OBRA")
    List<DiarioDeObra> findDiarioDeObrasByObraId(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM DIARIO_DE_OBRA WHERE DIARIO.ID = ?1")
    DiarioDeObra findDiarioById(Integer id);
}
