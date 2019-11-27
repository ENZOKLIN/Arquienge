package com.arquienge.service;

import com.arquienge.model.Endereco;
import com.arquienge.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> selectAll() {
        List<Endereco> enderecos = new ArrayList<>();
        for (Endereco endereco : enderecoRepository.findAll()) {
            enderecos.add(endereco);
        }
        return enderecos;
    }

    public void saveEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public void deleteEnderecobyId(Integer id) {
        enderecoRepository.deleteById(id);
    }

    public void deleteEndereco(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

    public List<Endereco> selectByCep(String cep) {
        List<Endereco> enderecos = new ArrayList<>();
        for (Endereco endereco : enderecoRepository.findByCep(cep)) {
            enderecos.add(endereco);
        }
        return enderecos;
    }

    public Optional<Endereco> selectById(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (isNull(endereco)) {
            throw new RuntimeException("Endereço não encontrado!");
        }
        return endereco;
    }

    public Endereco findEnderecoByObraId(Integer id) {
        return enderecoRepository.findEnderecoByObraId(id);
    }

    public Endereco findEnderecoByFuncionarioId(Integer id) {
        return enderecoRepository.findEnderecoByFuncionarioId(id);
    }

    public List<Endereco> findEnderecosByBairro(String bairro) {
        return enderecoRepository.findEnderecosByBairro(bairro);
    }

    public List<Endereco> findEnderecosByCidade(String cidade) {
        return enderecoRepository.findEnderecosByCidade(cidade);
    }

    public List<Endereco> findEnderecosByRua(String rua) {
        return enderecoRepository.findEnderecosByRua(rua);
    }

    public void insertEndereco(Integer id, String bairro, String cep, String cidade, Integer numero, String rua) {
        enderecoRepository.insertEndereco(id, bairro, cep, cidade, numero, rua);
    }
}
