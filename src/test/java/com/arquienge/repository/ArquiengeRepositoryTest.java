package com.arquienge.repository;

import com.arquienge.ArquiengeApplication;
import com.arquienge.model.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArquiengeApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ArquiengeRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private EngenheiroRepository engenheiroRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CarteiradeTrabalhoRepository carteiradeTrabalhoRepository;

    @Test
    void insertEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Tal");
        endereco.setNumero(89);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);
        Optional<Endereco> endereco1 = enderecoRepository.findById(1);
        Assert.assertNotNull(endereco1);
        Assert.assertEquals("Rua Tal", endereco1.get().getRua());
        System.out.print(endereco1.get().getRua());
    }

    @Test
    void findByCep() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Tal");
        endereco.setNumero(89);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);
        List<Endereco> endereco1 = enderecoRepository.findByCep("88058-490");
        Assert.assertNotNull(endereco1);
        Assert.assertEquals(endereco.getCidade(), endereco1.get(0).getCidade());
        System.out.println(endereco1.get(0).getCep());
    }

    @Test
    void findEnderecoByObraId() {

        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Tal");
        endereco.setNumero(89);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(1);
        engenheiro.setEmail("enzo.klin@gmail.com");
        engenheiro.setCpf("122.148.189-03");
        Date date = new Date(2019, 10, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Enzo");
        engenheiro.setSobrenome("Klin");
        engenheiro.setSenha("enzo2008");
        engenheiro.setTelefone("48 99925-5250");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra do Shopping");
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        List<Obra> obradb = obraRepository.findObrasByDatadeInicio(date);
        Engenheiro engenheirodb = engenheiroRepository.findEngenheiroByNomeAndSobrenome("Enzo", "Klin");
        Endereco enderecodb = enderecoRepository.findEnderecoByObraId(obradb.get(0).getId_obra());

        Assert.assertNotNull(obradb);
        Assert.assertNotNull(engenheirodb);
        Assert.assertNotNull(enderecodb);
        Assert.assertEquals(endereco.getCidade(), enderecodb.getCidade());
    }

    @Test
    void cadastrarFuncionarioeLogarCorretamente() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Tal");
        endereco.setNumero(89);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(1);
        engenheiro.setEmail("enzo.klin@gmail.com");
        engenheiro.setCpf("122.148.189-03");
        Date date = new Date(2019, 10, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Enzo");
        engenheiro.setSobrenome("Klin");
        engenheiro.setSenha("enzo2008");
        engenheiro.setTelefone("48 99925-5250");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra do Shopping");
        obra.setId_obra(1);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(1);
        carteira.setNumero_carteira("4994884");
        carteira.setPis("393.40400.22-1");
        carteira.setSerie("320-1");
        carteira.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Pintor");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(1);
        funcionario.setObra(obra);
        funcionario.setCpf("448.459.322-49");
        funcionario.setEmail("enzo.klin@gmail.com");
        funcionario.setNascimento(date);
        funcionario.setNome("Enzo");
        funcionario.setSenha("enzo2008");
        funcionario.setSobrenome("Klin");
        funcionario.setTelefone("48 99925-5250");
        funcionario.setSalario(2000.00);
        funcionario.setCarteira(carteira);
        funcionarioRepository.save(funcionario);

       Optional<Funcionario> funcionariodb = funcionarioRepository.findById(funcionario.getId());
        Assert.assertTrue("Bem vindo ao Arquienge!", funcionariodb.get().getSenha().equals(funcionario.getSenha()));
        if(funcionariodb.get().getSenha() == funcionario.getSenha() && funcionariodb.get().getEmail() == funcionario.getEmail())
        {
            System.out.print("Bem vindo ao Sistema do Arquienge");
        }
        else{
            System.out.print("Credenciais Inválidas!");
        }
    }

    @Test
    void cadastrarFuncionarioELogarIncorretamente() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setRua("Rua Tal");
        endereco.setNumero(89);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(1);
        engenheiro.setEmail("enzo.klin@gmail.com");
        engenheiro.setCpf("122.148.189-03");
        Date date = new Date(2019, 10, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Enzo");
        engenheiro.setSobrenome("Klin");
        engenheiro.setSenha("enzo2008");
        engenheiro.setTelefone("48 99925-5250");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra do Shopping");
        obra.setId_obra(1);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(1);
        carteira.setNumero_carteira("4994884");
        carteira.setPis("393.40400.22-1");
        carteira.setSerie("320-1");
        carteira.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Pintor");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(1);
        funcionario.setObra(obra);
        funcionario.setCpf("448.459.322-49");
        funcionario.setEmail("enzo.klin@gmail.com");
        funcionario.setNascimento(date);
        funcionario.setNome("Enzo");
        funcionario.setSenha("enzo2008");
        funcionario.setSobrenome("Klin");
        funcionario.setTelefone("48 99925-5250");
        funcionario.setSalario(2000.00);
        funcionario.setCarteira(carteira);
        funcionarioRepository.save(funcionario);

       Funcionario funcionariodb = new Funcionario();
        funcionariodb.setCargo("Pintor");
        funcionariodb.setDt_admissao(date);
        funcionariodb.setEndereco(endereco);
        funcionariodb.setId(1);
        funcionariodb.setObra(obra);
        funcionariodb.setCpf("448.459.322-49");
        funcionariodb.setEmail("enzo.klin3@gmail.com");
        funcionariodb.setNascimento(date);
        funcionariodb.setNome("Enzo");
        funcionariodb.setSenha("enzo2009");
        funcionariodb.setSobrenome("Klin");
        funcionariodb.setTelefone("48 99925-5250");
        funcionariodb.setSalario(2000.00);
        funcionariodb.setCarteira(carteira);

        Assert.assertFalse("Credenciais Incorretas!", funcionariodb.getSenha().equals(funcionario.getSenha()));
        if(funcionariodb.getSenha() == funcionario.getSenha() && funcionariodb.getEmail() == funcionario.getEmail())
        {
            System.out.print("Bem vindo ao Sistema do Arquienge");
        }
        else{
            System.out.print("Credenciais Inválidas!");
        }
    }

    @Test
    void buscarCarteiradoFuncionario () {
    }

    @Test
    void buscarMaquinasObra () {
    }
}