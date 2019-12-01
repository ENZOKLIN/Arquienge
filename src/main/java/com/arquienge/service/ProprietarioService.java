package com.arquienge.service;

import com.arquienge.model.Proprietario;
import com.arquienge.repository.ProprietarioRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<Proprietario> selectAll() {
        List<Proprietario> proprietarios = new ArrayList<>();
        for (Proprietario proprietario : proprietarioRepository.findAll()) {
            proprietarios.add(proprietario);
        }
        return proprietarios;
    }

    public Optional<Proprietario> selectById(Integer id) {
        Optional<Proprietario> proprietario = proprietarioRepository.findById(id);
        if (isNull(proprietario)) {
            throw new RuntimeException("Proprietário não encontrado!");
        }
        return proprietario;
    }

    public void saveProprietario(Proprietario proprietario) {
        proprietarioRepository.save(proprietario);
    }

    public void deleteProprietarioById(Integer id) {
        proprietarioRepository.deleteById(id);
    }

    public void deleteProprietario(Proprietario proprietario) {
        proprietarioRepository.delete(proprietario);
    }

    public Proprietario findProprietarioByNomeandSobrenome(String nome, String sobrenome) {
        return proprietarioRepository.findProprietarioByNomeAndSobrenome(nome, sobrenome);
    }

    public Proprietario findProprietarioByEmaileSenha(String email, String senha) {
        return proprietarioRepository.findProprietarioByEmailAndSenha(email, senha);
    }
    public Proprietario findProprietarioByEngenheiroId(Integer id){
        return proprietarioRepository.findProprietarioByEngenheiroId(id);
    }

    public Proprietario findProprietarioByCpf(String cpf) {
        return proprietarioRepository.findProprietarioByCpf(cpf);
    }

    public Proprietario findProprietarioByTelefone(String telefone) {
        return proprietarioRepository.findProprietarioByTelefone(telefone);
    }

    public List<Proprietario> findProprietariosByCep(String cidade) {
        return proprietarioRepository.findProprietariosByCidade(cidade);
    }
    public Proprietario findProprietarioById(Integer id){
        return proprietarioRepository.findProprietarioById(id);
    }
}
