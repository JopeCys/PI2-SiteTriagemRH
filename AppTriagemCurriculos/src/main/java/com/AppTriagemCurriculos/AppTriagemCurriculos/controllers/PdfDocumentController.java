package com.AppTriagemCurriculos.AppTriagemCurriculos.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.PdfDocument;
import com.AppTriagemCurriculos.AppTriagemCurriculos.services.PdfDocumentService;

@RestController
@RequestMapping("/api/pdfs")
public class PdfDocumentController {

    @Autowired
    private PdfDocumentService pdfService;

    @PostMapping("/{curriculoId}")
    public ResponseEntity<Void> uploadPdf(@RequestParam("file") MultipartFile file, @PathVariable Long curriculoId) {
        try {
            pdfService.savePdf(file, curriculoId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{mongoId}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String mongoId) {
        PdfDocument pdfDocument = pdfService.getPdf(mongoId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfDocument.getNomeArquivo() + "\"")
                .body(pdfDocument.getConteudo());
    }
}
