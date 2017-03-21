package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.Disciplina;
import br.com.app01.util.Paginate;

public interface DisciplinaDAO {
	
	public Disciplina insert(Disciplina disciplina);
	
	public Disciplina update(Disciplina disciplina);
	
	public Disciplina delete(Disciplina disciplina);
	
	public List<Disciplina> getList();
	
	public List<Disciplina> getPaginatedList(Paginate paginate, String palavraBusca);
	
	public List<Disciplina> getByProperty(String whereClause, Object param);
	
	public List<Disciplina> getNaoLigadasATurma(Integer codDiscTurma);
}
