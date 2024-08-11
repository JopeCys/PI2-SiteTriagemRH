package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

// import
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CurriculoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.VagaRepository;

@Controller
public class VagaController {

    @Autowired
    private VagaRepository vr;

    @Autowired
    private CurriculoRepository cur;

    // CASDASTRO DE VAGA

    // Chama .html do formulario de cadastro de vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String mostrarFormulario() {
        return "vaga/formVaga";
    }

    // Registra formulario e trata possíveis erros
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String registrarFormulario(@Valid Vaga vaga, BindingResult bindingResult,
            RedirectAttributes redirectAtributtes) {
        // Se tiver erros no preenchimento do formulário: mostrar mensagem de erro e
        // retornar para cadastro
        if (bindingResult.hasErrors()) {
            redirectAtributtes.addFlashAttribute("mensagem", "Verifique os campos corretamente...");
            return "redirect:/cadastrarVaga";
        }

        // Registra os dados
        vr.save(vaga);
        redirectAtributtes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    // MOSTAR VAGAS EXISTENTES
    @RequestMapping(value = "/vagas")
    public ModelAndView listarVagas() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // DELETAR VAGA
    @RequestMapping(value = "/deletarVaga")
    public String deletarVaga(long vagaId) 
    {
        Vaga vaga = vr.findByVagaId(vagaId);
        vr.delete(vaga);
        return "redirect:/vagas";
    }


    // A revisar
    public String detalhesVagaPost(@PathVariable("vagaId") long vagaId, @Valid Curriculo curriculo,
            BindingResult result, RedirectAttributes attributes) 
    {

        if(result.hasErrors())
        {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/#";
        }


        // Gravacao do curriculo na vaga
        Vaga vaga = vr.findByVagaId(vagaId);
        curriculo.setVaga(vaga);
        cur.save(curriculo);
        attributes.addFlashAttribute("mensagem", "Curriculo adicionado com sucesso!");
        return "redirect:/#";
    } 

}
