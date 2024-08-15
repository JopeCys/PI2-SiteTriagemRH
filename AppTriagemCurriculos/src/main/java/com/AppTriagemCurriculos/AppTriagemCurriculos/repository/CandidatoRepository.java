package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

// Imports
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;



public interface CandidatoRepository extends CrudRepository<Candidato, Long> 
{
    Candidato findByCandidatoId(long candidatoId);

    Optional<Candidato> findByLoginAndEmail(String login, String email);

    List<Candidato> findByNome(String nome);
    
} 