package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "curriculo")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int curriculoId;

    @ManyToOne
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @Lob
    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String arquivo;

    // Getters
    public int getcurriculoId() {
        return curriculoId;
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
    public void setcurriculoId(int curriculoId) {
        this.curriculoId = curriculoId;
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
