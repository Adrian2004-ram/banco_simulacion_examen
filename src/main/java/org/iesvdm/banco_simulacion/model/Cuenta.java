package org.iesvdm.banco_simulacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuenta {

    private Long id;
    private String aliasCuenta;
    private String iban;

}
