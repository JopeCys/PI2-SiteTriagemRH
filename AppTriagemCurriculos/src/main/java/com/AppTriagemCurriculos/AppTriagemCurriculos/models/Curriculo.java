package com.AppTriagemCurriculos.AppTriagemCurriculos.models;


import jakarta.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculo 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CurriculoId")
    private Long id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "MongoId", nullable = false)
    private String mongoId;

    @ManyToOne
    @JoinColumn(name = "CandidatoId")
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

    public Candidato getCandidato() {
        return candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }
}
