package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.GerenteDeptoVagas;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.GerenteDeptoVagasRepository;


@Controller
public class GerenteDeptoVagasController {

    @Autowired
    private GerenteDeptoVagasRepository gr;


    // Lista gerentes do Departamento de Vagas
    @GetMapping("/listagerenteDeptoVagas")
    public ModelAndView listarGerenteDeptoVagas() 
    {
        ModelAndView mv = new ModelAndView("gerenteDeptoVagas/listaGerenteDeptoVagas");
        Iterable<GerenteDeptoVagas> gerenteDeptoVagas = gr.findAll();
        mv.addObject("gerentes", gerenteDeptoVagas);
        return mv;
    }



    // Chama .html na página inicial
    @RequestMapping(value = "/gerenteDeptoVagas", method = RequestMethod.GET)
    public String mostrarFormulario() {
        return "gerenteDeptoVagas/home";
    }
    
}
