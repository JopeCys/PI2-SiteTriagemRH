package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

// Imports
import org.springframework.data.repository.CrudRepository;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> 
{
    Candidato findByCandidatoId(long candidatoId);
} 
