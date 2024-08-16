package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "funcionario_rh")
public class FuncionarioRH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionarioRhId")
    private Long id;

    @OneToMany(mappedBy = "funcionarioRh")
    private Set<Candidato> candidatos;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCandidatos(Set<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Set<Candidato> getCandidatos() {
        return candidatos;
    }

    
}
