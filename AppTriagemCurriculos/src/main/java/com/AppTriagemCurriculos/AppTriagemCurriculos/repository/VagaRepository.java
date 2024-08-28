package com.AppTriagemCurriculos.AppTriagemCurriculos.repository;

// Imports
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.AppTriagemCurriculos.AppTriagemCurriculos.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long> {
    Vaga findById(long id);
    
    Vaga findByNomeAndArea(String nome, String area);
    
    List<Vaga> findByNome(String nome);
    
    Boolean existsByNomeAndArea(String nome, String area);
    
    Boolean existsById(long vagaId);

    Iterable<Vaga> findByGerenteDeptoVagasId(Long gerenteDeptoVagasId);
}
