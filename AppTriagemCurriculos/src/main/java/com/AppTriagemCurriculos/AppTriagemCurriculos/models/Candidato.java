package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

// Imports
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    private List<Curriculo> curriculo;


    // Getters 
    public long getCandidato_id() {
        return candidato_id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Curriculo> getCurriculo() {
        return curriculo;
    }

    // Setters
    public void setCandidato_id(long candidato_id) {
        this.candidato_id = candidato_id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurriculo(List<Curriculo> curriculo) {
        this.curriculo = curriculo;
    }
}
