package com.arquienge.repository;

import com.arquienge.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {

    @Query(value = "SELECT MAQUINA.* FROM MAQUINA,OBRA WHERE MAQUINA.ID_OBRA = ?1 and OBRA.ID = ?1", nativeQuery = true)
    List<Maquina> findMaquinasByObraId(Integer id);

    @Query(value = "SELECT MAQUINA.* FROM MAQUINA WHERE MAQUINA.TIPO_MAQUINA = ?1", nativeQuery = true)
    List<Maquina> findMaquinasByTipo(String tipo);

    @Query(value = "SELECT * FROM MAQUINA WHERE MAQUINA.PLACA = ?1", nativeQuery = true)
    Maquina findMaquinaByPlaca(String placa);

    @Query(value = "SELECT SUM(QUANTIDADE) FROM MAQUINA WHERE MAQUINA.ID_OBRA = ?1", nativeQuery = true)
    Integer findTotaldeMaquinasByObraId(Integer id);
}
