package com.arquienge.service;

import com.arquienge.model.Etapa;
import com.arquienge.repository.EtapaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EtapaService {

    private final EtapaRepository etapaRepository;

    public EtapaService(EtapaRepository etapaRepository) {
        this.etapaRepository = etapaRepository;
    }

    public List<Etapa> selectAll() {
        List<Etapa> etapas = new ArrayList<>();
        for (Etapa etapa : etapaRepository.findAll()) {
            etapas.add(etapa);
        }
        return etapas;
    }

    public Optional<Etapa> selectById(Integer id) {
        Optional<Etapa> etapa = etapaRepository.findById(id);
        if (isNull(etapa)) {
            throw new RuntimeException("Maquina não encontrada!");
        }
        return etapa;
    }

    public void saveEtapa(Etapa etapa) {
        etapaRepository.save(etapa);
    }

    public void deleteEtapaById(Integer id) {
        etapaRepository.deleteById(id);
    }

    public void deleteEtapa(Etapa etapa) {
        etapaRepository.delete(etapa);
    }

    public List<Etapa> findEtapasByObraId(Integer id) {
        return etapaRepository.findEtapasByObraId(id);
    }

    public List<Etapa> findEtapasByStatus(Integer status) {
        return etapaRepository.findEtapasByStatus(status);
    }

    public Etapa findEtapaByNome(String nome) {
        return etapaRepository.findEtapaByName(nome);
    }
}
