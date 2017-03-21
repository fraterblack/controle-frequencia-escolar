package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.Aluno;
import br.com.app01.util.Paginate;

public interface AlunoDAO {
	
	public Aluno insert(Aluno aluno);
	
	public Aluno update(Aluno aluno);
	
	public Aluno delete(Aluno aluno);
	
	public List<Aluno> getList();
	
	public List<Aluno> getPaginatedList(Paginate paginate, String palavraBusca);
	
	public List<Aluno> getByProperty(String whereClause, Object param);
	
	public List<Aluno> getNaoMatriculadosNaTurma(Integer codMatricula);
}
