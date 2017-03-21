package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.FrequenciaAluno;

public interface FrequenciaAlunoDAO {
	
	public FrequenciaAluno insert(FrequenciaAluno frequenciaAluno);
	
	public FrequenciaAluno update(FrequenciaAluno frequenciaAluno);
	
	public FrequenciaAluno delete(FrequenciaAluno frequenciaAluno);
	
	public List<FrequenciaAluno> getList();
	
	public List<FrequenciaAluno> getByProperty(String whereClause, Object param);
	
	public List<Object[]> getFrequenciaPorDisciplina(Integer codDiscTurma);
	
	public List<Object[]> getFrequenciaPorTurma(Integer codTurma);
	
	public List<Object[]> getFrequenciaPorAluno(Integer codAluno);
}
