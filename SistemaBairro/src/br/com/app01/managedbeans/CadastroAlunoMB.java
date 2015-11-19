package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.AlunoDAOImpl;
import br.com.app01.model.entities.Aluno;
import br.com.app01.util.Message;
import br.com.app01.util.Paginate;

@Named(value="cadAlunoMB")
@ViewScoped
public class CadastroAlunoMB extends Paginate implements Serializable {
	private static final long serialVersionUID = -7474521008260877419L;
	
	private Integer codAluno;
	private String nomeAluno;
	private Date dataNascimento;
	private String docId;
	
	private String palavraBusca = "";
	
	private Boolean modoEdicao = false;
	
	@Inject
	AlunoDAOImpl alunoDao;

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
		
	public List<Aluno> getListaAlunos() {
		return (List<Aluno>) alunoDao.getPaginatedList(this, getPalavraBusca());
	}

	public void limpar() {
		setCodAluno(0);
		setNomeAluno(null);
		setDataNascimento(null);
		setDocId(null);
		setModoEdicao(false);
	}
	
	public String gravarAluno() {
		if (getDataNascimento().before(new Date())) {
			try {
				Aluno aluno = new Aluno();
				aluno.setCodAluno(getCodAluno());
				aluno.setNomeAluno(getNomeAluno());
				aluno.setDataNascimento(getDataNascimento());
				aluno.setDocId(getDocId());
				
				if (aluno.getCodAluno() == null || aluno.getCodAluno() == 0) {
					aluno.setCodAluno(null);
					
					alunoDao.insert(aluno);
					
					setPalavraBusca("");
				} else {
					alunoDao.update(aluno);
				}
				
				limpar();
			} catch (Exception e) {
				Message.errorMessage(e.getMessage());
			}
		} else {
			Message.warningMessage("Data de nascimento inválida");
		}

		return "";
	}
	
	public void buscar() {
		pageFirst();
	}
	
	public void editar(Aluno aluno) {
		setCodAluno(aluno.getCodAluno());
		setNomeAluno(aluno.getNomeAluno());
		setDataNascimento(aluno.getDataNascimento());
		setDocId(aluno.getDocId());
		
		setModoEdicao(true);
	}
	
	public void excluir(Aluno aluno) {
		limpar();
		
		try {
			alunoDao.delete(aluno);
			
			if (getIsLastPage() && getNumberPages() > 1) {
				pagePrevious();
			}
		} catch (Exception e) {
			Message.errorMessage("Não é possível excluir o aluno");
		}
	}
}
