package com.models;

//imports
import java.util.List;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

public class Curriculo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int curriculo_id;
	
	@OneToMany
	private List<Candidato> candidatos;
	
	@OneToMany
	private List<Vaga> vagas;
	
	@NotEmpty
	private String arquivo;

	
	public int getCurriculo_id() {
		return curriculo_id;
	}

	public void setCurriculo_id(int curriculo_id) {
		this.curriculo_id = curriculo_id;
	}

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
