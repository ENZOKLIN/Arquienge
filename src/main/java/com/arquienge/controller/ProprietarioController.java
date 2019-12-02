package com.arquienge.controller;

import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProprietarioController {

    @Autowired
    private final ProprietarioService proprietarioService;
    @Autowired
    private final EnderecoService enderecoService;
    @Autowired
    private final EngenheiroService engenheiroService;
    @Autowired
    private final ObraService obraService;
    @Autowired
    private final FuncionarioService funcionarioService;


    public ProprietarioController(ProprietarioService proprietarioService, EnderecoService enderecoService, EngenheiroService engenheiroService, ObraService obraService, FuncionarioService funcionarioService) {
        this.proprietarioService = proprietarioService;
        this.enderecoService = enderecoService;
        this.engenheiroService = engenheiroService;
        this.obraService = obraService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/cadastroEngenheiro2")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        ModelAndView view = new ModelAndView("registrar/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        return view;
    }
}
