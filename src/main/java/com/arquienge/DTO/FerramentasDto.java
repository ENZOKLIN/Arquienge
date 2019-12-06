package com.arquienge.DTO;

import com.arquienge.model.Ferramenta;
import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FerramentasDto {

    private List<Ferramenta> ferramentas;

    // default and parameterized constructor

    public void addFerramenta(Ferramenta ferramenta) {
            this.ferramentas.add(ferramenta);
    }
}

