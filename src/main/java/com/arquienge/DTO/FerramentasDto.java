package com.arquienge.DTO;

import com.arquienge.model.Ferramenta;
import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FerramentasDto {

    private List<Ferramenta> ferramentas;

    // default and parameterized constructor

    public void addFerramenta(Ferramenta ferramenta) {
        this.ferramentas.add(ferramenta);
    }
}
