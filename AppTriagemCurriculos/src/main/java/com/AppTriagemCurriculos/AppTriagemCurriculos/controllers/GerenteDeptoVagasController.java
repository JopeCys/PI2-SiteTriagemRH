package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GerenteDeptoVagasController {

    // Chama .html na página inicial
    @RequestMapping(value = "/gerenteDeptoVagas", method = RequestMethod.GET)
    public String mostrarFormulario() {
        return "gerenteDeptoVagas/home";
    }
}
