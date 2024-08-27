package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

// Imports
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.VagaRepository;

@Controller
public class VagaController {

    @Autowired
    private VagaRepository vr;

    // CADASTRO DE VAGA

    // Chama .html do formulário de cadastro de vaga
    @GetMapping("/cadastrarVaga")
    public String mostrarFormulario() {
        return "vaga/formVaga";
    }

    // Registra formulário e trata possíveis erros
    @PostMapping("/cadastrarVaga")
    public String registrarFormulario(@Valid Vaga vaga, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String mensagem;
        
        // Se tiver erros no preenchimento do formulário
        if (bindingResult.hasErrors()) {
            mensagem = "Verifique os campos corretamente...";
        } else if (vr.existsByNomeAndArea(vaga.getNome(), vaga.getArea())) {
            // Verifica se a vaga já existe
            mensagem = "Vaga já existe! Não é permitido duplicidade.";
        } else {
            // Registra os dados
            vr.save(vaga);
            mensagem = "Vaga cadastrada com sucesso!";
        }
        
        redirectAttributes.addFlashAttribute("mensagem", mensagem);
        return "redirect:/cadastrarVaga";
    }

    // MOSTAR VAGAS EXISTENTES
    // para gerente depto vagas
    @GetMapping("/vagas")
    public ModelAndView listarVagas() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // Vagas existentes para candidatos - sem poder editar ou excluir
    @GetMapping("/vagasCandidato")
    public ModelAndView listarVagasSemLogin() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVagaCandidato");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // para gerente pessoas não logadas
    @GetMapping("/vagasIndex")
    public ModelAndView listarVagasIndex() 
    {
        ModelAndView mv = new ModelAndView("vaga/listaVagaIndex");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // DELETAR VAGA
    @PostMapping("/deletarVaga")
    public String deletarVaga(@RequestParam("vagaId") long vagaId, RedirectAttributes redirectAttributes) {
        String mensagem;

        // Verifica se a vaga existe
        if (!vr.existsById(vagaId)) {
            mensagem = "Vaga já foi deletada ou não existe!";
        } else {
            // Deleta a vaga
            vr.deleteById(vagaId);
            mensagem = "Vaga deletada com sucesso!";
        }

        redirectAttributes.addFlashAttribute("mensagem", mensagem);
        return "redirect:/vagas";
    }

    // ATUALIZAR/EDITAR VAGA

    // Mostra vaga em detalhes para ser editada
    // Abre o formulário de edição de vaga
    // Abre o formulário de edição de vaga
    @PostMapping("/editarVaga")
    public ModelAndView vagaParaEditar(@RequestParam("vagaId") long vagaId) {   
        Vaga vagaParaEdicao = vr.findById(vagaId);

        // Verifica se a vaga existe
        if (vagaParaEdicao == null) {
            return new ModelAndView("redirect:/vagas");
        }

        ModelAndView mv = new ModelAndView("vaga/updateVaga");
        mv.addObject("vaga", vagaParaEdicao);
        return mv;
    }

    // Salva edições da vaga
    @PostMapping("/editarVaga/salvarVaga")
    public String salvaVagaEditada(@Valid Vaga vaga, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String mensagem;
        
        // Se tiver erros no preenchimento do formulário
        if (bindingResult.hasErrors()) {
            mensagem = "Verifique os campos corretamente...";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/editarVaga/" + vaga.getId();
        }

        // Verifica se a vaga com o mesmo nome e área já existe (exceto a própria)
        Vaga vagaExistente = vr.findByNomeAndArea(vaga.getNome(), vaga.getArea());
        if (vagaExistente != null && vagaExistente.getId() != vaga.getId()) {
            mensagem = "Vaga já existe! Não é permitido duplicidade.";
            redirectAttributes.addFlashAttribute("mensagem", mensagem);
            return "redirect:/editarVaga/" + vaga.getId();
        }

        // Salva as alterações na vaga
        vr.save(vaga);
        mensagem = "Vaga atualizada com sucesso!";
        redirectAttributes.addFlashAttribute("mensagem", mensagem);
        return "redirect:/vagas";
    }
}
