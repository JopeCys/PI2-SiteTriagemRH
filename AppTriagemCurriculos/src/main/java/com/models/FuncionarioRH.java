package com.models;

// Imports
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class FuncionarioRH 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long funcionario_rh_id;

    @OneToMany
    private List<Candidato> candidatos;
}
