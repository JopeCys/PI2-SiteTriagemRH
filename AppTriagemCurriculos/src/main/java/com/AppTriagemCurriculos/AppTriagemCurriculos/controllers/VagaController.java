package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

// import
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.VagaRepository;

@Controller
public class VagaController {

    @Autowired
    private VagaRepository vr;

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
        if (bindingResult.hasErrors()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Verifique os campos corretamente...");
            return "redirect:/cadastrarVaga";
        }

        // Verifica se a vaga já existe
        Optional<Vaga> vagaExistente = vr.findByNomeAndArea(vaga.getNome(), vaga.getArea());
        if (vagaExistente.isPresent()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Vaga já existe! Não é permitido duplicidade.");
            return "redirect:/cadastrarVaga";
        }

        // Registra os dados
        vr.save(vaga);
        redirectAtributtes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    // MOSTAR VAGAS EXISTENTES
    // para gerente depto vagas
    @RequestMapping(value = "/vagas")
    public ModelAndView listarVagas() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // Vagas existentes para candidatos
    @RequestMapping(value = "/vagasCandidato")
    public ModelAndView listarVagasSemLogin() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVagaCandidato");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // DELETAR VAGA
    @RequestMapping(value = "/deletarVaga", method = RequestMethod.POST)
    public String deletarVaga(@RequestParam("vagaId") long vagaId, RedirectAttributes attributes) 
    {
        Vaga vaga = vr.findByVagaId(vagaId);
        
        if (vaga == null)
        {
            attributes.addFlashAttribute("mensagem", "Vaga já foi deletada!");    
            return "redirect:/vagas";    
        }
        
        vr.delete(vaga);
        return "redirect:/vagas";
    }

    // ATUALIZAR/EDITAR VAGA

    // Mostra vaga em detalhes para ser editada
    @RequestMapping(value = "/editarVaga", method = RequestMethod.POST)
    public ModelAndView vagaParaEditar(@RequestParam("vagaId") long vagaId)
    {   
        Vaga vagaParaEdicao = vr.findByVagaId(vagaId);

        if (vagaParaEdicao == null) 
        { 
            ModelAndView mv = listarVagas();
            return mv;
        }

        ModelAndView mv = new ModelAndView("vaga/updateVaga");
        mv.addObject("vaga", vagaParaEdicao);
        return mv;
    }

    // Salva edições | A REVISAR
    @RequestMapping(value = "/editarVaga/salvarVaga", method = RequestMethod.POST)
    public String salvaVagaEditada(@Valid Vaga vaga, BindingResult bindingResult,
            RedirectAttributes redirectAtributtes)
    {
        String caminhoVagaUrl = "" + vaga.getVagaId();
        
        // Se tiver erros no preenchimento do formulário: mostrar mensagem de erro e
        // retornar para cadastro
        if (bindingResult.hasErrors()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Verifique os campos corretamente...");
            return "redirect:/editarVaga/" + caminhoVagaUrl ;
        }

        // Verifica se a vaga já existe
        Optional<Vaga> vagaExistente = vr.findByNomeAndArea(vaga.getNome(), vaga.getArea());
        if (vagaExistente.isPresent()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Vaga já existe! Não é permitido duplicidade.");
            return "redirect:/editarVaga/" + caminhoVagaUrl;
        }

        // Registra os dados
        vr.save(vaga);
        redirectAtributtes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/editarVaga/" + caminhoVagaUrl;
    }

}
