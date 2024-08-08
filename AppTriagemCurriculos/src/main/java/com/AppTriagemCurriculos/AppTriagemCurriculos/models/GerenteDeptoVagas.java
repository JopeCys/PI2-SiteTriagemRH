package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class GerenteDeptoVagas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gerente_depto_vagas_id;

    @OneToMany(mappedBy = "gerenteDeptoVagas")
    private List<Candidato> candidatos;


    // Getters
    public long getGerente_depto_vagas_id() {
        return gerente_depto_vagas_id;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    // Setters
    public void setGerente_depto_vagas_id(long gerente_depto_vagas_id) {
        this.gerente_depto_vagas_id = gerente_depto_vagas_id;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    
}
