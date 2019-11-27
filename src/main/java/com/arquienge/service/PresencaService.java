package com.arquienge.service;

import com.arquienge.model.Funcionario;
import com.arquienge.model.Presenca;
import com.arquienge.repository.PresencaRepository;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PresencaService {

    private final PresencaRepository presencaRepository;

    public PresencaService(PresencaRepository presencaRepository) {
        this.presencaRepository = presencaRepository;
    }

    public List<Presenca> selectAll() {
        List<Presenca> presencas = new ArrayList<>();
        for (Presenca presenca : presencaRepository.findAll()) {
            presencas.add(presenca);
        }
        return presencas;
    }
    public void savePresenca(Presenca presenca) {
        presencaRepository.save(presenca);
    }
    public void deletePresencaById(Integer id) {
        presencaRepository.deleteById(id);
    }
    public void deletePresenca(Presenca presenca) {
        presencaRepository.delete(presenca);
    }

    public List<Date> getFaltasByFuncionarioId(Integer id) {
        return presencaRepository.datasDeFaltaByFuncionarioId(id);
    }
    public List<Funcionario> getFuncionariosAusentesByDiarioId(Integer id){
        return presencaRepository.funcionariosAusentesByDiarioId(id);
    }
    public List<Funcionario> getFuncionariosPresentesByDiarioId(Integer id){
        return presencaRepository.funcionariosPresentesByDiarioId(id);
    }
    public List<Funcionario> getFuncionariosAusentesByDiaDiario(Date dia){
        return presencaRepository.funcionariosAusentesByDiaDiario(dia);
    }
    public List<Date> getDatasDePresencaByFuncionarioId(Integer id){
        return presencaRepository.datasDePresencaByFuncionarioId(id);
    }
    public Optional<Presenca> getPresencaById(Integer id){
        return presencaRepository.findPresencaById(id);
    }
}
