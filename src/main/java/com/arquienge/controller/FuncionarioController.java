package com.arquienge.controller;

import com.arquienge.model.Funcionario;
import com.arquienge.service.FuncionarioService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;
import java.text.AttributedString;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class FuncionarioController {

    @Autowired
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/cadastroFuncionario")
    public ModelAndView viewRegisterScreen(Funcionario funcionario) {
        ModelAndView view = new ModelAndView("cadastro/registrar/funcionario");
        view.addObject("Funcionario", funcionario);
        return view;
    }


}
