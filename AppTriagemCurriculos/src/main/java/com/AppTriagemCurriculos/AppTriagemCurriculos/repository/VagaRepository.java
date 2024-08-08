package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long>
{
    Vaga findByVaga_ID(long vaga_id);
    
    List<Vaga> findByNome(String nome);
}
