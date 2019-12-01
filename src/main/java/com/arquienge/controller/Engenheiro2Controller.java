package com.arquienge.controller;

import com.arquienge.model.*;
import com.arquienge.service.EnderecoService;
import com.arquienge.service.EngenheiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Engenheiro2Controller {

    @Autowired
    private final EngenheiroService engenheiroService;
    @Autowired
    private final EnderecoService enderecoService;


    public Engenheiro2Controller(EngenheiroService engenheiroService, EnderecoService enderecoService) {
        this.engenheiroService = engenheiroService;
        this.enderecoService = enderecoService;
    }

    @GetMapping("/cadastroEngenheiro2")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        ModelAndView view = new ModelAndView("registrar/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        return view;
    }

}
