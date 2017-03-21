package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Disciplina;
import br.com.app01.util.Paginate;

public class DisciplinaDAOImpl implements DisciplinaDAO, Serializable {
	
	private static final long serialVersionUID = -6748077270777515865L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Disciplina insert(Disciplina disciplina) {
		em.persist(disciplina);
		
		return null;
	}
	
	@Transactional
	public Disciplina update(Disciplina disciplina) {
		em.merge(disciplina);
		
		return disciplina;
	}
	
	@Transactional
	public Disciplina delete(Disciplina disciplina) {
		em.remove(em.getReference(Disciplina.class, disciplina.getCodDisciplina()));
		
		return disciplina;
	}

	public List<Disciplina> getList() {
		TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM disciplinas AS d ORDER BY d.nomeDisciplina ASC", Disciplina.class);
				
		return query.getResultList();
	}
	
	public List<Disciplina> getPaginatedList(Paginate paginate, String palavraBusca) {
		String conditionCount = "";
		String condition = "";
		
		if (!palavraBusca.isEmpty()) {
			conditionCount = " WHERE nome_disciplina LIKE '%" + palavraBusca + "%' ";
			condition = " WHERE d.nomeDisciplina LIKE '%" + palavraBusca + "%' ";
		}
		
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM disciplinas" + conditionCount);
		
		paginate.setTotalNumberOfEntries(Integer.valueOf(query.getResultList().get(0).toString()));
		
		TypedQuery<Disciplina> query2 = em.createQuery("SELECT d FROM disciplinas AS d" + condition + " ORDER BY d.nomeDisciplina ASC", Disciplina.class);
		query2.setFirstResult(paginate.getFirstResult());
		query2.setMaxResults(paginate.getPagingNumberPer());
		
		return query2.getResultList();
	}

	public List<Disciplina> getByProperty(String whereClause, Object param) {
		TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM disciplinas AS d WHERE " + whereClause + param + " ORDER BY d.nomeDisciplina ASC", Disciplina.class);
		
		return query.getResultList();
	}
	
	public List<Disciplina> getNaoLigadasATurma(Integer codDiscTurma) {
		TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM disciplinas AS d WHERE NOT EXISTS(SELECT dt.codDisciplina FROM disc_turmas AS dt WHERE dt.codTurma=" + codDiscTurma + " AND dt.codDisciplina=d.codDisciplina) ORDER BY d.nomeDisciplina ASC", Disciplina.class);
		
		return query.getResultList();
	}
}
