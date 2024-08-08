package com.models;

// Imports
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FuncionarioRH 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long funcionario_rh_id;

    @OneToMany
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
