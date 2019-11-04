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
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Autowired
    private FerramentaRepository ferramentaRepository;

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
        Optional<Endereco> endereco1 = enderecoRepository.findById(endereco.getId());
        Assert.assertNotNull(endereco1);
        Assert.assertEquals(endereco.getCidade(), endereco1.get().getCidade());
        if(endereco.getCidade() == endereco1.get().getCidade())
        {
            System.out.println("Endereço Recuperado do BD igual ao salvo Localmente!");
        }
        else{
            System.out.println("Endereço Recuperado Inválido!");
        }
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
        obra.setId_obra(1);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        List<Obra> obradb = obraRepository.findObrasByDatadeInicio(date);
        Engenheiro engenheirodb = engenheiroRepository.findEngenheiroByNomeAndSobrenome("Enzo", "Klin");
        Endereco enderecodb = enderecoRepository.findEnderecoByObraId(obradb.get(0).getId_obra());

        Assert.assertNotNull("Obra encontrada no banco de dados!", obradb);
        if (obradb != null && engenheirodb != null && enderecodb != null) {
            System.out.print("Obra encontrada no banco de dados!");
            System.out.print("Engenheiro encontrado no banco de dados!");
            System.out.println("Endereço encontrado no banco de dados!");
        } else {
            System.out.println("Algum Objeto do Banco de Dados não foi encontrado!");
        }

        Assert.assertNotNull("Engenheiro encontrado no banco de dados!", engenheirodb);
        Assert.assertNotNull("Endereço encontrado no banco de dados!", enderecodb);
        Assert.assertEquals("Endereço do Banco de dados e do objeto instanciado localmente são iguais!", endereco.getCidade(), enderecodb.getCidade());
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
        if (funcionariodb.get().getSenha() == funcionario.getSenha() && funcionariodb.get().getEmail() == funcionario.getEmail()) {
            System.out.print("Bem vindo ao Sistema do Arquienge");
        } else {
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
        if (funcionariodb.getSenha() == funcionario.getSenha() && funcionariodb.getEmail() == funcionario.getEmail()) {
            System.out.print("Bem vindo ao Sistema do Arquienge");
        } else {
            System.out.print("Credenciais Inválidas!");
        }
    }

    @Test
    void buscarCarteiradoFuncionario() {
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

        Funcionario funcionariodb = funcionarioRepository.findFuncionarioByCpf(funcionario.getCpf());
        CarteiradeTrabalho carteiradb = carteiradeTrabalhoRepository.findCarteiradeTrabalhoByIdCarteiradoFuncionario(funcionariodb.getCarteira().getCod_carteira());
        System.out.println("Carteira de Número: " + carteiradb.getNumero_carteira() + " Encontrada, Ela pertence ao Funcionários: " + funcionariodb.getNome() + " " + funcionariodb.getSobrenome());
    }

    @Test
    void buscarMaquinasObra() {
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

        Maquina maquina = new Maquina(1, "CAT", 10, "Trator", "MMX-8088", obra);
        Maquina maquina2 = new Maquina(2, "CAT", 10, "Retroescavadeira", "MMX-9099", obra);
        Maquina maquina3 = new Maquina(3, "CAT", 10, "Patrola", "MMX-9838", obra);

        maquinaRepository.save(maquina);
        maquinaRepository.save(maquina2);
        maquinaRepository.save(maquina3);

        Optional<Obra> obradb = obraRepository.findById(obra.getId_obra());

        List<Maquina> maquinasdb = maquinaRepository.findMaquinasByObraId(obradb.get().getId_obra());
        Assert.assertNotNull(maquinasdb);

        if (maquinasdb.get(0) != null) {
            System.out.println("Máquinas Vinculadas a Obra Encontradas com Sucesso!");
        } else {
            System.out.println("Máquinas Vinculadas a Obra não Encontradas!");
        }
    }

    @Test
    void UpdateEngenheiro() {
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

        Engenheiro engenheirodb = engenheiroRepository.findEngenheiroByNomeAndSobrenome("Enzo", "Klin");
        engenheirodb.setNome("Lucas");
        engenheirodb.setSobrenome("Bueno");
        engenheirodb.setEmail("lucasbueno@gmail.com");
        engenheirodb.setSenha("lucas0908");
        engenheiroRepository.save(engenheirodb);

        Optional<Engenheiro> engenheiro1 = engenheiroRepository.findById(engenheirodb.getId_engenheiro());
        Assert.assertEquals(engenheiro1.get().getId_engenheiro(), engenheirodb.getId_engenheiro());
        if (engenheiro1.get().getId_engenheiro() == engenheirodb.getId_engenheiro()) {
            System.out.println("Dados de Engenheiro alterados com Sucesso!");
        }
    }

    @Test
    void searchFerramentasObra() {
        Obra obra = obraRepository.findObraByName("Obra do Shopping");
        Engenheiro engenheiro = engenheiroRepository.findEngenheiroByNomeAndSobrenome("Lucas", "Bueno");
        Endereco endereco = enderecoRepository.findEnderecoByObraId(obra.getId_obra());
        Ferramenta ferramenta = new Ferramenta(1, "Alicate", 10, obra);
        Ferramenta ferramenta1 = new Ferramenta(2, "Betoneira", 10, obra);
        Ferramenta ferramenta2 = new Ferramenta(3, "Chave de Fenda", 5, obra);

        ferramentaRepository.save(ferramenta);
        ferramentaRepository.save(ferramenta1);
        ferramentaRepository.save(ferramenta2);

        List<Ferramenta> ferramentas = ferramentaRepository.findFerramentasByObraId(obra.getId_obra());
        System.out.println(ferramentas.toString());
    }
}