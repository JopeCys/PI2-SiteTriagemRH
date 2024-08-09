package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.VagaRepository;

@Controller
public class VagaController {
    private VagaRepository vr;

    // CASDASTRO DE VAGA

    // Chama .html do formulario de cadastro de vaga 
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String mostrarFormulario() 
    {
        return "vaga/formVaga";
    }

    // Registra formulario e trata possíveis erros 
    @RequestMapping(value = "/cadastraVaga", method = RequestMethod.POST)
    public String registrarFormulario(@Valid Vaga vaga, BindingResult bindingResult, RedirectAttributes redirectAtributtes) 
    {
        // Se tiver erros no preenchimento do formulário: mostrar mensagem de erro e retornar para cadastro 
        if(bindingResult.hasErrors())
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Verifique os campos corretamente...");
            return "redirect:/cadastrarVaga";
        }

        // Registra os dados 
        vr.save(vaga);
        redirectAtributtes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    // MOSTAR VAGAS EXISTENTES

    // Lista vagas existentes
    @RequestMapping(value = "/vagas")    
    public ModelAndView listarVagas() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVagas");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }
}
