package com.arquienge.DTO;


import com.arquienge.model.FerramentasUsadas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FerramentasUsadasDto {

    private List<FerramentasUsadas> ferramentasUsadas;

    // default and parameterized constructor

    public void addFerramenta(FerramentasUsadas ferramentasUsadas) {
        this.ferramentasUsadas.add(ferramentasUsadas);
    }
}
