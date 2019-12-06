
package com.arquienge.converter;

import com.arquienge.model.Ferramenta;
import com.arquienge.service.FerramentaService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FerramentaConverter implements Converter<String, Ferramenta> {

    private final FerramentaService ferramentaService;

    public FerramentaConverter(FerramentaService ferramentaService) {
        this.ferramentaService = ferramentaService;
    }

    public Ferramenta convert(Integer id) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setCod_ferramenta(id);
        return ferramenta;
    }

    @Override
    public Ferramenta convert(String s) {
        Integer id = Integer.parseInt(s);
        return ferramentaService.findFerramentaById(id);

    }
}
