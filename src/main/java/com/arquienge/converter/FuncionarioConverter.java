package com.arquienge.converter;

import com.arquienge.model.Funcionario;
import com.arquienge.service.FuncionarioService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


public class FuncionarioConverter implements Converter<String, Funcionario> {

    private FuncionarioService funcionarioService;

    @Override
    public Funcionario convert(String id) {
        Integer idt = Integer.parseInt(id);
        Funcionario funcionario = funcionarioService.findFuncionarioById(idt);
        return funcionario;
    }
}
