package com.arquienge.controller;

import com.arquienge.DTO.FerramentasDto;
import com.arquienge.DTO.MaquinasDto;
import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.*;
import com.arquienge.service.EnderecoService;
import com.arquienge.service.EngenheiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
