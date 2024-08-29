package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.FuncionarioRH;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CurriculoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.FuncionarioRHRepository;


@Controller
public class FuncionarioRHController {

    @Autowired
    private FuncionarioRHRepository fr;

    @Autowired
    private CurriculoRepository cr;

    @GetMapping("/listafuncionarioRH")
    public ModelAndView listarFuncionarioRH() {
        ModelAndView mv = new ModelAndView("funcionariorh/listaFuncionarioRH");
        Iterable<FuncionarioRH> funcionarioRH = fr.findAll();
        mv.addObject("funcionarioRHs", funcionarioRH);
        return mv;
    }


    // Aprovar currículo
    @PostMapping("/curriculo/aprovar")
    public String aprovarCurriculo(@RequestParam("curriculoId") Long curriculoId, RedirectAttributes redirectAttributes) {
        Curriculo curriculo = cr.findById(curriculoId)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado"));

        // Implementar lógica para aprovar currículo
        // curriculo.setStatus(Status.APROVADO); 

        cr.save(curriculo);
        redirectAttributes.addFlashAttribute("mensagem", "Currículo aprovado com sucesso!");
        return "redirect:/curriculos";
    }

    // Rejeitar currículo
    @PostMapping("/curriculo/rejeitar")
    public String rejeitarCurriculo(@RequestParam("curriculoId") Long curriculoId, RedirectAttributes redirectAttributes) {
        Curriculo curriculo = cr.findById(curriculoId)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado"));

        // Implementar lógica para rejeitar currículo
        // curriculo.setStatus(Status.REJEITADO); 

        cr.save(curriculo);
        redirectAttributes.addFlashAttribute("mensagem", "Currículo rejeitado com sucesso!");
        return "redirect:/curriculos";
    }

    // Excluir currículo
    @PostMapping("/curriculo/excluir")
    public String excluirCurriculo(@RequestParam("curriculoId") Long curriculoId, RedirectAttributes redirectAttributes) {
        cr.deleteById(curriculoId);
        redirectAttributes.addFlashAttribute("mensagem", "Currículo excluído com sucesso!");
        return "redirect:/curriculos";
    }

    @GetMapping("/escolherFuncionarioRH")
    public ModelAndView escolherFuncionarioRH(@RequestParam("funcionarioRhId") Long funcionarioRhId) {
        ModelAndView mv = new ModelAndView("funcionariorh/opcaoFuncionarioRH");
        FuncionarioRH funcionarioRH = fr.findById(funcionarioRhId).orElse(null);
        mv.addObject("funcionarioRH", funcionarioRH);
        return mv;
    }

    // Chama .html na página inicial
    @RequestMapping(value = "/funcionarioRH", method = RequestMethod.GET)
    public String mostrarFormulario() {
        return "funcionariorh/home";
    }     
    
    
}
