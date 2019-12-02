package com.arquienge.controller;

import com.arquienge.config.AdminEstatico;
import com.arquienge.model.Administrador;
import com.arquienge.model.Endereco;
import com.arquienge.model.Proprietario;
import com.arquienge.service.AdministradorService;
import com.arquienge.service.EnderecoService;
import com.arquienge.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private final ProprietarioService proprietarioService;
    @Autowired
    private final EnderecoService enderecoService;
    @Autowired
    private final AdministradorService administradorService;

    public AdminController(ProprietarioService proprietarioService, EnderecoService enderecoService, AdministradorService administradorService) {
        this.proprietarioService = proprietarioService;
        this.enderecoService = enderecoService;
        this.administradorService = administradorService;
    }


    @GetMapping("admin/cadastro")
    public ModelAndView viewFormTest(Proprietario proprietario, Endereco endereco) {
        if (AdminEstatico.getNome() != null) {
            if (AdminEstatico.getId() != 0 && AdminEstatico.getUsuario() != null) {
                ModelAndView view = new ModelAndView("admin/cadastro-proprietario.html");
                view.addObject("Endereco", endereco);
                view.addObject("Proprietario", proprietario);
                return view;
            }
        }
        return new ModelAndView("redirect:/admin/principal");
    }

    @GetMapping("admin/principal")
    public ModelAndView viewIndexAdmin(Administrador admin) {
        if (AdminEstatico.getSenha() != null) {
            if (AdminEstatico.getId() != 0 && AdminEstatico.getUsuario() != null && AdminEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("admin/admin.html");
                view.addObject("Admin", admin);
                return view;
            }
        }
        return new ModelAndView("redirect:/login/admin");

    }

    @PostMapping("admin/cadastro")
    public Object register(@Valid Proprietario proprietario, @Valid Endereco endereco, BindingResult result, RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageFailure", "Erro ao cadastrar proprietário!");
            return new ModelAndView("admin/cadastro-proprietario").addObject("Proprietario", proprietario).addObject("Endereco", endereco);
        }
        if(proprietarioService.findProprietarioByEmail(proprietario.getEmail()) != null){
           redirectAttributes.addFlashAttribute("messageFailure", "Proprietário com este email já está cadastrado!");
           return new ModelAndView("redirect:/admin/cadastro");
        }
        enderecoService.saveEndereco(endereco);
        proprietario.setEndereco(endereco);
        proprietarioService.saveProprietario(proprietario);
        redirectAttributes.addFlashAttribute("messageSucess", "Proprietário cadastrado com sucesso!");
        return "redirect:/admin/principal";
    }

    @GetMapping("login/admin")
    public ModelAndView viewLoginAdmin() {
        if (AdminEstatico.getUsuario() != null && AdminEstatico.getSenha() != null) {
            if (AdminEstatico.getId().equals(1)) {
                return new ModelAndView("redirect:/admin/principal");
            }
        }
        Administrador admin = new Administrador();
        ModelAndView view = new ModelAndView("admin/login-admin.html");
        view.addObject("admin", admin);
        return view;
    }

    @PostMapping("login/admin")
    public Object login(@Valid Administrador admin, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("messageFailure", "Usuário ou senha incorretos.");
            return new ModelAndView("redirect:/login/admin");
        }
        Administrador adminlogin = administradorService.getByUsuarioAndSenha(admin.getUsuario(), admin.getSenha());
        if (adminlogin != null) {
            AdminEstatico.setAdminLogado(adminlogin);
            redirectAttributes.addFlashAttribute("messageSucess", "Bem vindo Administrador");
            return "redirect:/admin/principal";
        }
        redirectAttributes.addFlashAttribute("messageFailure", "Usuário ou senha incorretos.");
        return new ModelAndView("redirect:/login/admin");

    }

    @GetMapping("logout/admin")
    public ModelAndView logoutAdmin(RedirectAttributes redirectAttributes) {
        AdminEstatico.desconectar();
        redirectAttributes.addFlashAttribute("messageAditional", "Você acabou de sair de sua conta.");
        return new ModelAndView("redirect:/login/admin");
    }

    @GetMapping("admin/consultar")
    public ModelAndView consultaProprietarios(RedirectAttributes redirectAttributes) {
        if (AdminEstatico.getSenha() != null) {
            if (AdminEstatico.getId() != 0 && AdminEstatico.getUsuario() != null && AdminEstatico.getNome() != null) {
                ModelAndView view = new ModelAndView("admin/consultar");
                List<Proprietario> proprietarios = proprietarioService.selectAll();
                view.addObject("proprietarios", proprietarios);
                return view;
            }
        }
        return new ModelAndView("redirect:/login/admin");
    }

    @GetMapping(value = "admin/consultar/{id}")
    public ModelAndView detalhesUsuario(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("admin/detalhes_proprietario");
        view.addObject("proprietario", proprietarioService.findProprietarioById(id));
        return view;
    }
}
