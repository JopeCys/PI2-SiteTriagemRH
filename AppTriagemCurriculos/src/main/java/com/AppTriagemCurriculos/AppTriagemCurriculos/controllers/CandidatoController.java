package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;


// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Chama .html do formulário de cadastro de Candidato
    @GetMapping("/cadastrarCandidato")
    public String mostrarFormularioCandidato() {
        return "candidato/formCandidato";
    }

    // Chama .html do login do Candidato
    @GetMapping("/loginCandidato")
    public String mostrarLoginCandidato() {
        return "candidato/loginCandidato";
    }


    // Registra formulário de candidato e trata possíveis erros
    @PostMapping("/cadastrarCandidato")
    public String registrarFormulario(@Valid Candidato candidato, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String mensagem;
        
        // Se tiver erros no preenchimento do formulário
        if (bindingResult.hasErrors()) {
            mensagem = "Verifique os campos corretamente...";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/cadastrarCandidato";
        }

        // Verifica se o candidato já existe
        boolean candidatoExistente = cr.existsByLoginAndEmail(candidato.getLogin(), candidato.getEmail());
        if (candidatoExistente) {
            mensagem = "Candidato já cadastrado.";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/cadastrarCandidato";
        }

        // Registra os dados
        cr.save(candidato);
        mensagem = "Candidato cadastrado com sucesso!";
        redirectAttributes.addFlashAttribute("mensagem", mensagem);
        return "redirect:/cadastrarCandidato";
    }

    // Mostrar candidatos existentes
    @GetMapping("/candidatos")
    public ModelAndView listarCandidatos() {
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
    @PostMapping("/loginCandidato")
    public String validarLogin(@RequestParam("login") String loginOrEmail, 
                                @RequestParam("senha") String senha, 
                                RedirectAttributes redirectAttributes) 
    {
        String mensagem;

        // Verifica se o candidato existe com o login e a senha fornecidos
        boolean candidatoExiste = cr.existsByLoginAndSenha(loginOrEmail, senha) || cr.existsByEmailAndSenha(loginOrEmail, senha);
        
        if (candidatoExiste) {
            // Login bem-sucedido
            return "redirect:/vagasCandidato"; // Direciona para a página de vagas do candidato
        } else {
            // Login falhou
            mensagem = "Login/e-mail ou senha inválidos.";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/loginCandidato";
        }
    }
}
