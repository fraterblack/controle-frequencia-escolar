package br.com.app01.managedbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.MatriculaDAOImpl;
import br.com.app01.model.entities.Disciplina;
import br.com.app01.model.entities.Matricula;
import br.com.app01.util.Message;

@Named(value="gerFrequenciaPorAlunoMB")
@ViewScoped
public class GerenciarFrequenciaPorAlunoMB extends GerenciarFrequenciaMB implements Serializable {

	private static final long serialVersionUID = -1828908279719586745L;
	
	private Integer codAluno;
	
	private List<FrequenciaAlunoWrapper> listaFrequenciaAluno;
	
	@Inject
	MatriculaDAOImpl matriculaDao;

	public Integer getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}
	
	public List<Matricula> getListaMatriculas() {
		return matriculaDao.getListaMatriculas(getCodTurma());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populaFrequencia(Integer codAluno) {
		List<FrequenciaAlunoWrapper> lista = new ArrayList();
		
		if (codAluno == null) {
			setListaFrequenciaAluno(lista);
			return;
		}
		
		List<Object[]> listaFrequencia = frequenciaAlunoDAO.getFrequenciaPorAluno(codAluno);
		
		String ultimaData = "";
		for (Object[] dados: listaFrequencia) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = dados[0].toString();

			try {
				Date dataAula = formatter.parse(dateInString);
				
				if (ultimaData.equals(dados[0].toString())) {
					dataAula = null;
				}
				
				ultimaData = dados[0].toString();
				
				Disciplina d = new Disciplina();
				d.setNomeDisciplina(dados[1].toString());

				lista.add(new FrequenciaAlunoWrapper(dataAula, d, String.valueOf(dados[2].toString()), String.valueOf(dados[3].toString())));

			} catch (ParseException e) {
				Message.errorMessage("Houve um erro ao gerar o relatório: " + e.getMessage());
			}
		}
		
		setListaFrequenciaAluno(lista);
	}
	
	public void changeListener(ValueChangeEvent e) {
		if ("filtroTurma".equals(e.getComponent().getId())) {
			setCodTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			setCodAluno(null);
			setListaFrequenciaAluno(null);
		} else if ("filtroAluno".equals(e.getComponent().getId())) {
			setCodAluno((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			populaFrequencia(getCodAluno());
		}
	}
	
	public List<FrequenciaAlunoWrapper> getListaFrequenciaAluno() {
		return listaFrequenciaAluno;
	}
	public void setListaFrequenciaAluno(List<FrequenciaAlunoWrapper> listaFrequenciaAluno) {
		this.listaFrequenciaAluno = listaFrequenciaAluno;
	}
	
	public static class FrequenciaAlunoWrapper implements Serializable {

		private static final long serialVersionUID = 5112285976127151675L;
		
		private Date dataAula;
		private Disciplina disciplina;
		private String presenca;
		private String observacao;
		
		public FrequenciaAlunoWrapper(Date dataAula, Disciplina disciplina, String presenca, String observacao) {
			this.dataAula = dataAula;
			this.disciplina = disciplina;
			this.presenca = presenca;
			this.observacao = observacao;
		}

		public Date getDataAula() {
			return dataAula;
		}
		public void setDataAula(Date dataAula) {
			this.dataAula = dataAula;
		}
		
		public Disciplina getDisciplina() {
			return disciplina;
		}
		public void setDisciplina(Disciplina disciplina) {
			this.disciplina = disciplina;
		}
		
		public String getPresenca() {
			return presenca;
		}
		public void setPresenca(String presenca) {
			this.presenca = presenca;
		}
		
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
	}
}
