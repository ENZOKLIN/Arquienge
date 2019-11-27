package com.arquienge.controller;

import com.arquienge.model.Endereco;
import com.arquienge.model.Engenheiro;
import com.arquienge.service.EnderecoService;
import com.arquienge.service.EngenheiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;


@Controller
public class EngenheiroController {

    @Autowired
    private final EngenheiroService engenheiroService;
    @Autowired
    private final EnderecoService enderecoService;

    public EngenheiroController(EngenheiroService engenheiroService, EnderecoService enderecoService) {
        this.engenheiroService = engenheiroService;
        this.enderecoService = enderecoService;
    }

    @GetMapping("/cadastroEngenheiro")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        ModelAndView view = new ModelAndView("testes/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        return view;
    }

    @GetMapping("/login")
    public ModelAndView viewLoginScreen(Engenheiro engenheiro) {
        ModelAndView view = new ModelAndView("testes/login");
        view.addObject("Engenheiro", engenheiro);
        return view;
    }

    @PostMapping("/login")
    public Object authenticate(@Valid Engenheiro engenheiro, BindingResult result, RedirectAttributes redirectAttributes){
        boolean success = false;

        for(Engenheiro u: engenheiroService.selectAll()) {
            if (engenheiro.getEmail().equals(u.getEmail()) && engenheiro.getSenha().equals(u.getSenha())){
                success = true;
                break;
            }
        }

        if(!success) {
            return this.viewLoginScreen(engenheiro);
        }
        redirectAttributes.addFlashAttribute("engenheiro", engenheiro);
        return viewIndexScreen(engenheiro);
    }

    @GetMapping("/index")
    public ModelAndView viewIndexScreen(Engenheiro engenheiro) {
        ModelAndView view = new ModelAndView("testes/index");
        Engenheiro logado = engenheiroService.findEngenheiroByEmailandSenha(engenheiro.getEmail(), engenheiro.getSenha());
        view.addObject("engenheiro", logado);
        return view;
    }


    @PostMapping("/cadastroEngenheiro")
    public Object register(@Valid Engenheiro engenheiro, @Valid Endereco endereco, BindingResult result) throws IOException {
        if(result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro, endereco);
        }
        enderecoService.saveEndereco(endereco);
        engenheiro.setEndereco(endereco);
        engenheiroService.saveEngenheiro(engenheiro);
        return "redirect:/login";
    }

}
