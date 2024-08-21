package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

// Imports
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;



public interface CandidatoRepository extends CrudRepository<Candidato, Long> 
{
    Candidato findById(long id);

    Candidato findByLoginAndEmail(String login, String email);

    Candidato findByLogin(String login);
    
    boolean existsByLoginAndEmail(String login, String e);

    boolean existsByLoginAndSenha(String login, String e);

    boolean existsByEmailAndSenha(String email, String senha);
} 