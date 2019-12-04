package com.arquienge.DTO;


import com.arquienge.model.MaquinasUsadas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaquinasUsadasDto {

    private List<MaquinasUsadas> maquinasUsadas;

    // default and parameterized constructor

    public void addFerramenta(MaquinasUsadas maquinasUsadas) {
        this.maquinasUsadas.add(maquinasUsadas);
    }
}
