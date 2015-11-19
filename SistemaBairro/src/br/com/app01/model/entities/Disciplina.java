package br.com.app01.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name="disciplinas")
@NamedQuery(name="disciplinas.findAll", query="SELECT d FROM disciplinas AS d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 4737626840619705493L;

	@Id
	@Column(name="cod_disciplinas")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codDisciplina;
	
	@Column(name="nome_disciplina")
	private String nomeDisciplina;
	
	public Integer getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(Integer codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
}
