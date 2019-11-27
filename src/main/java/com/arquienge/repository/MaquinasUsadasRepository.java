package com.arquienge.repository;

import com.arquienge.model.Maquina;
import com.arquienge.model.MaquinasUsadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquinasUsadasRepository extends JpaRepository<MaquinasUsadas, Integer> {

List<Maquina> getMaquinasUsadasByDiarioDeObraId(Integer id);
}
