package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidatoId")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "funcionarioRhId")
    private FuncionarioRh funcionarioRh;
    
    // Setters
    public void setId(Long id) {
        this.id = id;
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

    public void setFuncionarioRh(FuncionarioRh funcionarioRh) {
        this.funcionarioRh = funcionarioRh;
    }

    public void setCurriculos(Set<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }
    
    // Getters
    public Long getId() {
        return id;
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

    public FuncionarioRh getFuncionarioRh() {
        return funcionarioRh;
    }

    public Set<Curriculo> getCurriculos() {
        return curriculos;
    }
    @OneToMany(mappedBy = "candidato")
    private Set<Curriculo> curriculos;
}
