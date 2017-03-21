package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.DisciplinaDAOImpl;
import br.com.app01.model.entities.Disciplina;
import br.com.app01.util.Message;
import br.com.app01.util.Paginate;

@Named(value="cadDisciplinaMB")
@ViewScoped
public class CadastroDisciplinaMB extends Paginate implements Serializable {
	
	private static final long serialVersionUID = -4303491569928612986L;
	
	private Integer codDisciplina;
	private String nomeDisciplina;
	
	private String palavraBusca = "";
	
	private Boolean modoEdicao = false;
	
	@Inject
	DisciplinaDAOImpl disciplinaDao;

	public Integer getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(Integer codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
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

	public List<Disciplina> getListaDisciplinas() {
		return (List<Disciplina>) disciplinaDao.getPaginatedList(this, getPalavraBusca());
	}

	public void limpar() {
		setCodDisciplina(0);
		setNomeDisciplina(null);
		setModoEdicao(false);
	}
	
	public String gravarDisciplina() {
		try {
			Disciplina disciplina = new Disciplina();
			disciplina.setCodDisciplina(getCodDisciplina());
			disciplina.setNomeDisciplina(getNomeDisciplina());
			
			if (disciplina.getCodDisciplina() == null || disciplina.getCodDisciplina() == 0) {
				disciplina.setCodDisciplina(null);
				
				disciplinaDao.insert(disciplina);
				
				setPalavraBusca("");
			} else {
				disciplinaDao.update(disciplina);
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
	
	public void editar(Disciplina disciplina) {
		setCodDisciplina(disciplina.getCodDisciplina());
		setNomeDisciplina(disciplina.getNomeDisciplina());
		
		setModoEdicao(true);
	}
	
	public void excluir(Disciplina disciplina) {
		limpar();
		
		try {
			disciplinaDao.delete(disciplina);
			
			if (getIsLastPage() && getNumberPages() > 1) {
				pagePrevious();
			}
		} catch (Exception e) {
			Message.errorMessage("Não é possível excluir a disciplina");
		}
	}
}
