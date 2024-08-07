package com.models;

import java.util.List;

// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class GerenteDeptoVagas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gerente_depto_vagas_id;

    @OneToMany
    private List<Candidato> candidatos;
}
