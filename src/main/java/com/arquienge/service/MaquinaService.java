package com.arquienge.service;

import com.arquienge.model.Maquina;
import com.arquienge.repository.MaquinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@Transactional
public class MaquinaService {

    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> selectAll() {
        List<Maquina> maquinas = new ArrayList<>();
        for (Maquina maquina : maquinaRepository.findAll()) {
            maquinas.add(maquina);
        }
        return maquinas;
    }

    public Optional<Maquina> selectById(Integer id) {
        Optional<Maquina> maquina = maquinaRepository.findById(id);
        if (isNull(maquina)) {
            throw new RuntimeException("Maquina não encontrada!");
        }
        return maquina;
    }

    public void saveMaquina(Maquina maquina) {
        maquinaRepository.save(maquina);
    }

    public void deleteMaquinaById(Integer id) {
        maquinaRepository.deleteById(id);
    }

    public void deleteMaquina(Maquina maquina) {
        maquinaRepository.delete(maquina);
    }

    public Maquina findMaquinaByPlaca(String placa) {
        return maquinaRepository.findMaquinaByPlaca(placa);
    }

    public List<Maquina> findMaquinasByObraId(Integer id) {
        return maquinaRepository.findMaquinasByObraId(id);
    }

    public List<Maquina> findMaquinasByTipo(String tipo) {
        return maquinaRepository.findMaquinasByTipo(tipo);
    }

    public Integer findTotalDeMaquinasById(Integer id) {
        return maquinaRepository.findTotaldeMaquinasByObraId(id);
    }

}
