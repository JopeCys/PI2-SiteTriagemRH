package com.AppTriagemCurriculos.AppTriagemCurriculos.models;

//imports
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity  
public class Curriculo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int curriculo_id;
	
	@NotEmpty
	private Candidato candidatos;
	
	@OneToMany
	private List<Vaga> vagas;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	@NotEmpty
	private String arquivo;

	// Getters
	public int getCurriculo_id() {
		return curriculo_id;
	}

	public Candidato getCandidatos() {
		return candidatos;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public String getArquivo() {
		return arquivo;
	}

	// Setters
	public void setCurriculo_id(int curriculo_id) {
		this.curriculo_id = curriculo_id;
	}

	public void setCandidatos(Candidato candidatos) {
		this.candidatos = candidatos;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
}
