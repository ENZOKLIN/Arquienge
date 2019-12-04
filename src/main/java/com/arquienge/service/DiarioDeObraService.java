package com.arquienge.service;


import com.arquienge.model.DiarioDeObra;
import com.arquienge.repository.DiarioDeObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class DiarioDeObraService {

    @Autowired
    private final DiarioDeObraRepository diarioDeObraRepository;

    public DiarioDeObraService(DiarioDeObraRepository diarioDeObraRepository) {
        this.diarioDeObraRepository = diarioDeObraRepository;
    }

    public List<DiarioDeObra> selectAll() {
        List<DiarioDeObra> diariosdeobra = new ArrayList<>();
        for (DiarioDeObra diarioDeObra : diarioDeObraRepository.findAll()) {
            diariosdeobra.add(diarioDeObra);
        }
        return diariosdeobra;
    }

    public Optional<DiarioDeObra> selectById(Integer id) {
        Optional<DiarioDeObra> diariodeobra = diarioDeObraRepository.findById(id);
        if (isNull(diariodeobra)) {
            throw new RuntimeException("Diario não encontrado!");
        }
        return diariodeobra;
    }

    public List<DiarioDeObra> findDiariosByObraId(Integer id){
        return diarioDeObraRepository.findDiarioDeObrasByObraId(id);
    }
}
