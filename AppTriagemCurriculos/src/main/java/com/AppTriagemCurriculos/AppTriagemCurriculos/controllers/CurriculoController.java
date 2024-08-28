package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CurriculoRepository;

@Controller
public class CurriculoController {

    @Autowired
    private CurriculoRepository cr;

    @GetMapping("/listarCurriculosPorFuncionarioRH")
    public ModelAndView listarCurriculosPorFuncionarioRH(@RequestParam("funcionarioRhId") long funcionarioRhId) {
        ModelAndView mv = new ModelAndView("curriculo/listaCurriculo");
        Iterable<Curriculo> curriculos = cr.findByFuncionarioRhId(funcionarioRhId);
        mv.addObject("curriculos", curriculos);
        return mv;
    }
}