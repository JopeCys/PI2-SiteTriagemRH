package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Curriculo;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;


public interface CurriculoRepository extends CrudRepository<Curriculo, Long> 
{
    Curriculo findByCurriculoId(int curriculoId);

    List<Curriculo> findByVaga(Vaga vaga);
}

