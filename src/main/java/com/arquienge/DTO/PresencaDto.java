package com.arquienge.DTO;


import com.arquienge.model.Presenca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PresencaDto {

    private List<Presenca> presencas;

    // default and parameterized constructor

    public void addFerramenta(Presenca presenca) {
        this.presencas.add(presenca);
    }
}
