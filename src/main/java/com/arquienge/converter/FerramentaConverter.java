package com.arquienge.converter;

import com.arquienge.model.Ferramenta;
import com.arquienge.model.Funcionario;
import com.arquienge.service.FuncionarioService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FerramentaConverter implements Converter<String, Integer> {


    public Ferramenta convert(Integer id) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setCod_ferramenta(id);
        return ferramenta;
    }

    @Override
    public Integer convert(String s) {
        return null;
    }
}
