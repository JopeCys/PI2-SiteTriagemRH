package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vagaId;

    @NotEmpty
    private String nome;

    @Lob
    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String descricao;

    @NotEmpty
    private String area;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Curriculo> curriculos;

    // Getters
    public long getVagaId() {
        return vagaId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getArea() {
        return area;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    // Setters
    public void setVagaId(long vagaId) {
        this.vagaId = vagaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    
}
    