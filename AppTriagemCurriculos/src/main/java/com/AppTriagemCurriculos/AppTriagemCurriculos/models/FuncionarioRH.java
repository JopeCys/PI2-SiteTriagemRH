package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "funcionarioRH")
public class FuncionarioRH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionarioRhId")
    private Long id;

    @OneToMany(mappedBy = "funcionarioRh")
    private Set<Curriculo> curriculos;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setcurriculos(Set<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Set<Curriculo> getcurriculos() {
        return curriculos;
    }
}
