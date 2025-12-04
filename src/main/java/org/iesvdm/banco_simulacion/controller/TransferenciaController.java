package org.iesvdm.banco_simulacion.controller;

import jakarta.validation.Valid;
import org.iesvdm.banco_simulacion.dto.CodigoDTO;
import org.iesvdm.banco_simulacion.dto.TransferenciaDTO;
import org.iesvdm.banco_simulacion.model.Cuenta;
import org.iesvdm.banco_simulacion.model.Transferencia;
import org.iesvdm.banco_simulacion.repository.CuentadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transferencia/transferir")
@SessionAttributes("transferenciaDTO")
public class TransferenciaController {

    @Autowired
    CuentadDAO cuentadDAO;

    @GetMapping("/paso1")
    public String paso1Get(Model model, TransferenciaDTO transferenciaDTO) {

        // Traemos la lista cuentas
        List<Cuenta> cuentas = cuentadDAO.findAllCuentas();
        model.addAttribute("cuentas", cuentas);

        // Inicializar el DTO
        model.addAttribute("transferenciaDTO", transferenciaDTO);

        return "paso1";

    }

    @PostMapping("/paso2")
    public String paso1Post(Model model, @Valid TransferenciaDTO transferenciaDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            // Traemos la lista cuentas
            List<Cuenta> cuentas = cuentadDAO.findAllCuentas();
            model.addAttribute("cuentas", cuentas);

            // Inicializar el DTO
            model.addAttribute("transferenciaDTO", transferenciaDTO);

            return "paso1";

        }

        // Buscamos la cuenta para obtener el iban
        Cuenta cuentaOrigen = cuentadDAO.findById(transferenciaDTO.getCuentaId());
        model.addAttribute("cuentaOrigen", cuentaOrigen);

        // Pasamos el DTO
        model.addAttribute("transferenciaDTO", transferenciaDTO);

        return "paso2";

    }

//    @GetMapping("/final")
//    public String paso2Post(Model model, @ModelAttribute TransferenciaDTO transferenciaDTO, @Valid CodigoDTO codigoDTO, BindingResult bindingResult) {
//
////        if(!codigoDTO.getCodigo().equals("123456")) {
////
////            // Buscamos la cuenta para obtener el iban
////            Cuenta cuentaOrigen = cuentadDAO.findById(transferenciaDTO.getCuentaId());
////            model.addAttribute("cuentaOrigen", cuentaOrigen);
////
////            // Pasamos el DTO
////            model.addAttribute("transferenciaDTO", transferenciaDTO);
////
////            return "paso2";
////
////        }
//
//        if (bindingResult.hasErrors()) {
//
//            // Buscamos la cuenta para obtener el iban
//            Cuenta cuentaOrigen = cuentadDAO.findById(transferenciaDTO.getCuentaId());
//            model.addAttribute("cuentaOrigen", cuentaOrigen);
//
//            // Pasamos el DTO
//            model.addAttribute("transferenciaDTO", transferenciaDTO);
//
//            return "paso2";
//
//        }
//
//
//        return "final";
//
//    }
//
//    @GetMapping("final")
//    public String fin() {
//
//        return "redirect:/transferencia/transferir/final";
//
//    }


}
