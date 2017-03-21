package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Frequencia;

public class FrequenciaDAOImpl implements FrequenciaDAO, Serializable {
	
	private static final long serialVersionUID = -6000082227653190205L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Frequencia insert(Frequencia frequencia) {
		em.persist(frequencia);
		
		return null;
	}
	
	@Transactional
	public Frequencia update(Frequencia frequencia) {
		em.merge(frequencia);
		
		return frequencia;
	}
	
	@Transactional
	public Frequencia delete(Frequencia frequencia) {
		em.remove(em.getReference(Frequencia.class, frequencia.getCodChamada()));
		
		return frequencia;
	}

	public List<Frequencia> getList() {
		TypedQuery<Frequencia> query = em.createQuery("SELECT f FROM frequencia AS f", Frequencia.class);
				
		return query.getResultList();
	}

	public List<Frequencia> getByProperty(String whereClause, Object param) {
		TypedQuery<Frequencia> query = em.createQuery("SELECT f FROM frequencia AS f WHERE " + whereClause + param, Frequencia.class);
		
		return query.getResultList();
	}
	
	public List<Frequencia> getByProperty(String whereClause, Object param, String order) {
		TypedQuery<Frequencia> query = em.createQuery("SELECT f FROM frequencia AS f WHERE " + whereClause + param + 
				" ORDER BY " + order, Frequencia.class);
		
		return query.getResultList();
	}
	
	public Frequencia getChamada(Integer codDiscTurma, Date dataAula) {
		TypedQuery<Frequencia> query = em.createQuery("SELECT f FROM frequencia AS f WHERE f.codDiscTurma=:codDiscTurma AND f.dataAula=:dataAula", Frequencia.class);
		query.setParameter("codDiscTurma", codDiscTurma);
		//query.setParameter("dataAula", dataAula);
		query.setParameter("dataAula", dataAula, TemporalType.DATE);
		
		List<Frequencia> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public Frequencia getChamada(Integer codChamada) {
		TypedQuery<Frequencia> query = em.createQuery("SELECT f FROM frequencia AS f WHERE f.codChamada=:codChamada", Frequencia.class);
		query.setParameter("codChamada", codChamada);
		
		List<Frequencia> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
}
