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

@Entity(name="matriculas")
@NamedQuery(name="matriculas.findAll", query="SELECT m FROM matriculas AS m")
public class Matricula implements Serializable {

	private static final long serialVersionUID = 5332056924639317490L;

	@Id
	@Column(name="cod_matriculas")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codMatricula;
	
	@Column(name="cod_turmas")
	private Integer codTurma;
	
	@Column(name="cod_alunos")
	private Integer codAluno;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_alunos", updatable=false, insertable=false)
	private Aluno aluno;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_turmas", updatable=false, insertable=false)
	private Turma turma;

	public Integer getCodMatricula() {
		return codMatricula;
	}
	public void setCodMatricula(Integer codMatricula) {
		this.codMatricula = codMatricula;
	}
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}
	
	public Integer getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}

	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
