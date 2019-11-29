package com.arquienge.service;

import com.arquienge.model.Administrador;
import com.arquienge.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class AdministradorService {

    @Autowired
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> selectAll() {
        List<Administrador> administradores = new ArrayList<>();
        for (Administrador administrador : administradorRepository.findAll()) {
            administradores.add(administrador);
        }
        return administradores;
    }

    public Optional<Administrador> selectById(Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        if (isNull(administrador)) {
            throw new RuntimeException("Funcionário não encontrado!");
        }
        return administrador;
    }
    public Administrador getByUsuarioAndSenha(String usuario, String senha){
        return administradorRepository.getAdministradorByUsuarioAndAndSenha(usuario, senha);
    }
}
