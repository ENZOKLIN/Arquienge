package com.arquienge.DTO;

import com.arquienge.model.Maquina;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaquinasDto {

    private List<Maquina> maquinas;

    // default and parameterized constructor

    public void addMaquina(Maquina maquina) {
        this.maquinas.add(maquina);
    }
}
