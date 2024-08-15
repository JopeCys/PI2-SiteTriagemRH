package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vagaId")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "area", nullable = false)
    private String area;

    @ManyToOne
    @JoinColumn(name = "gerenteDeptoVagasId")
    private GerenteDeptoVagas gerenteDeptoVagas;

    @OneToMany(mappedBy = "vaga")
    private Set<Curriculo> curriculos;

    
    // Getters
    public Long getId() {
        return id;
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

    public GerenteDeptoVagas getGerenteDeptoVagas() {
        return gerenteDeptoVagas;
    }

    public Set<Curriculo> getCurriculos() {
        return curriculos;
    }

    
    // Setters
    public void setId(Long id) {
        this.id = id;
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
    
    public void setGerenteDeptoVagas(GerenteDeptoVagas gerenteDeptoVagas) {
        this.gerenteDeptoVagas = gerenteDeptoVagas;
    }
    
    public void setCurriculos(Set<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }
}

