package com.arquienge.service;

import com.arquienge.model.CarteiradeTrabalho;
import com.arquienge.repository.CarteiradeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class CarteiradeTrabalhoService {

    private final CarteiradeTrabalhoRepository carteiradeTrabalhoRepository;

    public CarteiradeTrabalhoService(CarteiradeTrabalhoRepository carteiradeTrabalhoRepository) {
        this.carteiradeTrabalhoRepository = carteiradeTrabalhoRepository;
    }

    public List<CarteiradeTrabalho> selectAll() {
        List<CarteiradeTrabalho> carteiras = new ArrayList<>();
        for (CarteiradeTrabalho carteira : carteiradeTrabalhoRepository.findAll()) {
            carteiras.add(carteira);
        }
        return carteiras;
    }

    public Optional<CarteiradeTrabalho> selectById(Integer id) {
        Optional<CarteiradeTrabalho> carteira = carteiradeTrabalhoRepository.findById(id);
        if (isNull(carteira)) {
            throw new RuntimeException("Carteira não encontrada!");
        }
        return carteira;
    }

    public void saveCarteira(CarteiradeTrabalho carteira) {
        carteiradeTrabalhoRepository.save(carteira);
    }

    public void deleteCarteiraById(Integer id) {
        carteiradeTrabalhoRepository.deleteById(id);
    }

    public void deleteCarteira(CarteiradeTrabalho carteiradeTrabalho) {
        carteiradeTrabalhoRepository.delete(carteiradeTrabalho);
    }

    public CarteiradeTrabalho findCarteiraByNumerodaCarteira(String numerocarteira) {
        return carteiradeTrabalhoRepository.findCarteiradeTrabalhoByNumerodaCarteira(numerocarteira);
    }

    public CarteiradeTrabalho findCarteiraByPis(String pis) {
        return carteiradeTrabalhoRepository.findCarteiradeTrabalhoByPis(pis);
    }

    public List<CarteiradeTrabalho> findCarteirasBySerie(String serie) {
        return carteiradeTrabalhoRepository.findCarteirasdeTrabalhoBySerie(serie);
    }

    public List<CarteiradeTrabalho> findCarteirasByUf(String uf) {
        return carteiradeTrabalhoRepository.findCarteiradeTrabalhoByUf(uf);
    }

    public CarteiradeTrabalho findCarteiraByFuncionarioId(Integer id) {
        return carteiradeTrabalhoRepository.findCarteiradeTrabalhoByIdCarteiradoFuncionario(id);
    }
}
