package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Turma;
import br.com.app01.util.Paginate;

public class TurmaDAOImpl implements TurmaDAO, Serializable {
	
	private static final long serialVersionUID = 5095426068138296439L;
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Turma insert(Turma turma) {
		em.persist(turma);
		
		return null;
	}
	
	@Transactional
	public Turma update(Turma turma) {
		em.merge(turma);
		
		return turma;
	}
	
	@Transactional
	public Turma delete(Turma turma) {
		em.remove(em.getReference(Turma.class, turma.getCodTurma()));
		
		return turma;
	}

	public List<Turma> getList() {
		TypedQuery<Turma> query = em.createQuery("SELECT t FROM turmas AS t ORDER BY t.anoTurma DESC, t.nomeTurma ASC", Turma.class);
				
		return query.getResultList();
	}
	
	public List<Turma> getPaginatedList(Paginate paginate, String palavraBusca) {
		String conditionCount = "";
		String condition = "";
		
		if (!palavraBusca.isEmpty()) {
			conditionCount = " WHERE nome_turma LIKE '%" + palavraBusca + "%' ";
			condition = " WHERE t.nomeTurma LIKE '%" + palavraBusca + "%' ";
		}
		
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM turmas" + conditionCount);
		
		paginate.setTotalNumberOfEntries(Integer.valueOf(query.getResultList().get(0).toString()));
		
		TypedQuery<Turma> query2 = em.createQuery("SELECT t FROM turmas AS t" + condition + " ORDER BY t.nomeTurma ASC", Turma.class);
		query2.setFirstResult(paginate.getFirstResult());
		query2.setMaxResults(paginate.getPagingNumberPer());
		
		return query2.getResultList();
	}

	public List<Turma> getByProperty(String whereClause, Object param) {
		TypedQuery<Turma> query = em.createQuery("SELECT t FROM turmas AS t WHERE " + whereClause + param + " ORDER BY t.anoTurma DESC, t.nomeTurma ASC", Turma.class);
		
		return query.getResultList();
	}
}
