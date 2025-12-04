package org.iesvdm.banco_simulacion.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferenciaDTO {

    @NotNull(message = "{error.vacio}")
    private Long cuentaId;


    private String nombreBeneficiario;

    //@Min(value=23, message = "{error.tamaño.iban}")
   // @Max(value=25, message = "{error.tamaño.iban}")
    private String ibanDestino;

    @Min(value=1, message = "{error.importe.minimo}")
    private BigDecimal importe;


    private String concepto;

    @Future(message = "{error.fecha.programada.future}")
    private LocalDate fechaProgramada;


}
