package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

// Imports
import org.springframework.data.mongodb.repository.MongoRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.PdfDocument;

public interface PdfDocumentRepository extends MongoRepository<PdfDocument, String>
{
    
}
