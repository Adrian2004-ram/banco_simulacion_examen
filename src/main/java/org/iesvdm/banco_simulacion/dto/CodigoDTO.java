package org.iesvdm.banco_simulacion.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodigoDTO {

    @NotEmpty(message = "no puede estar vacio")
    private String codigo;

}
