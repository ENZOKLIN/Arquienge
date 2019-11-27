package com.arquienge.repository;

import com.arquienge.model.FerramentasUsadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerramentasUsadasRepository extends JpaRepository<FerramentasUsadas, Integer> {
}
