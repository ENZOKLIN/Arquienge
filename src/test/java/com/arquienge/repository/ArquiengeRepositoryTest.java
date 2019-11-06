package com.arquienge.repository;

import com.arquienge.ArquiengeApplication;
import com.arquienge.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
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

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.ArrayList;
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
        endereco.setId(null);
        endereco.setRua("Rua Tol");
        endereco.setNumero(109);
        endereco.setCidade("Floripa");
        endereco.setCep("88058-480");
        endereco.setBairro("Rio Vermelho");
        enderecoRepository.save(endereco);
        Optional<Endereco> endereco1 = enderecoRepository.findById(endereco.getId());
        Assert.assertNotNull(endereco1);
        Assert.assertEquals("Rua Tol", endereco1.get().getRua());
        System.out.print(endereco1.get().getRua());
    }

    @Test
    void findByCep() {
        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Tal");
        endereco.setNumero(899);
        endereco.setCidade("Florianopolis");
        endereco.setCep("88058-490");
        endereco.setBairro("Ingleses");
        enderecoRepository.save(endereco);
        Optional<Endereco> endereco1 = enderecoRepository.findById(endereco.getId());
        Assert.assertNotNull(endereco1);
        Assert.assertEquals(endereco.getCidade(), endereco1.get().getCidade());
        if (endereco.getCidade() == endereco1.get().getCidade()) {
            System.out.println("Endereço Recuperado do BD igual ao salvo Localmente!");
        } else {
            System.out.println("Endereço Recuperado Inválido!");
        }
    }

    @Test
    void findEnderecoByObraId() {

        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Tey");
        endereco.setNumero(99);
        endereco.setCidade("Floripa");
        endereco.setCep("88058-490");
        endereco.setBairro("Santinho");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("enzo.klin@gmail.com");
        engenheiro.setCpf("122.148.189-03");
        Date date = new Date(2020, 05, 11);
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
        obra.setNome_obra("Obra do Sanatório");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        Optional<Obra> obradb = obraRepository.findById(obra.getId_obra());
        Optional<Engenheiro> engenheirodb = engenheiroRepository.findById(engenheiro.getId_engenheiro());
        Endereco enderecodb = enderecoRepository.findEnderecoByObraId(obradb.get().getId_obra());

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
        endereco.setId(null);
        endereco.setRua("Rua Tewtew");
        endereco.setNumero(70);
        endereco.setCidade("Curitiba");
        endereco.setCep("88058-490");
        endereco.setBairro("Trindade");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("linhares@gmail.com");
        engenheiro.setCpf("133.389.399-99");
        Date date = new Date(2019, 10, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Linhares");
        engenheiro.setSobrenome("Soares");
        engenheiro.setSenha("linhares1234");
        engenheiro.setTelefone("48 99828-9399");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Vila");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(null);
        carteira.setNumero_carteira("4953884");
        carteira.setPis("393.40400.22-9");
        carteira.setSerie("420-1");
        carteira.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Pintor");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(null);
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
        endereco.setId(null);
        endereco.setRua("Rua Terra");
        endereco.setNumero(993);
        endereco.setCidade("Porto Alegre");
        endereco.setCep("88053-490");
        endereco.setBairro("Santa Maria");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("lucassoares@gmail.com");
        engenheiro.setCpf("393.384.392-39");
        Date date = new Date(2021, 11, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Lucas");
        engenheiro.setSobrenome("Soares");
        engenheiro.setSenha("lucas1234");
        engenheiro.setTelefone("48 99238-3992");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra de Teste");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(null);
        carteira.setNumero_carteira("9393883");
        carteira.setPis("182.28384.21-4");
        carteira.setSerie("393-2");
        carteira.setUf("PR");
        carteiradeTrabalhoRepository.save(carteira);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Gerente de Projetos");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(null);
        funcionario.setObra(obra);
        funcionario.setCpf("392.239.283-49");
        funcionario.setEmail("richarlisons@gmail.com");
        funcionario.setNascimento(date);
        funcionario.setNome("Linhares");
        funcionario.setSenha("linhares09");
        funcionario.setSobrenome("Silva");
        funcionario.setTelefone("48 99384-5250");
        funcionario.setSalario(2051.00);
        funcionario.setCarteira(carteira);
        funcionarioRepository.save(funcionario);

        Funcionario funcionariodb = new Funcionario();
        funcionariodb.setCargo("Gerente de Projetos");
        funcionariodb.setDt_admissao(date);
        funcionariodb.setEndereco(endereco);
        funcionariodb.setId(null);
        funcionariodb.setObra(obra);
        funcionariodb.setCpf("342.786.940-75");
        funcionariodb.setEmail("enzoandrade@gmail.com");
        funcionariodb.setNascimento(date);
        funcionariodb.setNome("Louise Patrícia");
        funcionariodb.setSenha("louise1234");
        funcionariodb.setSobrenome("Araújo");
        funcionariodb.setTelefone("81 98519-4461");
        funcionariodb.setSalario(3000.00);
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
        endereco.setId(null);
        endereco.setRua("Rua ACATE");
        endereco.setNumero(232);
        endereco.setCidade("Caxias do Sul");
        endereco.setCep("88048-490");
        endereco.setBairro("Centro");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("ssarahalanamirellarodrigues@pop.com.br");
        engenheiro.setCpf("674.180.467-66");
        Date date = new Date(2018, 9, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Sarah Alana Mirella");
        engenheiro.setSobrenome("Rodrigues");
        engenheiro.setSenha("sarah1234");
        engenheiro.setTelefone("41 99914-9168");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Ladeira");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(null);
        carteira.setNumero_carteira("9594998");
        carteira.setPis("899.77877.66-9");
        carteira.setSerie("788-5");
        carteira.setUf("RS");
        carteiradeTrabalhoRepository.save(carteira);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Empilhador");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(null);
        funcionario.setObra(obra);
        funcionario.setCpf("776.228.239-45");
        funcionario.setEmail("camilagiovannasales-71@vanguarda.tv");
        funcionario.setNascimento(date);
        funcionario.setNome("Camila Giovanna");
        funcionario.setSenha("camila1234");
        funcionario.setSobrenome("Sales");
        funcionario.setTelefone("67 99252-8605");
        funcionario.setSalario(4000.00);
        funcionario.setCarteira(carteira);
        funcionarioRepository.save(funcionario);

        Funcionario funcionariodb = funcionarioRepository.findFuncionarioByCpf(funcionario.getCpf());
        CarteiradeTrabalho carteiradb = carteiradeTrabalhoRepository.findCarteiradeTrabalhoByIdCarteiradoFuncionario(funcionariodb.getCarteira().getCod_carteira());
        System.out.println("Carteira de Número: " + carteiradb.getNumero_carteira() + " Encontrada, Ela pertence ao(a) Funcionário(a): " + funcionariodb.getNome() + " " + funcionariodb.getSobrenome());
    }

    @Test
    void buscarMaquinasObra() {
        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Diretriz");
        endereco.setNumero(343);
        endereco.setCidade("Balneário Camboriú");
        endereco.setCep("88053-345");
        endereco.setBairro("Balneário");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("ppedrobernardocortereal@trustsign.com.br");
        engenheiro.setCpf("387.754.212-32");
        Date date = new Date(2017, 11, 30);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Pedro Bernardo Corte");
        engenheiro.setSobrenome("Real");
        engenheiro.setSenha("pedro1234");
        engenheiro.setTelefone("95 99866-4692");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Passarela");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        Maquina maquina = new Maquina(null, "CAT", 10, "Trator", "MMX-8088", obra);
        Maquina maquina2 = new Maquina(null, "CAT", 10, "Retroescavadeira", "MMX-9099", obra);
        Maquina maquina3 = new Maquina(null, "CAT", 10, "Patrola", "MMX-9838", obra);

        maquinaRepository.save(maquina);
        maquinaRepository.save(maquina2);
        maquinaRepository.save(maquina3);

        Optional<Obra> obradb = obraRepository.findById(obra.getId_obra());

        List<Maquina> maquinasdb = maquinaRepository.findMaquinasByObraId(obradb.get().getId_obra());
        Assert.assertNotNull(maquinasdb);

        if (maquinasdb.get(0) != null) {
            System.out.println(maquinasdb.toString());
            System.out.println("Máquinas Vinculadas a Obra Encontradas com Sucesso!");
        } else {
            System.out.println("Máquinas Vinculadas a Obra não Encontradas!");
        }
    }

    @Test
    void UpdateEngenheiro() {
        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Terel");
        endereco.setNumero(342);
        endereco.setCidade("São José");
        endereco.setCep("88049-384");
        endereco.setBairro("SanJose");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("kaiquerenatomarcoscampos@novaface.com.br");
        engenheiro.setCpf("784.872.007-10");
        Date date = new Date(2013, 22, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Kaique Renato Marcos");
        engenheiro.setSobrenome("Campos");
        engenheiro.setSenha("kaique1234");
        engenheiro.setTelefone("24 98890-4183");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Casa de Doações");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        Engenheiro engenheirodb = engenheiroRepository.findEngenheiroByCpf("784.872.007-10");
        engenheirodb.setNome("Lucas");
        engenheirodb.setSobrenome("Bueno");
        engenheirodb.setEmail("lucasbueno@gmail.com");
        engenheirodb.setSenha("lucas0908");
        engenheiroRepository.save(engenheirodb);

        Optional<Engenheiro> engenheiro1 = engenheiroRepository.findById(engenheirodb.getId_engenheiro());
        Assert.assertEquals(engenheiro1.get().getId_engenheiro(), engenheirodb.getId_engenheiro());
        if (engenheiro1.get().getNome() == "Lucas") {
            System.out.println("Dados de Engenheiro alterados com Sucesso!");
        }
    }

    @Test
    void searchFerramentasObra() {

        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Doutor Paulo Lobo de Morais");
        endereco.setNumero(23);
        endereco.setCidade("Petrópolis");
        endereco.setCep("25655-070");
        endereco.setBairro("Valparaíso");
        enderecoRepository.save(endereco);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("kaiquerenatomarcoscampos__kaiquerenatomarcoscampos@novaface.com.br");
        engenheiro.setCpf("782.860.279-06");
        Date date = new Date(2014, 12, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Sara Natália");
        engenheiro.setSobrenome("Rezende");
        engenheiro.setSenha("sara1234");
        engenheiro.setTelefone("91 99444-6834");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Casa de Mansões");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        Ferramenta ferramenta = new Ferramenta(null, "Alicate", 10, obra);
        Ferramenta ferramenta1 = new Ferramenta(null, "Betoneira", 10, obra);
        Ferramenta ferramenta2 = new Ferramenta(null, "Chave de Fenda", 5, obra);

        ferramentaRepository.save(ferramenta);
        ferramentaRepository.save(ferramenta1);
        ferramentaRepository.save(ferramenta2);

        List<Ferramenta> ferramentas = ferramentaRepository.findFerramentasByObraId(obra.getId_obra());
        System.out.println(ferramentas.toString());
    }

    @Test
    void searchFuncionariosdeObra() {
//        ferramentaRepository.deleteAll();
//        funcionarioRepository.deleteAll();
//        maquinaRepository.deleteAll();
//        obraRepository.deleteAll();
//        engenheiroRepository.deleteAll();
//        enderecoRepository.deleteAll();
//        carteiradeTrabalhoRepository.deleteAll();


        Endereco endereco = new Endereco();
        endereco.setId(null);
        endereco.setRua("Vila Figueiredo");
        endereco.setNumero(34);
        endereco.setCidade("João Pessoa");
        endereco.setCep("58027-423");
        endereco.setBairro("Mandacaru");
        enderecoRepository.save(endereco);

        Endereco endereco2 = new Endereco();
        endereco.setId(null);
        endereco.setRua("Praça Luiz Gonzaga 47");
        endereco.setNumero(343);
        endereco.setCidade("Nossa Senhora de Lourdes");
        endereco.setCep("49890-970");
        endereco.setBairro("Centro");
        enderecoRepository.save(endereco2);

        Endereco endereco3 = new Endereco();
        endereco.setId(null);
        endereco.setRua("Rua Novo Horizonte");
        endereco.setNumero(44);
        endereco.setCidade("João Pessoa");
        endereco.setCep("57608-387");
        endereco.setBairro("Palmeira de Fora");
        enderecoRepository.save(endereco3);

        Engenheiro engenheiro = new Engenheiro();
        engenheiro.setEndereco(endereco);
        engenheiro.setId_engenheiro(null);
        engenheiro.setEmail("vvitorbernardonogueira@depotit.com.br");
        engenheiro.setCpf("869.844.265-02");
        Date date = new Date(2011, 11, 10);
        engenheiro.setNascimento(date);
        engenheiro.setNome("Vitor Bernardo");
        engenheiro.setSobrenome("Nogueira");
        engenheiro.setSenha("vitor2008");
        engenheiro.setTelefone("83 99920-6556");

        Obra obra = new Obra();
        obra.setDt_entrega(date);
        obra.setDt_inicio(date);
        obra.setEndereco(endereco);
        obra.setEngenheiro(engenheiro);
        obra.setNome_obra("Obra da Casan");
        obra.setId_obra(null);
        engenheiroRepository.save(engenheiro);
        obraRepository.save(obra);
        engenheiro.setObra(obra);

        CarteiradeTrabalho carteira = new CarteiradeTrabalho();
        carteira.setCod_carteira(null);
        carteira.setNumero_carteira("8438474");
        carteira.setPis("232.45482.44-5");
        carteira.setSerie("433-2");
        carteira.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira);

        CarteiradeTrabalho carteira2 = new CarteiradeTrabalho();
        carteira2.setCod_carteira(null);
        carteira2.setNumero_carteira("4994394");
        carteira2.setPis("392.40400.22-1");
        carteira2.setSerie("394-3");
        carteira2.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira2);

        CarteiradeTrabalho carteira3 = new CarteiradeTrabalho();
        carteira3.setCod_carteira(null);
        carteira3.setNumero_carteira("3773774");
        carteira3.setPis("332.23277.11-2");
        carteira3.setSerie("312-4");
        carteira3.setUf("SC");
        carteiradeTrabalhoRepository.save(carteira3);

        Funcionario funcionario = new Funcionario();
        funcionario.setCargo("Carregador de Tijolos");
        funcionario.setDt_admissao(date);
        funcionario.setEndereco(endereco);
        funcionario.setId(null);
        funcionario.setObra(obra);
        funcionario.setCpf("039.967.769-00");
        funcionario.setEmail("franciscaadrianaalmada__franciscaadrianaalmada@ppconsulting.com.br");
        funcionario.setNascimento(date);
        funcionario.setNome("Francisca Adriana");
        funcionario.setSenha("francisca2008");
        funcionario.setSobrenome("Almada");
        funcionario.setTelefone("31 98229-1632");
        funcionario.setSalario(3001.00);
        funcionario.setCarteira(carteira);
        funcionarioRepository.save(funcionario);


        Funcionario funcionario2 = new Funcionario();
        funcionario2.setCargo("Gerente de Projetos");
        funcionario2.setDt_admissao(date);
        funcionario2.setEndereco(endereco2);
        funcionario2.setId(null);
        funcionario2.setObra(obra);
        funcionario2.setCpf("719.107.672-04");
        funcionario2.setEmail("ssebastianamilenaassuncao@gradu.if.ufrj.br");
        funcionario2.setNascimento(date);
        funcionario2.setNome("Sebastiana Milena");
        funcionario2.setSenha("sebastiana2008");
        funcionario2.setSobrenome("Assunção");
        funcionario2.setTelefone("92 99454-6946");
        funcionario2.setSalario(5000.00);
        funcionario2.setCarteira(carteira2);
        funcionarioRepository.save(funcionario2);

        Funcionario funcionario3 = new Funcionario();
        funcionario3.setCargo("Fiscal de Obra");
        funcionario3.setDt_admissao(date);
        funcionario3.setEndereco(endereco3);
        funcionario3.setId(null);
        funcionario3.setObra(obra);
        funcionario3.setCpf("643.399.679-55");
        funcionario3.setEmail("allanaoliviaamandadaluz@tita.com.br");
        funcionario3.setNascimento(date);
        funcionario3.setNome("Allana Olivia Amanda");
        funcionario3.setSenha("allana0908");
        funcionario3.setSobrenome("da Luz");
        funcionario3.setTelefone("92 99468-9315");
        funcionario3.setSalario(3010.00);
        funcionario3.setCarteira(carteira3);
        funcionarioRepository.save(funcionario3);

       System.out.println(funcionario.toString());
       System.out.println(funcionario2.toString());
        System.out.println(funcionario3.toString());

        List<Funcionario> funcionarios = funcionarioRepository.findFuncionariosByObraId(obra.getId_obra(), obra.getNome_obra());

        Assert.assertNotNull(funcionarios);

        System.out.println(funcionarios.toString());

    }
}