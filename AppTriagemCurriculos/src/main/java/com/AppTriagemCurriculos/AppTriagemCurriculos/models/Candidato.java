package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long candidatoId;

    @NotEmpty
    private String login;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "candidato")
    private List<Curriculo> curriculos;

    @ManyToOne
    private FuncionarioRH funcionarioRH;

    @ManyToOne
    private GerenteDeptoVagas gerenteDeptoVagas;

    // Getters
    public long getCandidato_id() {
        return candidatoId;
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

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public FuncionarioRH getFuncionarioRH() {
        return funcionarioRH;
    }

    public GerenteDeptoVagas getGerenteDeptoVagas() {
        return gerenteDeptoVagas;
    }

    // Setters
    public void setCandidato_id(long candidato_id) {
        this.candidatoId = candidato_id;
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

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public void setFuncionarioRH(FuncionarioRH funcionarioRH) {
        this.funcionarioRH = funcionarioRH;
    }

    public void setGerenteDeptoVagas(GerenteDeptoVagas gerenteDeptoVagas) {
        this.gerenteDeptoVagas = gerenteDeptoVagas;
    }

}
