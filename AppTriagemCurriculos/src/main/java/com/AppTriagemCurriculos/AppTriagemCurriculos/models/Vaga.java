package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vaga {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vaga_id;

    @NotEmpty
    private String nome;

    @Lob
    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String descricao;

    @NotEmpty
    private String area;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private Curriculo curriculo;

    // Getters
    public long getVaga_id() {
        return vaga_id;
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

    public Curriculo getCurriculo() {
        return curriculo;
    }

    // Setters
    public void setVaga_id(long vaga_id) {
        this.vaga_id = vaga_id;
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

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }
}
