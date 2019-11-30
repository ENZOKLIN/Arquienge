package com.arquienge.controller;

import com.arquienge.DTO.FerramentasDto;
import com.arquienge.DTO.MaquinasDto;
import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.*;
import com.arquienge.service.*;
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
    @Autowired
    private final MaquinaService maquinaService;
    @Autowired
    private final FerramentaService ferramentaService;
    @Autowired
    private final ObraService obraService;


    public EngenheiroController(EngenheiroService engenheiroService, EnderecoService enderecoService, MaquinaService maquinaService, ObraService obraService, FerramentaService ferramentaService) {
        this.engenheiroService = engenheiroService;
        this.maquinaService = maquinaService;
        this.enderecoService = enderecoService;
        this.obraService = obraService;
        this.ferramentaService = ferramentaService;
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
        Engenheiro engenheiro1 = (Engenheiro) logged.get();
        view.addObject("engenheiro", engenheiro1);
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
        if(LogadoEstatico.getId() != null) {
            ModelAndView view = new ModelAndView("registrar/obra");
            MaquinasDto maquinasDto = new MaquinasDto();
            FerramentasDto ferramentasDto = new FerramentasDto();
            List<Maquina> maquinas = new ArrayList<>();
            List<Ferramenta> ferramentas = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                maquinas.add(new Maquina());
                maquinas.get(i - 1).setCod_maquina(i);
            }
            for (int i = 1; i <= 5; i++) {
                ferramentas.add(new Ferramenta());
                ferramentas.get(i - 1).setCod_ferramenta(i);
            }
            ferramentasDto.setFerramentas(ferramentas);
            maquinasDto.setMaquinas(maquinas);
            Obra obra = new Obra();
            Endereco endereco = new Endereco();
            Logado logado = new Logado(false);
            Engenheiro engenheiro = engenheiroService.findEngenheiroById(logado.getRg());
            view.addObject("Engenheiro", engenheiro);
            view.addObject("Endereco", endereco);
            view.addObject("Obra", obra);
            view.addObject("form", maquinasDto);
            view.addObject("form2", ferramentasDto);
            return view;
        }
        else{
            return new ModelAndView("/login");
        }
    }

    @PostMapping("/cadastroObra")
    public Object createObra(@ModelAttribute MaquinasDto form, @ModelAttribute FerramentasDto form2, Obra obra, Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addAttribute("messageFailure", "Obra não pode ser cadastrada!");
            return "redirect:/cadastroObra";
        }

        for(Maquina maquina : form.getMaquinas()){
            maquinaService.saveMaquina(maquina);
        }
        for(Ferramenta ferramenta : form2.getFerramentas()){
            ferramentaService.saveFerramenta(ferramenta);
        }

        Logado logado = new Logado(false);
        if(LogadoEstatico.getId() != null) {
            obra.setEngenheiro(engenheiroService.findEngenheiroById(logado.getRg()));
            obra.setEndereco(endereco);
            obraService.saveObra(obra);
            redirectAttributes.addAttribute("messageSucess", "Obra salva com sucesso!");
            return "/cadastroObra";
        }
        return "redirect:/login";
    }
}
