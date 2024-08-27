package com.AppTriagemCurriculos.AppTriagemCurriculos.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.PdfDocument;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.CurriculoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.repository.PdfDocumentRepository;

@Service
public class PdfDocumentService {

    @Autowired
    private PdfDocumentRepository pdfRepository;

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Transactional
    public void savePdf(MultipartFile file, Long curriculoId) throws IOException {
        // Salva o PDF no MongoDB
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.setConteudo(file.getBytes());
        pdfDocument.setNomeArquivo(file.getOriginalFilename());
        PdfDocument savedPdf = pdfRepository.save(pdfDocument);

        // Atualiza o currículo no MySQL com o mongoId
        Curriculo curriculo = curriculoRepository.findById(curriculoId)
                .orElseThrow(() -> new RuntimeException("Currículo não encontrado"));
        curriculo.setMongoId(savedPdf.getId());
        curriculoRepository.save(curriculo);
    }

    public PdfDocument getPdf(String mongoId) {
        return pdfRepository.findById(mongoId)
                .orElseThrow(() -> new RuntimeException("PDF não encontrado"));
    }
}
