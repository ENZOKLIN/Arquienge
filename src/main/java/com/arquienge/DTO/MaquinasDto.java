package com.arquienge.DTO;

import com.arquienge.model.Maquina;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaquinasDto {

    private List<Maquina> machines;

    // default and parameterized constructor

    public void addMaquina(Maquina maquina) {
        this.machines.add(maquina);
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.machines = maquinas;
        }
    }
