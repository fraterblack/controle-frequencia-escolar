package br.com.app01.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name="turmas")
@NamedQuery(name="turmas.findAll", query="SELECT t FROM turmas AS t")
public class Turma implements Serializable {

	private static final long serialVersionUID = 3724314555395869869L;

	@Id
	@Column(name="cod_turmas")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codTurma;
	
	@Column(name="nome_turma")
	private String nomeTurma;
	
	@Column(name="ano_turma")
	private String anoTurma;
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getAnoTurma() {
		return anoTurma;
	}
	public void setAnoTurma(String anoTurma) {
		this.anoTurma = anoTurma;
	}
	
}
