package com.arquienge.DTO;


import com.arquienge.model.Obra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ObrasDTO {

    private List<Obra> obras;

    // default and parameterized constructor

    public void addFerramenta(Obra obra) {
        this.obras.add(obra);
    }
}
