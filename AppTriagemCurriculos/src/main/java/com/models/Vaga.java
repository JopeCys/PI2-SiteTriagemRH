package com.models;

import java.io.Serializable;

// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vaga implements Serializable{
    
    // Controle de versionamento
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vaga_id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String area;
}
