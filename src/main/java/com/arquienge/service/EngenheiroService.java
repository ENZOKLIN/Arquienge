package com.arquienge.service;

import com.arquienge.model.Engenheiro;
import com.arquienge.repository.EngenheiroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EngenheiroService {

    private final EngenheiroRepository engenheiroRepository;

    public EngenheiroService(EngenheiroRepository engenheiroRepository) {
        this.engenheiroRepository = engenheiroRepository;
    }

    public List<Engenheiro> selectAll() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        for (Engenheiro engenheiro : engenheiroRepository.findAll()) {
            engenheiros.add(engenheiro);
        }
        return engenheiros;
    }

    public Optional<Engenheiro> selectById(Integer id) {
        Optional<Engenheiro> engenheiro = engenheiroRepository.findById(id);
        if (isNull(engenheiro)) {
            throw new RuntimeException("Engenheiro não encontrado!");
        }
        return engenheiro;
    }

    public void saveEngenheiro(Engenheiro engenheiro) {
        engenheiroRepository.save(engenheiro);
    }

    public void deleteEngenheiroById(Integer id) {
        engenheiroRepository.deleteById(id);
    }

    public void deleteEngenheiro(Engenheiro engenheiro) {
        engenheiroRepository.delete(engenheiro);
    }

    public Engenheiro findEngenheiroByObraId(Integer id) {
        return engenheiroRepository.findEngenheiroByObraId(id);
    }

    public Engenheiro findEngenheiroByEmailandSenha(String email, String senha) {
        return engenheiroRepository.findEngenheiroByEmailAndSenha(email, senha);
    }
    public Engenheiro findEngenheiroById(Integer id){
        return engenheiroRepository.findEngenheiroById(id);
    }

    public Engenheiro findEngenheiroByCpf(String cpf) {
        return engenheiroRepository.findEngenheiroByCpf(cpf);
    }

    public Engenheiro findEngenheiroByTelefone(String telefone) {
        return engenheiroRepository.findEngenheiroByTelefone(telefone);
    }

    public Engenheiro findEngenheiroByNomeandSobrenome(String nome, String sobrenome) {
        return engenheiroRepository.findEngenheiroByNomeAndSobrenome(nome, sobrenome);
    }
}
