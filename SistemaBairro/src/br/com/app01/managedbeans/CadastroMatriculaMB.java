package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.AlunoDAOImpl;
import br.com.app01.model.dao.MatriculaDAOImpl;
import br.com.app01.model.dao.TurmaDAOImpl;
import br.com.app01.model.entities.Aluno;
import br.com.app01.model.entities.Matricula;
import br.com.app01.model.entities.Turma;
import br.com.app01.util.Message;

@Named(value="cadMatriculaMB")
@ViewScoped
public class CadastroMatriculaMB implements Serializable {

	private static final long serialVersionUID = 6628514183142451488L;
	
	private Integer codMatricula;
	private Integer codAluno;
	private Integer codTurma;

	private Aluno aluno;
	private Turma turma;
	
	@Inject
	MatriculaDAOImpl matriculaDao;
	
	@Inject
	AlunoDAOImpl alunoDao;
	
	@Inject
	TurmaDAOImpl turmaDao;
	
	@PostConstruct
	public void init(){
		
	}

	public Integer getCodMatricula() {
		return codMatricula;
	}
	public void setCodMatricula(Integer codMatricula) {
		this.codMatricula = codMatricula;
	}
	
	public Integer getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}
	
	public Integer getCodTurma() {
		return codTurma;
	}
	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
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
	
	public List<Turma> getListaTurmas() {

		return (List<Turma>) turmaDao.getList();
	}
	
	public String gravarMatricula() {
		try {
			Matricula matriculaExistente = matriculaDao.getMatricula(getCodTurma(), getCodAluno());
			
			if (matriculaExistente == null) {
				Matricula matricula = new Matricula();
				matricula.setCodMatricula(null);
				matricula.setCodAluno(getCodAluno());
				matricula.setCodTurma(getCodTurma());
				
				matriculaDao.insert(matricula);
			} else {
				Message.warningMessage("Aluno já matriculado na turma");
			}
		} catch (Exception e) {
			Message.errorMessage(e.getMessage());
		}

		return "";
	}
	
	public void excluir(Matricula matricula) {
		try {
			matriculaDao.delete(matricula);
		} catch (Exception e) {
			Message.errorMessage("Não é possível desmatricular o aluno");
		}
	}
	
	public List<Matricula> getListaMatriculas() {
		return matriculaDao.getListaMatriculas(getCodTurma());
	}
	
	public List<Aluno> getListaAlunosNaoMatriculadosTurma() {
		return alunoDao.getNaoMatriculadosNaTurma(getCodTurma());
	}
	
	public void changeListener(ValueChangeEvent e) {
		if ("filtroAluno".equals(e.getComponent().getId())) {
			setCodAluno((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()): null);
		} else {
			setCodTurma((e.getNewValue() != null) ? Integer.valueOf(e.getNewValue().toString()) : null);
			
			setCodAluno(null);
		}
	}
}
