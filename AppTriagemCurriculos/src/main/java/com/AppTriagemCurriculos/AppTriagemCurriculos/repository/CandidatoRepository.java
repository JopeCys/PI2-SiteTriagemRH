package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

import org.springframework.data.jpa.repository.Query;
// Imports
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Candidato;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> 
{
    Candidato findById(long id);

    Candidato findByLoginAndEmail(String login, String email);

    Candidato findByLogin(String login);
    
    // Buscar candidato por login ou email e senha usando @Query
    @Query("SELECT c FROM Candidato c WHERE (c.login = :loginOrEmail OR c.email = :loginOrEmail) AND c.senha = :senha")
    Candidato findByLoginOrEmailAndSenha(@Param("loginOrEmail") String loginOrEmail, @Param("senha") String senha);
    
    // Verificar existência de candidato por login e e-mail
    boolean existsByLoginAndEmail(String login, String email);

    // Verificar existência de candidato por login ou email e senha usando @Query
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Candidato c WHERE (c.login = :loginOrEmail OR c.email = :loginOrEmail) AND c.senha = :senha")
    boolean existsByLoginOrEmailAndSenha(@Param("loginOrEmail") String loginOrEmail, @Param("senha") String senha);

} 