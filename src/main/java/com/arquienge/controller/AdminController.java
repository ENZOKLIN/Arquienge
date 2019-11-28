package com.arquienge.controller;

import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.model.Proprietario;
import com.arquienge.service.EnderecoService;
import com.arquienge.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private final ProprietarioService proprietarioService;
    @Autowired
    private final EnderecoService enderecoService;

    public AdminController(ProprietarioService proprietarioService, EnderecoService enderecoService) {
        this.proprietarioService = proprietarioService;
        this.enderecoService = enderecoService;
    }


    @GetMapping("/cadastroMaster")
    public ModelAndView viewFormTest(Proprietario proprietario, Engenheiro engenheiro, Endereco endereco){
        ModelAndView view = new ModelAndView("registrar/cadastromaster");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        view.addObject("Proprietario", proprietario);
        return view;
    }

    @PostMapping("/cadastroProprietario")
    public Object register(@Valid Proprietario proprietario, @Valid Endereco endereco, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return new ModelAndView("registrar/cadastromaster").addObject("Proprietario", proprietario).addObject("Endereco", endereco);
        }
        enderecoService.saveEndereco(endereco);
        proprietario.setEndereco(endereco);
        proprietarioService.saveProprietario(proprietario);
        return "redirect:/login";
    }
}
