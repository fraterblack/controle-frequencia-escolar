package br.com.app01.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity(name="frequencia_aluno")
@NamedQuery(name="frequencia_aluno.findAll", query="SELECT fa FROM frequencia_aluno AS fa")
public class FrequenciaAluno implements Serializable {
	
	private static final long serialVersionUID = -6754145475102331650L;

	@Id
	@Column(name="cod_chamada")
	private Integer codChamada;
	
	@Id
	@Column(name="cod_alunos")
	private Integer codAluno;
	
	@Column(name="presente")
	private String presente;
	
	@Column(name="falta_justificada")
	private String faltaJustificada;
	
	@Column(name="observacao")
	private String observacao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_alunos", updatable=false, insertable=false)
	private Aluno aluno;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_chamada", updatable=false, insertable=false)
	private Frequencia frequencia;
	
	public FrequenciaAluno() {
	}
			
	public FrequenciaAluno(Integer codAluno, Integer codChamada, String presente, String faltaJustificada, String observacao) {
		this.codAluno = codAluno;
		this.codChamada = codChamada;
		this.presente = presente;
		this.faltaJustificada = faltaJustificada;
		this.observacao = observacao;
	}

	public Integer getCodChamada() {
		return codChamada;
	}
	public void setCodChamada(Integer codChamada) {
		this.codChamada = codChamada;
	}

	public Integer getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}

	public String getPresente() {
		return presente;
	}
	public void setPresente(String presente) {
		this.presente = presente;
	}

	public String getFaltaJustificada() {
		return faltaJustificada;
	}
	public void setFaltaJustificada(String faltaJustificada) {
		this.faltaJustificada = faltaJustificada;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Frequencia getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}
}
