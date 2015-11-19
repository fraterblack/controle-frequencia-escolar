package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.Turma;
import br.com.app01.util.Paginate;

public interface TurmaDAO {
	
	public Turma insert(Turma turma);
	
	public Turma update(Turma turma);
	
	public Turma delete(Turma turma);
	
	public List<Turma> getList();
	
	public List<Turma> getPaginatedList(Paginate paginate, String palavraBusca);
	
	public List<Turma> getByProperty(String whereClause, Object param);
}
