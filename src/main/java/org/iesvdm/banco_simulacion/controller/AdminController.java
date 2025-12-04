package org.iesvdm.banco_simulacion.controller;

import jakarta.validation.Valid;
import org.iesvdm.banco_simulacion.dto.TransferenciaDTO;
import org.iesvdm.banco_simulacion.model.Transferencia;
import org.iesvdm.banco_simulacion.repository.CuentadDAO;
import org.iesvdm.banco_simulacion.repository.TransferenciaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    TransferenciaDAO transferenciaDAO;

    @Autowired
    CuentadDAO cuentadDAO;

    @GetMapping("/transferencia")
    public String transGet(Model model){

        List<Transferencia> transferencias = transferenciaDAO.findAllTransferencia();

        model.addAttribute("transferencias", transferencias);

        model.addAttribute("cuentaDAO", cuentadDAO);

        return "adminTransferencias";

    }


    @GetMapping("/transferencia/borrar/{id}")
    private String eliminarEvento(Model model, @PathVariable Long id){

        Transferencia transferencia = transferenciaDAO.findById(id);

        model.addAttribute("transferencia",transferencia);

        return "eliminar";

    }

    @PostMapping("/transferencia/borrar/{id}")
    public String borrarEvento(Model model, Transferencia transferencia){

        transferenciaDAO.eliminarTransferencia(transferencia.getId());

        return "redirect:/admin/transferencia";

    }

    @GetMapping("/transferencia/editar/{id}")
    public String editarEvento(Model model, @PathVariable Long id){

        Transferencia transferencia = transferenciaDAO.findById(id);

        model.addAttribute("transferencia",transferencia);
        model.addAttribute("transferenciaDTO", transferenciaDAO);

        return "editar";

    }

    @PostMapping("/eventos/editar/{id}")
    public String editarEventoPost (Model model, Transferencia transferencia) {


        return "redirect:/admin/transferencia";

    }
}
