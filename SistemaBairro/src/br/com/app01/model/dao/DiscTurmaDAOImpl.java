package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.DiscTurma;

public class DiscTurmaDAOImpl implements DiscTurmaDAO, Serializable {

	private static final long serialVersionUID = 5838504507318177814L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public DiscTurma insert(DiscTurma discTurma) {
		em.persist(discTurma);
		
		return null;
	}
	
	@Transactional
	public DiscTurma update(DiscTurma discTurma) {
		em.merge(discTurma);
		
		return discTurma;
	}
	
	@Transactional
	public DiscTurma delete(DiscTurma discTurma) {
		em.remove(em.getReference(DiscTurma.class, discTurma.getCodDiscTurma()));
		
		return discTurma;
	}

	public List<DiscTurma> getList() {
		TypedQuery<DiscTurma> query = em.createQuery("SELECT dt FROM disc_turmas AS dt", DiscTurma.class);
				
		return query.getResultList();
	}

	public List<DiscTurma> getByProperty(String whereClause, Object param) {
		TypedQuery<DiscTurma> query = em.createQuery("SELECT dt FROM disc_turmas AS dt WHERE " + whereClause + param, DiscTurma.class);
		
		return query.getResultList();
	}
	
	public List<DiscTurma> getListaDisciplinasDaTurma(Integer codTurma) {
		TypedQuery<DiscTurma> query = em.createQuery("SELECT dt "
				+ "FROM disc_turmas AS dt, disciplinas AS d "
				+ "WHERE d.codDisciplina=dt.codDisciplina AND dt.codTurma=:codTurma "
				+ "ORDER BY d.nomeDisciplina", DiscTurma.class);
		query.setParameter("codTurma", codTurma);

		return query.getResultList();
	}
	
	public DiscTurma getDiscTurma(Integer codDiscTurma, Integer codDisciplina) {
		TypedQuery<DiscTurma> query = em.createQuery("SELECT dt "
				+ "FROM disc_turmas AS dt "
				+ "WHERE codDiscTurma=:codDiscTurma AND codDisciplina=:codDisciplina", DiscTurma.class);
		query.setParameter("codDiscTurma", codDiscTurma);
		query.setParameter("codDisciplina", codDisciplina);
		
		List<DiscTurma> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public DiscTurma getDiscTurma(Integer codDiscTurma) {
		TypedQuery<DiscTurma> query = em.createQuery("SELECT dt "
				+ "FROM disc_turmas AS dt "
				+ "WHERE codDiscTurma=:codDiscTurma", DiscTurma.class);
		query.setParameter("codDiscTurma", codDiscTurma);
		
		List<DiscTurma> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
}
