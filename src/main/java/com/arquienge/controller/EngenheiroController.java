package com.arquienge.controller;

import com.arquienge.DTO.FerramentasDto;
import com.arquienge.DTO.MaquinasDto;
import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.*;
import com.arquienge.service.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private final ProprietarioService proprietarioService;


    public EngenheiroController(EngenheiroService engenheiroService, EnderecoService enderecoService, MaquinaService maquinaService, ObraService obraService, FerramentaService ferramentaService, ProprietarioService proprietarioService) {
        this.engenheiroService = engenheiroService;
        this.maquinaService = maquinaService;
        this.enderecoService = enderecoService;
        this.obraService = obraService;
        this.ferramentaService = ferramentaService;
        this.proprietarioService = proprietarioService;
    }

    @GetMapping("/cadastroEngenheiro")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        ModelAndView view = new ModelAndView("testes/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        return view;
    }

    @GetMapping("/login")
    public ModelAndView viewLoginScreen() {
        ModelAndView view = new ModelAndView("login-new");
        Engenheiro engenheiro = new Engenheiro();
        Proprietario proprietario = new Proprietario();
        view.addObject("engenheiro", engenheiro);
        view.addObject("proprietario", proprietario);
        return view;
    }

    @PostMapping("/login")
    public Object authenticate(@RequestParam String type, Engenheiro engenheiro, Proprietario proprietario, BindingResult result, RedirectAttributes redirectAttributes) {
        boolean success = false;
        if (type.equals("engenheiro")) {
            for (Engenheiro u : engenheiroService.selectAll()) {
                if (engenheiro.getEmail().equals(u.getEmail()) && engenheiro.getSenha().equals(u.getSenha())) {
                    success = true;
                    break;
                }
            }
            Engenheiro logado = engenheiroService.findEngenheiroByEmailandSenha(engenheiro.getEmail(), engenheiro.getSenha());
            LogadoEstatico.setEngenheiroLogado(logado);
        } else if (type.equals("proprietario")) {
            for (Proprietario p : proprietarioService.selectAll()) {
                if (proprietario.getEmail().equals(p.getEmail()) && proprietario.getSenha().equals(p.getSenha())) {
                    success = true;
                    break;
                }
            }
            Proprietario logado = proprietarioService.findProprietarioByEmaileSenha(proprietario.getEmail(), proprietario.getSenha());
            LogadoEstatico.setProprietarioLogado(logado);
        }

        if (!success) {
            return this.viewLoginScreen();
        }
        return viewIndexScreenRedirect();
    }

    @GetMapping("redirect:/index")
    public RedirectView viewIndexScreenRedirect() {
        RedirectView view = new RedirectView("/index");
        return view;
    }

    @GetMapping("/index")
    public ModelAndView viewIndexScreen() {
        if(LogadoEstatico.getEmail() != null) {
            ModelAndView view = new ModelAndView("index");
            if (LogadoEstatico.prop != true && LogadoEstatico.getId() != 0) {
                Logado logado = new Logado(false);
                Optional<Engenheiro> logged = engenheiroService.selectById(logado.getId());
                Engenheiro engenheiro = (Engenheiro) logged.get();
                view.addObject("engenheiro", engenheiro);
                return view;
            } else if (LogadoEstatico.prop && LogadoEstatico.getId() != 0) {
                Logado logado = new Logado(true);
                Optional<Proprietario> logged = proprietarioService.selectById(logado.getId());
                Proprietario proprietario = (Proprietario) logged.get();
                view.addObject("proprietario", proprietario);
                return view;
            }
        }
            return new ModelAndView("redirect:/login");
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
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.prop != true && LogadoEstatico.getId() != 0) {
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
                Optional<Engenheiro> engenheiro = engenheiroService.selectById(LogadoEstatico.getId());
                Engenheiro engenheiro1 = (Engenheiro) engenheiro.get();
                view.addObject("engenheiro", engenheiro1);
                view.addObject("endereco", endereco);
                view.addObject("obra", obra);
                view.addObject("form", maquinasDto);
                view.addObject("form2", ferramentasDto);
                return view;
            } else if (LogadoEstatico.prop && LogadoEstatico.getId() != null) {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/cadastroObra")
    public Object createObra(@ModelAttribute MaquinasDto form, @ModelAttribute FerramentasDto form2, Obra obra, Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addAttribute("messageFailure", "Obra não pode ser cadastrada!");
            return "redirect:/cadastroObra";
        }

        Logado logado = new Logado(false);
        if (LogadoEstatico.getId() != null) {
            Optional<Engenheiro> engenheiro = engenheiroService.selectById(logado.getId());
            Engenheiro logged = (Engenheiro) engenheiro.get();
            obra.setEngenheiro(logged);
            obra.setEndereco(endereco);
            enderecoService.saveEndereco(endereco);
            obraService.saveObra(obra);
            for (Maquina maquina : form.getMaquinas()) {
                maquina.setObra(obra);
                maquinaService.saveMaquina(maquina);
            }
            for (Ferramenta ferramenta : form2.getFerramentas()) {
                ferramenta.setObra(obra);
                ferramentaService.saveFerramenta(ferramenta);
            }
            redirectAttributes.addAttribute("messageSucess", "Obra salva com sucesso!");
            return this.showCreateForm();
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/logout/{id}")
    public ModelAndView logout(@PathVariable Integer id,
                               RedirectAttributes redirectAttributes) {
        LogadoEstatico.desconectar();
        redirectAttributes.addFlashAttribute("aditionalMessage", "Você acabou de sair de sua conta.");
        return new ModelAndView("redirect:/login");
    }
}
