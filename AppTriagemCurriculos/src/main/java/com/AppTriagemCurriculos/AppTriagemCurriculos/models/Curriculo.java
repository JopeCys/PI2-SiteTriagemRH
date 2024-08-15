package com.AppTriagemCurriculos.AppTriagemCurriculos.models;


import jakarta.persistence.*;

@Entity
@Table(name = "curriculo")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculoId")
    private Long id;

    @Column(name = "nomeArquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "tipoArquivo", nullable = false)
    private String tipoArquivo;

    @Lob
    @Column(name = "dados", nullable = false)
    private byte[] dados;

    @ManyToOne
    @JoinColumn(name = "candidatoId")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "vagaId")
    private Vaga vaga;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
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

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public byte[] getDados() {
        return dados;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }
}
