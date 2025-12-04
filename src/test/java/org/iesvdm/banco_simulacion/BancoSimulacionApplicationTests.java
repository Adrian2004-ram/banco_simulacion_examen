package org.iesvdm.banco_simulacion;

import org.iesvdm.banco_simulacion.model.Cuenta;
import org.iesvdm.banco_simulacion.model.Transferencia;
import org.iesvdm.banco_simulacion.repository.CuentadDAO;
import org.iesvdm.banco_simulacion.repository.TransferenciaDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BancoSimulacionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    TransferenciaDAO transferenciaDAO;

    @Autowired
    CuentadDAO cuentadDAO;

    @Test
    void createTransferenciaTest() {
        // Aquí iría la lógica para probar la creación de una transferencia

        Transferencia transferencia = Transferencia.builder()
                .cuentaId(1L)
                .nombreBeneficiario("Adrian Espada")
                .ibanDestino("ES7620770024003102575766")
                .importe(new java.math.BigDecimal("150.75"))
                .concepto("Pago de servicios")
                .fechaProgramada(java.time.LocalDate.now().plusDays(5))
                .build();

        transferenciaDAO.createTransferencia(transferencia);

    }

    @Test
    void listaCuentas() {

        List<Cuenta> cuentas = cuentadDAO.findAllCuentas();

        cuentas.forEach(System.out::println);

    }

}
