package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.GerenteDeptoVagas;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.GerenteDeptoVagasRepository;



@Controller
public class GerenteDeptoVagasController {

    @Autowired
    private GerenteDeptoVagasRepository gr;


    @GetMapping("/listagerenteDeptoVagas")
    public ModelAndView listarGerenteDeptoVagas() {
        ModelAndView mv = new ModelAndView("gerenteDeptoVagas/listaGerenteDeptoVagas");
        Iterable<GerenteDeptoVagas> gerenteDeptoVagas = gr.findAll();
        mv.addObject("gerentes", gerenteDeptoVagas);
        return mv;
    }

    @GetMapping("/escolherGerente")
    public ModelAndView escolherGerente(@RequestParam("gerenteDeptoVagasId") Long gerenteDeptoVagasId) {
        ModelAndView mv = new ModelAndView("gerenteDeptoVagas/opcaoGerente");
        GerenteDeptoVagas gerente = gr.findById(gerenteDeptoVagasId).orElse(null);
        mv.addObject("gerente", gerente);
        return mv;
    }

    // Chama .html na página inicial
    @RequestMapping(value = "/gerenteDeptoVagas", method = RequestMethod.GET)
    public String mostrarHome() {
        return "gerenteDeptoVagas/home";
    }

    // Chama .html na página inicial
    @RequestMapping(value = "/funcionarioGerente", method = RequestMethod.GET)
    public String mostrarFuncionarioRHOuGerenteDeptoVagas() {
        return "funcionarioGerente";
    }

}
