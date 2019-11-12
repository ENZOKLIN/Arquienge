package com.arquienge.controller;

import com.arquienge.model.Engenheiro;
import com.arquienge.service.EngenheiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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



    @PostMapping("/cadastroEngenheiro")
    public Object register(@Valid Engenheiro engenheiro, BindingResult result){
        if(result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro);
        }
        engenheiroService.saveEngenheiro(engenheiro);
        return "redirect:/cadastro/engenheiro";
    }

}
