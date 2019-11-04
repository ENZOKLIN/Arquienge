package com.arquienge.service;

import com.arquienge.model.Ferramenta;
import com.arquienge.repository.FerramentaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Objects.isNull;

@Service
public class FerramentaService {

    private final FerramentaRepository ferramentaRepository;

    public FerramentaService(FerramentaRepository ferramentaRepository) {
        this.ferramentaRepository = ferramentaRepository;
    }

    public List<Ferramenta> selectAll() {
        List<Ferramenta> ferramentas = new ArrayList<>();
        for (Ferramenta ferramenta : ferramentaRepository.findAll()) {
            ferramentas.add(ferramenta);
        }
        return ferramentas;
    }

    public Optional<Ferramenta> selectById(Integer id) {
        Optional<Ferramenta> ferramenta = ferramentaRepository.findById(id);
        if (isNull(ferramenta)) {
            throw new RuntimeException("Ferramenta não encontrada!");
        }
        return ferramenta;
    }

    public void saveFerramenta(Ferramenta ferramenta) {
        ferramentaRepository.save(ferramenta);
    }

    public void deleteFerramentaById(Integer id) {
        ferramentaRepository.deleteById(id);
    }

    public void deleteFerramenta(Ferramenta ferramenta) {
        ferramentaRepository.delete(ferramenta);
    }

    public List<Ferramenta> findFerramentasByObraId(Integer id) {
        return ferramentaRepository.findFerramentasByObraId(id);
    }

    public Ferramenta findFerramentaByName(String name) {
        return ferramentaRepository.findFerramentaByName(name);
    }

    public Integer findTotalFerramentasByObraId(Integer id) {
        return ferramentaRepository.findTotaldeFerramentasByObraId(id);
    }

}
