package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int curriculo_id;

    @ManyToOne
    private Candidato candidato;

    @ManyToOne
    private Vaga vaga;

    @Lob
    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String arquivo;

    // Getters
    public int getCurriculo_id() {
        return curriculo_id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public String getArquivo() {
        return arquivo;
    }

    // Setters
    public void setCurriculo_id(int curriculo_id) {
        this.curriculo_id = curriculo_id;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    
}
