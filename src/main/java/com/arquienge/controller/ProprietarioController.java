package com.arquienge.controller;

import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

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

    @GetMapping("/cadastro/engenheiro")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        if(LogadoEstatico.getEmail() != null && LogadoEstatico.getNome() != null) {
            if (LogadoEstatico.prop) {
                ModelAndView view = new ModelAndView("proprietario/engenheiro");
                view.addObject("Engenheiro", engenheiro);
                view.addObject("Endereco", endereco);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/cadastro/engenheiro")
    public Object register(@Valid Engenheiro engenheiro, @Valid Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro, endereco);
        }
        if(LogadoEstatico.prop && LogadoEstatico.getEmail() != null) {
            if(engenheiroService.findEngenheiroByEmail(engenheiro.getEmail()) != null)
            {
                redirectAttributes.addFlashAttribute("messageFailure", "Engenheiro com este email já cadastrado.");
                return "redirect:/cadastro/engenheiro";
            }
            enderecoService.saveEndereco(endereco);
            engenheiro.setEndereco(endereco);
            engenheiro.setProprietario(proprietarioService.findProprietarioById(LogadoEstatico.getId()));
            engenheiroService.saveEngenheiro(engenheiro);
            redirectAttributes.addFlashAttribute("messageSucess", "Engenheiro cadastrado com sucesso.");
            return "redirect:/cadastro/engenheiro";
        }
        else if(!LogadoEstatico.prop && LogadoEstatico.getEmail() != null){
            return "redirect:/index";
        }
        return "redirect:/login";
    }
}
