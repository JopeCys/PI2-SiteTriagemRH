package com.models;

// Imports
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Candidato 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long candidato_id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;
}
