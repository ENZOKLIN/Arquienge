package com.arquienge.service;

import com.arquienge.model.MaquinasUsadas;
import com.arquienge.repository.MaquinasUsadasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaquinasUsadasService {

    private final MaquinasUsadasRepository maquinasUsadasRepository;

    public MaquinasUsadasService(MaquinasUsadasRepository maquinasUsadasRepository) {
        this.maquinasUsadasRepository = maquinasUsadasRepository;
    }

    public void saveMaquinaUsada(MaquinasUsadas maquinasUsadas){
        maquinasUsadasRepository.save(maquinasUsadas);
    }
}
