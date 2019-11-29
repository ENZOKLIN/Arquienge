package com.arquienge.repository;

import com.arquienge.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    Administrador getAdministradorByUsuarioAndAndSenha(String usuario, String senha);

}
