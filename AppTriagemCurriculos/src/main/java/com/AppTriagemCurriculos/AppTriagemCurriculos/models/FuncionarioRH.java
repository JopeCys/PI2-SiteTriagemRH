package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario_rh")
public class FuncionarioRH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long funcionario_rh_id;

    @OneToMany(mappedBy = "funcionarioRH")
    private List<Candidato> candidatos;

    // Getters
    public long getFuncionario_rh_id() {
        return funcionario_rh_id;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    // Setters
    public void setFuncionario_rh_id(long funcionario_rh_id) {
        this.funcionario_rh_id = funcionario_rh_id;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
    
}
