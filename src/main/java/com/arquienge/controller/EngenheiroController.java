package com.arquienge.controller;

import com.arquienge.DTO.FerramentasDto;
import com.arquienge.DTO.FerramentasUsadasDto;
import com.arquienge.DTO.MaquinasDto;
import com.arquienge.DTO.MaquinasUsadasDto;
import com.arquienge.config.Logado;
import com.arquienge.config.LogadoEstatico;
import com.arquienge.model.*;
import com.arquienge.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class EngenheiroController {

    private final EngenheiroService engenheiroService;
    private final EnderecoService enderecoService;
    private final MaquinaService maquinaService;
    private final FerramentaService ferramentaService;
    private final ObraService obraService;
    private final ProprietarioService proprietarioService;
    private final FuncionarioService funcionarioService;
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

    @GetMapping(value = {"/", "/inicio", "/home"})
    public ModelAndView viewInicioScreen() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.getId() != 0 && LogadoEstatico.prop) {
                return new ModelAndView("redirect:/index");
            } else if (LogadoEstatico.getId() != 0 && !LogadoEstatico.prop) {
                return new ModelAndView("redirect:/index");
            }
        }
        ModelAndView view = new ModelAndView("index2");
        return view;
    }

    @GetMapping("/login")
    public ModelAndView viewLoginScreen() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.getId() != 0 && LogadoEstatico.prop) {
                return new ModelAndView("redirect:/index");
            } else if (LogadoEstatico.getId() != 0 && !LogadoEstatico.prop) {
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
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/login");
        }
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
            if (!LogadoEstatico.prop && LogadoEstatico.getId() != 0) {
                Logado logado = new Logado(false);
                Optional<Engenheiro> logged = engenheiroService.selectById(logado.getId());
                Engenheiro engenheiro = logged.get();
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
    public RedirectView viewLoginScreenRedirect() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.getId() != 0) {
                RedirectView view = new RedirectView("/index");
                view.addStaticAttribute("engenheiro", engenheiroService.findEngenheiroById(LogadoEstatico.getId()));
                return view;
            }
        }
        return new RedirectView("/login");
    }


    @GetMapping(value = "/logout/{id}")
    public ModelAndView logout(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        LogadoEstatico.desconectar();
        redirectAttributes.addFlashAttribute("messageAditional", "Você acabou de sair de sua conta.");
        return new ModelAndView("redirect:/login");
    }


    /* MAPEAMENTOS DE CADASTROS */


    /*   ================== CADASTRO DE OBRAS ================== */

    @GetMapping("/cadastro/obra")
    public ModelAndView showCreateForm() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getId() != 0) {
                ModelAndView view = new ModelAndView("registrar/obra");
                MaquinasDto maquinasDto = new MaquinasDto();
                FerramentasDto ferramentasDto = new FerramentasDto();
                List<Maquina> maquinas = new ArrayList<>();
                List<Ferramenta> ferramentas = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    maquinas.add(new Maquina());
                    maquinas.get(i - 1).setCod_maquina(i);
                    ferramentas.add(new Ferramenta());
                    ferramentas.get(i - 1).setCod_ferramenta(i);
                }
                ferramentasDto.setFerramentas(ferramentas);
                maquinasDto.setMaquinas(maquinas);
                Obra obra = new Obra();
                Endereco endereco = new Endereco();
                Logado logado = new Logado(false);
                Engenheiro engenheiro = engenheiroService.findEngenheiroById(logado.getId());
                List<Funcionario> funcionarios = funcionarioService.findFuncionariosByEngenheiro(engenheiro.getId_engenheiro());
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
        if (LogadoEstatico.getId() != 0 && !LogadoEstatico.prop) {
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
                for (Maquina maquina : form.getMachines()) {
                    maquina.setObra(obra);
                    maquinaService.saveMaquina(maquina);
                    for (Ferramenta ferramenta : form2.getFerramentas()) {
                        ferramenta.setObra(obra);
                        ferramentaService.saveFerramenta(ferramenta);
                    }
                }
                    for (Funcionario funcionario : obra.getFuncionarios()) {
                        if (funcionario.getObra() != null) {
                            obraService.deleteObra(obra);
                            enderecoService.deleteEndereco(endereco);
                            redirectAttributes.addFlashAttribute("messageError", "Um dos funcionários selecionados já está trabalhando em outra obra.");
                        } else {
                            funcionario.setObra(obra);
                            funcionarioService.saveFuncionario(funcionario);
                        }
                    }
                redirectAttributes.addFlashAttribute("messageSucess", "Obra salva com sucesso!");
                return new ModelAndView("redirect:/cadastro/obra");
            }
        }
        return "redirect:/login";
    }

    /* ============ CADASTRO DE FUNCIONÁRIOS ============ */

    /* MAPEAMENTOS DE CONSULTAS */

    /* ============== CONSULTAR FUNCIONÁRIOS ============== */

    @GetMapping("/consulta/funcionarios")
    public ModelAndView consultaFuncionarios() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("consultar-funcionarios");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                List<Funcionario> funcionarios = funcionarioService.findFuncionariosByEngenheiro(logado.getId_engenheiro());
                view.addObject("funcionarios", funcionarios);
                view.addObject("engenheiro", logado);
                return view;
            } else {
                ModelAndView view = new ModelAndView("consultar-funcionarios");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                List<Funcionario> funcionarios = funcionarioService.findFuncionariosByProprietario(logado);
                view.addObject("funcionarios", funcionarios);
                view.addObject("proprietario", logado);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");
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

    @GetMapping(value = "/obra/detalhes/{idString}")
    public ModelAndView visualizarObra(@PathVariable String idString, RedirectAttributes redirectAttributes){
        Integer id = Integer.valueOf(idString);
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("editar-obras");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                Obra obra = obraService.selectObrabyId_obra(id);
                String dtInicio = new SimpleDateFormat("dd/MM/yyyy")
                        .format(obra.getDt_inicio());
                String dtEntrega = new SimpleDateFormat("dd/MM/yyyy").format(obra.getDt_entrega());
                view.addObject("engenheiro", logado);
                view.addObject("obra", obra);
                view.addObject("dataInicio", dtInicio);
                view.addObject("dataEntrega", dtEntrega);
                return view;
            } else {
                ModelAndView view = new ModelAndView("editar-obras");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                Obra obra = obraService.selectObrabyId_obra(id);
                view.addObject("proprietario", logado);
                view.addObject("obra", obra);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");
    }


    @GetMapping("/cadastro/opcoes")
    public ModelAndView opcoesCadastro() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("registrar/opcoes");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                view.addObject("engenheiro", logado);
                return view;
            } else {
                ModelAndView view = new ModelAndView("registrar/opcoes");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                view.addObject("proprietario", logado);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");

    }

    @GetMapping("/consulta/obras")
    public ModelAndView visualizarObras() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("obras");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                List<Obra> obras = obraService.selectByEngenheiro(logado.getId_engenheiro());
                view.addObject("engenheiro", logado);
                view.addObject("obras", obras);
                return view;
            } else {
                ModelAndView view = new ModelAndView("obras");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                List<Obra> obras = obraService.findObraByProprietario(logado.getId_proprietario());
                view.addObject("proprietario", logado);
                view.addObject("obras", obras);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");

    }

    @GetMapping(value = "/cadastro/diario/{id}")
    public ModelAndView createDiario(@PathVariable Integer id) {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getId() != 0) {
                ModelAndView view = new ModelAndView("registrar/diario-de-obra.html");
                Obra obra = obraService.selectObrabyId_obra(id);
                Logado logado = new Logado(false);
                List<Presenca> presencas = new ArrayList<>();
                List<MaquinasUsadas> maquinasUsadas = new ArrayList<>();
                List<FerramentasUsadas> ferramentasUsadas = new ArrayList<>();
                for (Funcionario funcionario : obra.getFuncionarios()) {
                    presencas.add(new Presenca());
                    for (Ferramenta ferramenta : obra.getFerramentas()) {
                        FerramentasUsadas ferramentasUsadas1 = new FerramentasUsadas();
                        ferramentasUsadas1.setFerramenta(ferramenta);
                        ferramentasUsadas.add(ferramentasUsadas1);
                    }
                    for (Maquina maquina : obra.getMaquinas()) {
                        MaquinasUsadas maquinasUsadas1 = new MaquinasUsadas();
                        maquinasUsadas1.setMaquina(maquina);
                        maquinasUsadas.add(new MaquinasUsadas());
                    }
                }
                MaquinasUsadasDto maquinasUsadasDto = new MaquinasUsadasDto(maquinasUsadas);
                FerramentasUsadasDto ferramentasUsadasDto = new FerramentasUsadasDto(ferramentasUsadas);
                Engenheiro engenheiro = engenheiroService.findEngenheiroById(logado.getId());
                view.addObject("presencas", presencas);
                view.addObject("engenheiro", engenheiro);
                view.addObject("obra", obra);
                view.addObject("form", maquinasUsadasDto);
                view.addObject("form2", ferramentasUsadasDto);
                view.addObject("diariodeobra", new DiarioDeObra());
                return view;
            } else if (LogadoEstatico.prop && LogadoEstatico.getId() != null) {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping(value = {"/cadastro/ferramenta/", "/cadastro/ferramenta"})
    public ModelAndView createFerramenta() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("registrar/ferramenta");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                List<Obra> obras = obraService.selectByEngenheiro(logado.getId_engenheiro());
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setCod_ferramenta(null);
                view.addObject("engenheiro", logado);
                view.addObject("obras", obras);
                view.addObject("ferramenta", ferramenta);
                return view;
            } else {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/cadastro/ferramenta")
    public Object cadastrarFerramenta(Ferramenta ferramenta, Obra obra, BindingResult result, RedirectAttributes redirectAttributes) {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                if(ferramenta != null) {
                    ferramenta.setObra(obra);
                    ferramentaService.saveFerramenta(ferramenta);
                }
                return new ModelAndView("redirect:/cadastro/ferramenta");
            } else {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/consulta/engenheiros")
    public ModelAndView consultaEngenheiros() {
        if (LogadoEstatico.getEmail() != null) {
            if (LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("proprietario/consultar-engenheiros");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                List<Engenheiro> engenheiros = engenheiroService.findEngenheirosByProprietario(logado.getId_proprietario());
                view.addObject("engenheiros", engenheiros);
                view.addObject("proprietario", logado);
                return view;
            } else {
                ModelAndView view = new ModelAndView("redirect:/index");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                view.addObject("proprietario", logado);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/consulta/opcoes")
    public ModelAndView opcoesConsulta() {
        if (LogadoEstatico.getEmail() != null) {
            if (!LogadoEstatico.prop && LogadoEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("opcoes_consulta");
                Engenheiro logado = engenheiroService.findEngenheiroById(LogadoEstatico.getId());
                view.addObject("engenheiro", logado);
                return view;
            } else {
                ModelAndView view = new ModelAndView("opcoes_consulta");
                Proprietario logado = proprietarioService.findProprietarioById(LogadoEstatico.getId());
                view.addObject("proprietario", logado);
                return view;
            }
        }
        return new ModelAndView("redirect:/login");

    }


}
