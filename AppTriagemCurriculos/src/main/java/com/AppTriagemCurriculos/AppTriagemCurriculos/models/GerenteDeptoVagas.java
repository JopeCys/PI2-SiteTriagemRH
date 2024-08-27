package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gerenteDeptoVagas")
public class GerenteDeptoVagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gerenteDeptoVagasId")
    private Long id;
    
    @OneToMany(mappedBy = "gerenteDeptoVagas")
    private Set<Vaga> vagas;
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setVagas(Set<Vaga> vagas) {
        this.vagas = vagas;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Set<Vaga> getVagas() {
        return vagas;
    }
}
