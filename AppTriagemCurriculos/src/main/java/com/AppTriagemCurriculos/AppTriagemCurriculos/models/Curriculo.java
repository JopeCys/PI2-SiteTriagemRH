package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculoId")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "mongoId", nullable = false)
    private String mongoId;

    @ManyToOne
    @JoinColumn(name = "candidatoId")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "funcionarioRhId")
    private FuncionarioRH funcionarioRh;

    @ManyToOne
    @JoinColumn(name = "vagaId")
    private Vaga vaga;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMongoId() {
        return mongoId;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }
}
