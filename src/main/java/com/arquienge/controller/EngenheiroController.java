package com.arquienge.controller;

import com.arquienge.DTO.FerramentasDto;
import com.arquienge.DTO.MaquinasDto;
import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.*;
import com.arquienge.service.*;
import jdk.internal.org.objectweb.asm.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @Autowired
    private final FuncionarioService funcionarioService;
    @Autowired
    private final CarteiradeTrabalhoService carteiradeTrabalhoService;


    public EngenheiroController(EngenheiroService engenheiroService, EnderecoService enderecoService, MaquinaService maquinaService, ObraService obraService, FerramentaService ferramentaService, ProprietarioService proprietarioService, FuncionarioService funcionarioService, CarteiradeTrabalhoService carteiradeTrabalhoService) {
        this.engenheiroService = engenheiroService;
        this.maquinaService = maquinaService;
        this.enderecoService = enderecoService;
        this.obraService = obraService;
        this.ferramentaService = ferramentaService;
        this.proprietarioService = proprietarioService;
        this.funcionarioService = funcionarioService;
        this.carteiradeTrabalhoService = carteiradeTrabalhoService;
    }

    /* MAPEAMENTOS DE LOGIN E INDEX */

    @GetMapping("/login")
    public ModelAndView viewLoginScreen() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.getId() != 0 && LogadoEstatico.prop) {
                return new ModelAndView("redirect:/index");
            } else if (LogadoEstatico.getId() != 0 && LogadoEstatico.prop == false) {
                return new ModelAndView("redirect:/index");
            }
        }
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
            if (success) {
                Engenheiro logado = engenheiroService.findEngenheiroByEmailandSenha(engenheiro.getEmail(), engenheiro.getSenha());
                LogadoEstatico.setEngenheiroLogado(logado);
            }
        } else if (type.equals("proprietario")) {
            for (Proprietario p : proprietarioService.selectAll()) {
                if (proprietario.getEmail().equals(p.getEmail()) && proprietario.getSenha().equals(p.getSenha())) {
                    success = true;
                    break;
                }
            }
            if (success) {
                Proprietario logado = proprietarioService.findProprietarioByEmaileSenha(proprietario.getEmail(), proprietario.getSenha());
                LogadoEstatico.setProprietarioLogado(logado);
            }
        }

        if (!success) {
            redirectAttributes.addFlashAttribute("messageFailure", "Email ou senha incorretos");
            return "redirect:/login";
        }
        return viewIndexScreenRedirect();
    }

    @GetMapping("/index")
    public ModelAndView viewIndexScreen() {
        if (LogadoEstatico.getEmail() != null) {
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

    @GetMapping("redirect:/index")
    public RedirectView viewIndexScreenRedirect() {
        RedirectView view = new RedirectView("/index");
        return view;
    }

    @GetMapping(value = {"/entrar", "/login/", "/registrar",})
    public RedirectView viewLoginScreenRedirect(){
        if(LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.getId() != 0) {
                RedirectView view = new RedirectView("/index");
                view.addStaticAttribute("engenheiro", engenheiroService.findEngenheiroById(LogadoEstatico.getId()));
                return view;
            }
        }
            RedirectView view = new RedirectView("/login");
        return view;
    }



    @GetMapping(value = "/logout/{id}")
    public ModelAndView logout(@PathVariable Integer id,
                               RedirectAttributes redirectAttributes) {
        if(id.equals(LogadoEstatico.getId())) {
            LogadoEstatico.desconectar();
            redirectAttributes.addFlashAttribute("messageAditional", "Você acabou de sair de sua conta.");
            return new ModelAndView("redirect:/login");
        }
        LogadoEstatico.desconectar();
        return new ModelAndView("redirect:/login");
    }


    /* MAPEAMENTOS DE CADASTROS */

    /* ================= CADASTRO DE ENGENHEIROS ================== */

    @GetMapping("/cadastro/engenheiro")
    public ModelAndView viewRegisterScreen(Engenheiro engenheiro, Endereco endereco) {
        ModelAndView view = new ModelAndView("testes/engenheiro");
        view.addObject("Engenheiro", engenheiro);
        view.addObject("Endereco", endereco);
        return view;
    }

    @PostMapping("/cadastro/engenheiro")
    public Object register(@Valid Engenheiro engenheiro, @Valid Endereco endereco, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return this.viewRegisterScreen(engenheiro, endereco);
        }
        enderecoService.saveEndereco(endereco);
        engenheiro.setEndereco(endereco);
        engenheiroService.saveEngenheiro(engenheiro);
        return "redirect:/login";
    }

    /*   ================== CADASTRO DE OBRAS ================== */

    @GetMapping("/cadastro/obra")
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
                Engenheiro engenheiro = engenheiroService.findEngenheiroById(logado.getId());
                List<Funcionario> funcionarios = funcionarioService.findFuncionariosByEngenheiro(engenheiro);
                view.addObject("funcionarios", funcionarios);
                view.addObject("engenheiro", engenheiro);
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

    @PostMapping("/cadastro/obra")
    public Object createObra(@ModelAttribute MaquinasDto form, @ModelAttribute FerramentasDto form2, Obra obra, Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageFailure", "Obra não pode ser cadastrada!");
            return "redirect:/cadastro/obra";
        }

        Logado logado = new Logado(false);
        if (LogadoEstatico.getId() != 0 && LogadoEstatico.prop == false) {
            Optional<Engenheiro> engenheiro = engenheiroService.selectById(logado.getId());
            Engenheiro logged = (Engenheiro) engenheiro.get();
            obra.setEngenheiro(logged);
            obra.setEndereco(endereco);
            if (obraService.selectObrabyName(obra.getNome_obra()) != null) {
                redirectAttributes.addFlashAttribute("messageError", "Uma obra com este nome já está cadastrada.");
                return "redirect:/cadastro/obra";
            } else {
                enderecoService.saveEndereco(endereco);
                obraService.saveObra(obra);
                System.out.println(obra.getFuncionarios().get(0).getCargo());
                for (Maquina maquina : form.getMaquinas()) {
                    maquina.setObra(obra);
                    maquinaService.saveMaquina(maquina);
                }
                for (Ferramenta ferramenta : form2.getFerramentas()) {
                    ferramenta.setObra(obra);
                    ferramentaService.saveFerramenta(ferramenta);
                }
                for (Funcionario funcionario : obra.getFuncionarios()) {
                    if (funcionario.getObra() != null) {
                        obraService.deleteObra(obra);
                        enderecoService.deleteEndereco(endereco);
                        funcionarioService.saveFuncionario(funcionario);
                        redirectAttributes.addFlashAttribute("messageError", "Um dos funcionários selecionados já está trabalhando em outra obra.");
                    } else {
                        funcionario.setObra(obra);
                        funcionarioService.saveFuncionario(funcionario);
                    }
                }
                redirectAttributes.addFlashAttribute("messageSucess", "Obra salva com sucesso!");
                return "redirect:/cadastro/obra";
            }
        }
        return "redirect:/login";
    }

    /* ============ CADASTRO DE FUNCIONÁRIOS ============ */

    @GetMapping("/cadastro/funcionario")
    public ModelAndView cadastroFuncionario() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.prop != true && LogadoEstatico.getId() != 0) {
                CarteiradeTrabalho carteiradeTrabalho = new CarteiradeTrabalho();
                Funcionario funcionario = new Funcionario();
                Endereco endereco = new Endereco();
                ModelAndView view = new ModelAndView("registrar/funcionario");
                view.addObject("funcionario", funcionario);
                view.addObject("carteira", carteiradeTrabalho);
                view.addObject("endereco", endereco);
                view.addObject("engenheiro", engenheiroService.findEngenheiroById(LogadoEstatico.getId()));
                return view;
            } else if (LogadoEstatico.prop && LogadoEstatico.getId() != 0) {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/cadastro/funcionario")
    public Object createFuncionario(Funcionario funcionario, Endereco endereco, CarteiradeTrabalho carteiradeTrabalho) {
        enderecoService.saveEndereco(endereco);
        funcionario.setEndereco(endereco);
        carteiradeTrabalhoService.saveCarteira(carteiradeTrabalho);
        funcionario.setCarteira(carteiradeTrabalho);
        Optional<Engenheiro> engenheiro = engenheiroService.selectById(LogadoEstatico.getId());
        Engenheiro logado = (Engenheiro) engenheiro.get();
        funcionario.setEngenheiro(logado);
        funcionarioService.saveFuncionario(funcionario);
        return "redirect:/cadastro/funcionario";

    }

    /* MAPEAMENTOS DE CONSULTAS */

    /* ============== CONSULTAR FUNCIONÁRIOS ============== */

    @GetMapping("/consulta/funcionarios")
    public ModelAndView consultaFuncionarios(RedirectAttributes redirectAttributes) {
        if (LogadoEstatico.getEmail() != null) {
            ModelAndView view = new ModelAndView("consultar-funcionarios");
            Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
            List<Funcionario> funcionarios = funcionarioService.findFuncionariosByEngenheiro(logado);
            view.addObject("funcionarios", funcionarios);
            view.addObject("engenheiro", logado);
            return view;
        }
        return new ModelAndView("consultar-funcionarios");
    }

    /* MAPEAMENTOS DE EDIÇÃO E DETALHES */

    /* ============= EDIÇÃO E DETALHES DE UM FUNCIONÁRIO ============== */

    @GetMapping("/funcionario/detalhes/{id}")
    public ModelAndView detalhesUsuario(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("detalhes_funcionario");
        view.addObject("funcionario", funcionarioService.findFuncionarioById(id));
        view.addObject("engenheiro", engenheiroService.findEngenheiroById(LogadoEstatico.getId()));
        return view;
    }


}
