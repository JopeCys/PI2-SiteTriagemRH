package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CandidatoRepository;

import jakarta.validation.Valid;


@Controller
public class CandidatoController {
     @Autowired
    private CandidatoRepository cr;
    
    // AUTO CADASTRO DO CANDIDATO

    // Chama .html do formulario de cadastro de Candidato
    @RequestMapping(value = "/cadastrarCandidato", method = RequestMethod.GET)
    public String mostrarFormularioCandidato() {
        return "candidato/formCandidato";
    }

    // Chama .html do login do Candidato
    @RequestMapping(value = "/loginCandidato", method = RequestMethod.GET)
    public String mostrarLoginCandidato() {
        return "candidato/loginCandidato";
    }


    // Registra formulario candidato e trata possíveis erros
    @RequestMapping(value = "/cadastrarCandidato", method = RequestMethod.POST)
    public String registrarFormulario(@Valid Candidato candidato, BindingResult bindingResult,
            RedirectAttributes redirectAtributtes) {
        // Se tiver erros no preenchimento do formulário: mostrar mensagem de erro e
        // retornar para cadastro
        if (bindingResult.hasErrors()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Verifique os campos corretamente...");
            return "redirect:/cadastrarCandidato";
        }

        // Verifica se a candidato já existe
        Optional<Candidato> candidatoExistente = cr.findByLoginAndEmail(candidato.getLogin(), candidato.getEmail());
        if (candidatoExistente.isPresent()) 
        {
            redirectAtributtes.addFlashAttribute("mensagem", "Candidato já cadastrado.");
            return "redirect:/cadastrarCandidato";
        }

        // Registra os dados
        cr.save(candidato);
        redirectAtributtes.addFlashAttribute("mensagem", "Candidato cadastrada com sucesso!");
        return "redirect:/cadastrarCandidato";
    }

    // MONSTRAR CANDIDATOS EXISTENTES
    @RequestMapping(value = "/candidatos")
    public ModelAndView listarVagas() 
    {
        ModelAndView mv = new ModelAndView("candidato/listaCandidatos");
        Iterable<Candidato> candidatos = cr.findAll();
        mv.addObject("candidatos", candidatos);
        return mv;
    }

    // Criptografia da senha

    // A  fazer, para se se Candidatar a uma vaga, o candidato precisa estar logado
    // @RequestMapping(value = "/candidatar", method = RequestMethod.POST)
    // public ModelAndView vagaParaEditar(@RequestParam("vagaId") long vagaId)

    // LOGIN DOS CANDIDATOS

    // Validar Login
    @RequestMapping(value = "/loginCandidato", method = RequestMethod.POST)
    public String validarLogin(@RequestParam("login") String login, 
                               @RequestParam("senha") String senha, 
                               RedirectAttributes redirectAttributes) 
    {
        // Verifica se o candidato existe e se a senha está correta
        Optional<Candidato> candidato = cr.findByLogin(login);
        if (candidato.isPresent() && candidato.get().getSenha().equals(senha)) 
        {
            // Login bem-sucedido
            return "redirect:/vagasCandidato"; // Direciona para a página inicial do candidato
        } else 
        {
            // Login falhou
            redirectAttributes.addFlashAttribute("mensagem", "Login ou senha inválidos.");
            return "redirect:/loginCandidato";
        }
    }
}
