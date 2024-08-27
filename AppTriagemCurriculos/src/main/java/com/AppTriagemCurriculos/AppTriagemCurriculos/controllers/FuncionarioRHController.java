package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.FuncionarioRH;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.FuncionarioRHRepository;

@Controller
public class FuncionarioRHController {

    @Autowired
    private FuncionarioRHRepository fr;

    @GetMapping("/listafuncionarioRH")
    public ModelAndView listarFuncionariosRH() 
    {
        ModelAndView mv = new ModelAndView("funcionariorh/listaFuncionarioRH");
        Iterable<FuncionarioRH> funcionariosRH = fr.findAll();
        mv.addObject("funcionarios", funcionariosRH);
        return mv;
    }

    // Chama .html na página inicial
    @RequestMapping(value = "/funcionarioRH", method = RequestMethod.GET)
    public String mostrarFormulario() {
        return "funcionariorh/home";
    }
    
}
