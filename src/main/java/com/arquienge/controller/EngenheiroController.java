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
        ModelAndView view = new ModelAndView("login");
        view.addObject("Engenheiro", engenheiro);
        return view;
    }

    @PostMapping("/login")
    public Object authenticate(@Valid Engenheiro engenheiro, BindingResult result, RedirectAttributes redirectAttributes) {
        boolean success = false;

        for (Engenheiro u : engenheiroService.selectAll()) {
            if (engenheiro.getEmail().equals(u.getEmail()) && engenheiro.getSenha().equals(u.getSenha())) {
                success = true;
                break;
            }
        }

        if (!success) {
            return this.viewLoginScreen(engenheiro);
        }
        Engenheiro logado = engenheiroService.findEngenheiroByEmailandSenha(engenheiro.getEmail(), engenheiro.getSenha());
        LogadoEstatico.setEngenheiroLogado(logado);
        redirectAttributes.addFlashAttribute("engenheiro", logado);
        return viewIndexScreenRedirect();
    }

    @GetMapping("redirect:/index")
    public RedirectView viewIndexScreenRedirect() {
        RedirectView view = new RedirectView("/index");
        return view;
    }

    @GetMapping("/index")
    public ModelAndView viewIndexScreen(ModelMap modelMap, @ModelAttribute("engenheiro") Engenheiro engenheiro){
        ModelAndView view = new ModelAndView("index");
        Logado logado = new Logado(false);
        Optional<Engenheiro> logged = engenheiroService.selectById(logado.getId());
        view.addObject("engenheiro", logged);
        return view;
    }


    @PostMapping("/cadastroEngenheiro")
    public Object register(@Valid Engenheiro engenheiro, @Valid Endereco endereco, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro, endereco);
        }
        enderecoService.saveEndereco(endereco);
        engenheiro.setEndereco(endereco);
        engenheiroService.saveEngenheiro(engenheiro);
        return "redirect:/login";
    }

    @GetMapping("/cadastroObra")
    public ModelAndView showCreateForm() {
        ModelAndView view = new ModelAndView("registrar/obra");
        MaquinasDto maquinasDto = new MaquinasDto();
        List<Maquina> maquinas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            maquinas.add(new Maquina());
        }
        maquinasDto.setMaquinas(maquinas);

//        MaquinasDto booksForm = new MaquinasDto();
//
//        for (int i = 1; i <= 3; i++) {
//            booksForm.addMaquina(new Maquina());
//        }
//
//        model.addAttribute("form", booksForm);
        view.addObject("form", maquinasDto);
        return view;
    }

}
