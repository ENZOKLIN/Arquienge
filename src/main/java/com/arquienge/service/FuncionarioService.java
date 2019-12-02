package com.arquienge.service;

import com.arquienge.model.Engenheiro;
import com.arquienge.model.Funcionario;
import com.arquienge.model.Proprietario;
import com.arquienge.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> selectAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        for (Funcionario funcionario : funcionarioRepository.findAll()) {
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }

    public Optional<Funcionario> selectById(Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (isNull(funcionario)) {
            throw new RuntimeException("Funcionário não encontrado!");
        }
        return funcionario;
    }

    public void saveFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionarioById(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    public void deleteFuncionario(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }

    public List<Funcionario> findFuncionariosByObraId(Integer id, String nomeobra) {
        return funcionarioRepository.findFuncionariosByObraId(id, nomeobra);
    }

    public Funcionario findFuncionarioByName(String nome, String sobrenome) {
        return funcionarioRepository.findFuncionarioByName(nome, sobrenome);
    }

    public Funcionario findFuncionarioByCpf(String cpf) {
        return funcionarioRepository.findFuncionarioByCpf(cpf);
    }

    public Funcionario findFuncionarioByEmail(String email) {
        return funcionarioRepository.findFuncionarioByEmail(email);
    }

    public List<Funcionario> findFuncionariosByCargo(String cargo) {
        return funcionarioRepository.findFuncionariosByCargo(cargo);
    }

    public Funcionario findFuncionarioByCarteira(String numerocarteira) {
        return funcionarioRepository.findFuncionarioByCarteira(numerocarteira);
    }

    public List<Funcionario> findFuncionariosByCep(String cep) {
        return funcionarioRepository.findFuncionariosByCep(cep);
    }
    public Funcionario findFuncionarioById(Integer id){
        return funcionarioRepository.findFuncionarioById(id);
    }
    public List<Funcionario> findFuncionariosByEngenheiro(Integer id){
        return funcionarioRepository.findFuncionariosByEngenheiroId(id);
    }
    public List<Funcionario> findFuncionariosByProprietario(Proprietario proprietario){
        return funcionarioRepository.findFuncionariosByProprietario(proprietario);
    }

}
