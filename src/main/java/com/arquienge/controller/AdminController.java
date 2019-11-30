package com.arquienge.controller;

import com.arquienge.model.Administrador;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/cadastro-admin")
    public ModelAndView viewFormTest(Proprietario proprietario, Endereco endereco){
        ModelAndView view = new ModelAndView("admin/cadastro-proprietario.html");
        view.addObject("Endereco", endereco);
        view.addObject("Proprietario", proprietario);
        return view;
    }

    @GetMapping("/index-admin")
    public ModelAndView viewIndexAdmin(Administrador admin){
        ModelAndView view = new ModelAndView("admin/admin.html");
        view.addObject("Admin", admin);
        return view;
    }

    @PostMapping("/cadastroProprietario")
    public Object register(@Valid Proprietario proprietario, @Valid Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageFailure", "Erro ao cadastrar proprietário!");
            return new ModelAndView("admin/cadastro-proprietario").addObject("Proprietario", proprietario).addObject("Endereco", endereco);  }
        enderecoService.saveEndereco(endereco);
        proprietario.setEndereco(endereco);
        proprietarioService.saveProprietario(proprietario);
        redirectAttributes.addFlashAttribute("messageSucess", "Proprietário cadastrado com sucesso!");
        return "redirect:/index-admin";
    }
}
