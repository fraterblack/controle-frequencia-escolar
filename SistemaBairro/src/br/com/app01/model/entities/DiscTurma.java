package br.com.app01.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity(name="disc_turmas")
@NamedQuery(name="disc_turmas.findAll", query="SELECT dt FROM disc_turmas AS dt")
public class DiscTurma implements Serializable {

	private static final long serialVersionUID = -2908683060627446722L;

	@Id
	@Column(name="cod_disc_turmas")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codDiscTurma;
	
	@Column(name="cod_turmas")
	private Integer codTurma;
	
	@Column(name="cod_disciplinas")
	private Integer codDisciplina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_turmas", updatable=false, insertable=false)
	private Turma turma;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_disciplinas", updatable=false, insertable=false)
	private Disciplina disciplina;

	public Integer getCodDiscTurma() {
		return codDiscTurma;
	}
	public void setCodDiscTurma(Integer codDiscTurma) {
		this.codDiscTurma = codDiscTurma;
	}
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}
	
	public Integer getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(Integer codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
