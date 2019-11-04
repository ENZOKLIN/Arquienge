package com.arquienge.service;

import com.arquienge.model.Obra;
import com.arquienge.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    public List<Obra> selectAll() {
        List<Obra> obras = new ArrayList<>();
        for (Obra obra : obraRepository.findAll()) {
            obras.add(obra);
        }
        return obras;
    }

    public Optional<Obra> selectObraById(Integer id) {
        if (obraRepository.findById(id).isPresent()) {
            return obraRepository.findById(id);
        }
        throw new RuntimeException("Obra não encontrada!");
    }

    public void deleteObra(Obra obra) {
        obraRepository.delete(obra);
    }

    public void deleteObraById(Integer id) {
        obraRepository.deleteById(id);
    }

    public void saveObra(Obra obra) {
        obraRepository.save(obra);
    }

    public Obra selectObrabyName(String nome) {
        return obraRepository.findObraByName(nome);
    }

    public List<Obra> selectObrasByDatadeEntrega(Date date) {
        return obraRepository.findObrasByDatadeEntrega(date);
    }

    public List<Obra> selectObrasByDatadeInicio(Date date) {
        return obraRepository.findObrasByDatadeInicio(date);
    }
}
