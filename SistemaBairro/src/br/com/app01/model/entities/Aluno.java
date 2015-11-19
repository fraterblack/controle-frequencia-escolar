package br.com.app01.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name="alunos")
@NamedQuery(name="alunos.findAll", query="SELECT a FROM alunos AS a")
public class Aluno implements Serializable {

	private static final long serialVersionUID = -6715419597399816631L;

	@Id
	@Column(name="cod_alunos")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAluno;
	
	@Column(name="nome_aluno")
	private String nomeAluno;
	
	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	@Column(name="doc_id")
	private String docId;
	

	public Integer getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
}
