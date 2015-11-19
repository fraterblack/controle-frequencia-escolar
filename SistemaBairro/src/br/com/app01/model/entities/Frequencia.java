package br.com.app01.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity(name="frequencia")
@NamedQuery(name="frequencia.findAll", query="SELECT f FROM frequencia AS f")
public class Frequencia implements Serializable {
	
	private static final long serialVersionUID = 2639677237393132498L;

	@Id
	@Column(name="cod_chamada")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codChamada;
	
	@Column(name="cod_disc_turmas")
	private Integer codDiscTurma;
	
	@Column(name="data_aula")
	private Date dataAula;
	
	@Column(name="data_chamada")
	private Date dataChamada;
	
	@Column(name="cod_usuarios")
	private Integer codUsuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_disc_turmas", updatable=false, insertable=false)
	private DiscTurma discTurma;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cod_usuarios", updatable=false, insertable=false)
	private Usuario usuario;
	
	public Integer getCodChamada() {
		return codChamada;
	}
	public void setCodChamada(Integer codChamada) {
		this.codChamada = codChamada;
	}

	public Integer getCodDiscTurma() {
		return codDiscTurma;
	}
	public void setCodDiscTurma(Integer codDiscTurma) {
		this.codDiscTurma = codDiscTurma;
	}

	public Date getDataAula() {
		return dataAula;
	}
	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}

	public Date getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}
	public Integer getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}
	public DiscTurma getDiscTurma() {
		return discTurma;
	}
	public void setDiscTurma(DiscTurma discTurma) {
		this.discTurma = discTurma;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
