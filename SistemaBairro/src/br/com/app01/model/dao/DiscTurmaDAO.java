package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.DiscTurma;

public interface DiscTurmaDAO {
	
	public DiscTurma insert(DiscTurma discTurma);
	
	public DiscTurma update(DiscTurma discTurma);
	
	public DiscTurma delete(DiscTurma discTurma);
	
	public List<DiscTurma> getList();
	
	public List<DiscTurma> getByProperty(String whereClause, Object param);
	
	public DiscTurma getDiscTurma(Integer codTurma, Integer codDisciplina);
	
	public DiscTurma getDiscTurma(Integer codDiscTurma);
}
