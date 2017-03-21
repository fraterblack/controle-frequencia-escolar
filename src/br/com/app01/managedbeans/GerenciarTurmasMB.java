package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.DisciplinaDAOImpl;
import br.com.app01.model.dao.DiscTurmaDAOImpl;
import br.com.app01.model.dao.TurmaDAOImpl;
import br.com.app01.model.entities.Disciplina;
import br.com.app01.model.entities.DiscTurma;
import br.com.app01.model.entities.Turma;
import br.com.app01.util.Message;

@Named(value="gerTurmasMB")
@ViewScoped
public class GerenciarTurmasMB implements Serializable {
	
	private static final long serialVersionUID = -2229267829337133393L;
	
	private Integer codDiscTurma;
	private Integer codDisciplina;
	private Integer codTurma;

	private Disciplina disciplina;
	private Turma turma;
	
	@Inject
	DiscTurmaDAOImpl discTurmaDao;
	
	@Inject
	DisciplinaDAOImpl disciplinaDao;
	
	@Inject
	TurmaDAOImpl turmaDao;
	
	@PostConstruct
	public void init(){
		
	}

	public Integer getCodDiscTurma() {
		return codDiscTurma;
	}
	public void setCodDiscTurma(Integer codDiscTurma) {
		this.codDiscTurma = codDiscTurma;
	}
	
	public Integer getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(Integer codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
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
	
	public List<Turma> getListaTurmas() {

		return (List<Turma>) turmaDao.getList();
	}
	
	public String gravarDiscTurma() {
		try {
			DiscTurma discTurmaExistente = discTurmaDao.getDiscTurma(getCodTurma(), getCodDisciplina());
			
			if (discTurmaExistente == null) {
				DiscTurma discTurma = new DiscTurma();
				discTurma.setCodDiscTurma(null);
				discTurma.setCodDisciplina(getCodDisciplina());
				discTurma.setCodTurma(getCodTurma());
				
				discTurmaDao.insert(discTurma);
			} else {
				Message.warningMessage("Disciplina já ligada a turma");
			}
		} catch (Exception e) {
			Message.errorMessage(e.getMessage());
		}

		return "";
	}
	
	public void excluir(DiscTurma discTurma) {
		try {
			discTurmaDao.delete(discTurma);
		} catch (Exception e) {
			Message.errorMessage("Não é possível desligar a disciplina da turma");
		}
	}
	
	public List<DiscTurma> getListaDisciplinasDaTurma() {
		return discTurmaDao.getListaDisciplinasDaTurma(getCodTurma());
	}
	
	public List<Disciplina> getListaDisciplinasNaoLigadasATurma() {
		return disciplinaDao.getNaoLigadasATurma(getCodTurma());
	}
	
	public void changeListener(ValueChangeEvent e) {
		if ("filtroDisciplina".equals(e.getComponent().getId())) {
			setCodDisciplina((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()): null);
		} else {
			setCodTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			setCodDisciplina(null);
		}
	}
}
