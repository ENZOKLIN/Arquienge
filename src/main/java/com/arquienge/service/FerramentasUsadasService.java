package com.arquienge.service;

import com.arquienge.model.FerramentasUsadas;
import com.arquienge.repository.FerramentasUsadasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FerramentasUsadasService {

    private final FerramentasUsadasRepository ferramentasUsadasRepository;

    public FerramentasUsadasService(FerramentasUsadasRepository ferramentasUsadasRepository) {
        this.ferramentasUsadasRepository = ferramentasUsadasRepository;
    }

    public void saveFerramentaUsada(FerramentasUsadas ferramentasUsadas){
        ferramentasUsadasRepository.save(ferramentasUsadas);
    }
}
