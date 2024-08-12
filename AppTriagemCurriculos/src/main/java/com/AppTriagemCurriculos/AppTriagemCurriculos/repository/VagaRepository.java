package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long> {
    Vaga findByVagaId(long vagaId);
    Optional<Vaga> findByNomeAndArea(String nome, String area);
    
    List<Vaga> findByNome(String nome);

    
}
