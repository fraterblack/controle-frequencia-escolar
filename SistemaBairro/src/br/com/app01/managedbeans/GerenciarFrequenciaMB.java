package br.com.app01.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.DiscTurmaDAOImpl;
import br.com.app01.model.dao.FrequenciaAlunoDAOImpl;
import br.com.app01.model.dao.TurmaDAOImpl;
import br.com.app01.model.entities.Aluno;
import br.com.app01.model.entities.DiscTurma;
import br.com.app01.model.entities.Turma;

@Named(value="gerFrequenciaMB")
@ViewScoped
public class GerenciarFrequenciaMB implements Serializable {
	private static final long serialVersionUID = 3797563601428491307L;
	
	private Integer codTurma;
	private Integer codDiscTurma;
	
	private List<FrequenciaWrapper> listaFrequencia;
	
	@Inject
	protected TurmaDAOImpl turmaDAO;
	
	@Inject
	protected DiscTurmaDAOImpl discTurmaDao;
	
	@Inject
	protected FrequenciaAlunoDAOImpl frequenciaAlunoDAO;
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}

	public Integer getCodDiscTurma() {
		return codDiscTurma;
	}
	public void setCodDiscTurma(Integer codDiscTurma) {
		this.codDiscTurma = codDiscTurma;
	}
	public List<Turma> getListaDeTurmas() {
		return turmaDAO.getList();
	}
	
	public List<DiscTurma> getListaDisciplinasDaTurma() {
		return discTurmaDao.getListaDisciplinasDaTurma(getCodTurma());
	}
	
	public List<FrequenciaWrapper> getListaFrequencia() {
		return listaFrequencia;
	}
	public void setListaFrequencia(List<FrequenciaWrapper> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;
	}
	
	public static class FrequenciaWrapper implements Serializable {
		private static final long serialVersionUID = 1803232252497798926L;
		
		private Aluno aluno;
		private Integer qtPresencas;
		private Integer qtdeFaltas;
		private Integer qtdeFaltasJustificadas;
		private BigDecimal frequencia;
		private Integer qtTotalAulas;
		
		public FrequenciaWrapper(Aluno aluno, Integer qtPresencas, Integer qtdeFaltas, Integer qtdeFaltasJustificadas, Integer qtTotalAulas) {
			this.aluno = aluno;
			this.qtPresencas = qtPresencas;
			this.qtdeFaltas = qtdeFaltas;
			this.qtdeFaltasJustificadas = qtdeFaltasJustificadas;
			this.qtTotalAulas = qtTotalAulas;
			
			this.frequencia = this.calculaFrequencia(qtTotalAulas, qtPresencas, qtdeFaltasJustificadas);
		}
		
		private BigDecimal calculaFrequencia(Integer qtTotalAulas, Integer qtPresencas, Integer qtdeFaltasJustificadas) {
			BigDecimal qtPresencasBD = new BigDecimal(qtPresencas);
			BigDecimal qtFaltasJustificadasBD = new BigDecimal(qtdeFaltasJustificadas);
			BigDecimal qtTotalAulasBD = new BigDecimal(qtTotalAulas);
			
			return ((qtPresencasBD.add(qtFaltasJustificadasBD)).divide(qtTotalAulasBD, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP));
		}
		
		public Aluno getAluno() {
			return aluno;
		}
		public void setAluno(Aluno aluno) {
			this.aluno = aluno;
		}
		
		public Integer getQtPresencas() {
			return qtPresencas;
		}
		public void setQtPresencas(Integer qtPresencas) {
			this.qtPresencas = qtPresencas;
		}
		
		public Integer getQtdeFaltas() {
			return qtdeFaltas;
		}
		public void setQtdeFaltas(Integer qtdeFaltas) {
			this.qtdeFaltas = qtdeFaltas;
		}
		
		public Integer getQtdeFaltasJustificadas() {
			return qtdeFaltasJustificadas;
		}
		public void setQtdeFaltasJustificadas(Integer qtdeFaltasJustificadas) {
			this.qtdeFaltasJustificadas = qtdeFaltasJustificadas;
		}

		public BigDecimal getFrequencia() {
			return frequencia;
		}
		public void setFrequencia(BigDecimal frequencia) {
			this.frequencia = frequencia;
		}
		
		public Integer getQtTotalAulas() {
			return qtTotalAulas;
		}
		public void setQtTotalAulas(Integer qtTotalAulas) {
			this.qtTotalAulas = qtTotalAulas;
		}
	}
}
