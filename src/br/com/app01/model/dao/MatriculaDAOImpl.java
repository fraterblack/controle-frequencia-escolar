package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Matricula;

public class MatriculaDAOImpl implements MatriculaDAO, Serializable {
	
	private static final long serialVersionUID = 6360373482232946749L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Matricula insert(Matricula matricula) {
		em.persist(matricula);
		
		return null;
	}
	
	@Transactional
	public Matricula update(Matricula matricula) {
		em.merge(matricula);
		
		return matricula;
	}
	
	@Transactional
	public Matricula delete(Matricula matricula) {
		em.remove(em.getReference(Matricula.class, matricula.getCodMatricula()));
		
		return matricula;
	}

	public List<Matricula> getList() {
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM matriculas AS m", Matricula.class);
				
		return query.getResultList();
	}

	public List<Matricula> getByProperty(String whereClause, Object param) {
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM matriculas AS m WHERE " + whereClause + param, Matricula.class);
		
		return query.getResultList();
	}
	
	public List<Matricula> getListaMatriculas(Integer codTurma) {
		TypedQuery<Matricula> query = em.createQuery("SELECT m "
				+ "FROM matriculas AS m, alunos AS a "
				+ "WHERE a.codAluno=m.codAluno AND m.codTurma=:codTurma "
				+ "ORDER BY a.nomeAluno ASC", Matricula.class);
		query.setParameter("codTurma", codTurma);
		
		return query.getResultList();
	}
	
	public Matricula getMatricula(Integer codTurma, Integer codAluno) {
		TypedQuery<Matricula> query = em.createQuery("SELECT m FROM matriculas AS m WHERE codTurma=:codTurma AND codAluno=:codAluno", Matricula.class);
		query.setParameter("codTurma", codTurma);
		query.setParameter("codAluno", codAluno);
		
		List<Matricula> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public Matricula getMatricula(Integer codMatricula) {
		TypedQuery<Matricula> query = em.createQuery("SELECT m "
				+ "FROM matriculas AS m "
				+ "WHERE codTurma=:codMatricula", Matricula.class);
		query.setParameter("codMatricula", codMatricula);
		
		List<Matricula> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
}
