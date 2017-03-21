package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.TurmaDAOImpl;
import br.com.app01.model.entities.Turma;
import br.com.app01.util.Message;
import br.com.app01.util.Paginate;

@Named(value="cadTurmaMB")
@ViewScoped
public class CadastroTurmaMB extends Paginate implements Serializable {
	private static final long serialVersionUID = 4151734562762454821L;
	
	private Integer codTurma;
	private String nomeTurma;
	private String anoTurma;
	
	private String palavraBusca = "";
	
	private Boolean modoEdicao = false;
	
	@Inject
	TurmaDAOImpl turmaDao;

	public Integer getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getAnoTurma() {
		return anoTurma;
	}

	public void setAnoTurma(String anoTurma) {
		this.anoTurma = anoTurma;
	}

	public String getPalavraBusca() {
		return palavraBusca;
	}
	public void setPalavraBusca(String palavraBusca) {
		this.palavraBusca = palavraBusca;
	}
	
	public Boolean getModoEdicao() {
		return modoEdicao;
	}
	public void setModoEdicao(Boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public List<Turma> getListaTurmas() {

		return (List<Turma>) turmaDao.getPaginatedList(this, getPalavraBusca());
	}

	public void limpar() {
		setCodTurma(0);
		setNomeTurma(null);
		setAnoTurma(null);
		setModoEdicao(false);
	}
	
	public String gravarTurma() {
		try {
			Turma turma = new Turma();
			turma.setCodTurma(getCodTurma());
			turma.setNomeTurma(getNomeTurma());
			turma.setAnoTurma(getAnoTurma());
			
			if (turma.getCodTurma() == null || turma.getCodTurma() == 0) {
				turma.setCodTurma(null);
				
				turmaDao.insert(turma);
				
				setPalavraBusca("");
			} else {
				turmaDao.update(turma);
			}
			
			limpar();
		} catch (Exception e) {
			Message.errorMessage(e.getMessage());
		}
		
		return "";
	}
	
	public void buscar() {
		pageFirst();
	}
	
	public void editar(Turma turma) {
		setCodTurma(turma.getCodTurma());
		setNomeTurma(turma.getNomeTurma());
		setAnoTurma(turma.getAnoTurma());
		
		setModoEdicao(true);
	}
	
	public void excluir(Turma turma) {
		limpar();
		
		try {
			turmaDao.delete(turma);
			
			if (getIsLastPage() && getNumberPages() > 1) {
				pagePrevious();
			}
		} catch (Exception e) {
			Message.errorMessage("Não é possível excluir a turma");
		}
	}
}
