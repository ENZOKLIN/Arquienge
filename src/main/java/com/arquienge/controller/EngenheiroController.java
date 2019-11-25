package com.arquienge.controller;

import com.arquienge.model.Engenheiro;
import com.arquienge.service.EngenheiroService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;
import java.text.AttributedString;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class EngenheiroController {

    @Autowired
    private final EngenheiroService engenheiroService;

    public EngenheiroController(EngenheiroService engenheiroService) {
        this.engenheiroService = engenheiroService;
    }

    @GetMapping("/cadastroEngenheiro")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro) {
        ModelAndView view = new ModelAndView("cadastro/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        return view;
    }

    @GetMapping("/login")
    public ModelAndView viewLoginScreen(Engenheiro engenheiro) {
        ModelAndView view = new ModelAndView("cadastro/login");
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
        ModelAndView view = new ModelAndView("cadastro/index");
        Engenheiro logado = engenheiroService.findEngenheiroByEmailandSenha(engenheiro.getEmail(), engenheiro.getSenha());
        view.addObject("engenheiro", logado);
        return view;
    }


    @PostMapping("/cadastroEngenheiro")
    public Object register(@Valid Engenheiro engenheiro, BindingResult result) throws IOException {
        if(result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro);
        }
        engenheiroService.saveEngenheiro(engenheiro);
        return "redirect:/login";
    }

}
