package com.arquienge.converter;

import com.arquienge.model.Obra;
import com.arquienge.service.ObraService;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Data
public class ObraConverter implements Converter<String, Obra> {

    private ObraService obraService;

    @Override
    public Obra convert(String id) {
        Integer idt = Integer.parseInt(id);
        return obraService.selectObrabyId_obra(idt);
    }
}
