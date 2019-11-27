package com.arquienge.repository;

import com.arquienge.model.Funcionario;
import com.arquienge.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Integer> {


    @Query(value = "SELECT DIARIO_DE_OBRA.DATA_CRIACAO FROM DIARIO_DE_OBRA, PRESENCA, FUNCIONARIO WHERE FUNCIONARIO.ID = ?1 AND PRESENCA.ID_FUNCIONARIO = ?1 AND PRESENCA.ID_DIARIO = DIARIO_DE_OBRA.ID AND PRESENCA.PRESENCA = false", nativeQuery = true)
    List<Date> datasDeFaltaByFuncionarioId(Integer idFuncionario);

    @Query(value = "SELECT FUNCIONARIO.* FROM DIARIO_DE_OBRA, PRESENCA, FUNCIONARIO WHERE DIARIO_DE_OBRA.ID = ?1 AND PRESENCA.ID_DIARIO = ?1 AND PRESENCA.ID_FUNCIONARIO = FUNCIONARIO.ID AND PRESENCA.PRESENCA = false", nativeQuery = true)
    List<Funcionario> funcionariosAusentesByDiarioId(Integer idDiario);

    @Query(value = "SELECT FUNCIONARIO.* FROM DIARIO_DE_OBRA, PRESENCA, FUNCIONARIO WHERE DIARIO_DE_OBRA.DATA_CRIACAO = ?1 AND PRESENCA.ID_DIARIO = DIARIO_DE_OBRA.ID AND PRESENCA.ID_FUNCIONARIO = FUNCIONARIO.ID AND PRESENCA.PRESENCA = false", nativeQuery = true)
    List<Funcionario> funcionariosAusentesByDiaDiario(Date dia);

    @Query(value = "SELECT FUNCIONARIO.* FROM DIARIO_DE_OBRA, PRESENCA, FUNCIONARIO WHERE DIARIO_DE_OBRA.ID = ?1 AND PRESENCA.ID_DIARIO = ?1 AND PRESENCA.ID_FUNCIONARIO = FUNCIONARIO.ID AND PRESENCA.PRESENCA = true", nativeQuery = true)
    List<Funcionario> funcionariosPresentesByDiarioId(Integer idDiario);

    @Query(value = "SELECT DIARIO_DE_OBRA.DATA_CRIACAO FROM DIARIO_DE_OBRA, PRESENCA, FUNCIONARIO WHERE FUNCIONARIO.ID = ?1 AND PRESENCA.ID_FUNCIONARIO = ?1 AND PRESENCA.ID_DIARIO = DIARIO_DE_OBRA.ID AND PRESENCA.PRESENCA = true", nativeQuery = true)
    List<Date> datasDePresencaByFuncionarioId(Integer idFuncionario);
}
