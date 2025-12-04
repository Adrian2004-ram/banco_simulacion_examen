package org.iesvdm.banco_simulacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transferencia {

    private Long id;
    private Long cuentaId;
    private String nombreBeneficiario;
    private String ibanDestino;
    private BigDecimal importe;
    private String concepto;
    private LocalDate fechaProgramada;
    private LocalDateTime fechaCreacion;
    private String estado;

}
