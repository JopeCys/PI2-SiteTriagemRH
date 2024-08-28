package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;


import java.util.List;
// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.FuncionarioRH;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.PdfDocument;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CandidatoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CurriculoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.FuncionarioRHRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.VagaRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.services.PdfDocumentService;

import java.util.Random;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CandidatoController {

    @Autowired
    private CandidatoRepository cr;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private PdfDocumentService pdfService;

    @Autowired
    private FuncionarioRHRepository fr;

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

    // Chama .html do home do Candidato logado
    @GetMapping("/homeCandidato")
    public String mostrarHomeCandidato() {
        return "candidato/home";
    }

    // Chama .html do home do Candidato prelogin
    @GetMapping("/preloginCandidato")
    public String mostrarPreloginCandidato() {
        return "candidato/prelogin";
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
    @GetMapping("/listaCandidatos")
    public ModelAndView listarCandidatos() {
        ModelAndView mv = new ModelAndView("candidato/listaCandidatos");
        Iterable<Candidato> candidatos = cr.findAll();
        mv.addObject("candidatos", candidatos);
        return mv;
    }




    // LOGIN DOS CANDIDATOS
    @PostMapping("/loginCandidato")
    public String validarLogin(@RequestParam("login") String loginOrEmail, 
                            @RequestParam("senha") String senha, 
                            HttpSession session, 
                            RedirectAttributes redirectAttributes) 
    {
        String mensagem;

        // Busca o candidato pelo login ou email e senha
        Candidato candidato = cr.findByLoginOrEmailAndSenha(loginOrEmail, senha);

        if (candidato != null) {
            // Login bem-sucedido, armazena o candidato na sessão
            session.setAttribute("candidatoLogado", candidato);
            return "redirect:/homeCandidato"; // Direciona para a página de vagas do candidato
        } else {
            // Login falhou
            mensagem = "Login/e-mail ou senha inválidos.";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/loginCandidato";
        }
    }

    // Método para logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginCandidato";
    }


    // Enviar currículo para uma vaga
    @PostMapping("/enviarCurriculo")
    public String enviarCurriculo(@RequestParam("vagaId") Long vagaId, 
                                  @RequestParam("file") MultipartFile file, 
                                  HttpSession session, 
                                  RedirectAttributes redirectAttributes) {
        Candidato candidatoLogado = (Candidato) session.getAttribute("candidatoLogado");
    
        if (candidatoLogado == null) {
            return "redirect:/loginCandidato";
        }
    
        try {
            // Salva o currículo no MongoDB
            PdfDocument pdfDocument = new PdfDocument();
            pdfDocument.setNomeArquivo(file.getOriginalFilename());
            pdfDocument.setConteudo(file.getBytes());
            PdfDocument savedPdf = pdfService.savePdf(pdfDocument);
    
            // Cria o currículo e associa ao candidato, vaga e funcionário RH
            Curriculo curriculo = new Curriculo();
            curriculo.setCandidato(candidatoLogado);
    
            Vaga vaga = vagaRepository.findById(vagaId).orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
            curriculo.setVaga(vaga);
    
            // Seleciona aleatoriamente um dos funcionários RH
            List<FuncionarioRH> funcionariosRH = (List<FuncionarioRH>) fr.findAll(); 
            if (funcionariosRH.isEmpty()) {
                redirectAttributes.addFlashAttribute("mensagem", "Nenhum responsável (RH) encontrado. Por favor, tente novamente mais tarde.");
                return "redirect:/vagasCandidato";
            }
            FuncionarioRH funcionarioRH = funcionariosRH.get(new Random().nextInt(funcionariosRH.size()));
    
            curriculo.setFuncionarioRh(funcionarioRH);
            curriculo.setMongoId(savedPdf.getId());
            curriculoRepository.save(curriculo);
    
            redirectAttributes.addFlashAttribute("mensagem", "Currículo enviado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Falha ao enviar currículo. Tente novamente.");
        }
    
        return "redirect:/vagasCandidato";
    }

    // Excluir candidato
    @GetMapping("/excluirCandidato")
    public String excluirCandidato(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        cr.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Candidato excluído com sucesso!");
        return "redirect:/listarcandidatos";
    }

    // Editar candidato (essa é uma rota de exemplo, que precisaria ser mais elaborada para funcionar completamente)
    @GetMapping("/editarCandidato")
    public ModelAndView editarCandidato(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("candidato/formCandidato");
        Candidato candidato = cr.findById(id).orElse(null);
        mv.addObject("candidato", candidato);
        return mv;
    }


}
