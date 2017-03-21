package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.Matricula;

public interface MatriculaDAO {
	
	public Matricula insert(Matricula matricula);
	
	public Matricula update(Matricula matricula);
	
	public Matricula delete(Matricula matricula);
	
	public List<Matricula> getList();
	
	public List<Matricula> getByProperty(String whereClause, Object param);
	
	public Matricula getMatricula(Integer codTurma, Integer codAluno);
	
	public Matricula getMatricula(Integer codMatricula);
}
