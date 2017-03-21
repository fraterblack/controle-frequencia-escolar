package br.com.app01.model.dao;

import java.util.Date;
import java.util.List;

import br.com.app01.model.entities.Frequencia;

public interface FrequenciaDAO {
	
	public Frequencia insert(Frequencia frequencia);
	
	public Frequencia update(Frequencia frequencia);
	
	public Frequencia delete(Frequencia frequencia);
	
	public List<Frequencia> getList();
	
	public List<Frequencia> getByProperty(String whereClause, Object param);
	
	public List<Frequencia> getByProperty(String whereClause, Object param, String order);
	
	public Frequencia getChamada(Integer codDiscTurma, Date dataAula);
	
	public Frequencia getChamada(Integer codChamada);
}
