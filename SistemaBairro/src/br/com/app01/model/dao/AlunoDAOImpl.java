package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Aluno;
import br.com.app01.util.Paginate;

public class AlunoDAOImpl implements AlunoDAO, Serializable {
	
	private static final long serialVersionUID = 9199393984593957240L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Aluno insert(Aluno aluno) {
		em.persist(aluno);
		
		return null;
	}
	
	@Transactional
	public Aluno update(Aluno aluno) {
		em.merge(aluno);
		
		return aluno;
	}
	
	@Transactional
	public Aluno delete(Aluno aluno) {
		em.remove(em.getReference(Aluno.class, aluno.getCodAluno()));
		
		return aluno;
	}

	public List<Aluno> getList() {
		TypedQuery<Aluno> query = em.createQuery("SELECT a FROM alunos AS a ORDER BY a.nomeAluno ASC", Aluno.class);
				
		return query.getResultList();
	}
	
	public List<Aluno> getPaginatedList(Paginate paginate, String palavraBusca) {
		String conditionCount = "";
		String condition = "";
		
		if (!palavraBusca.isEmpty()) {
			conditionCount = " WHERE nome_aluno LIKE '%" + palavraBusca + "%' ";
			condition = " WHERE a.nomeAluno LIKE '%" + palavraBusca + "%' ";
		}
		
		Query query = em.createNativeQuery("SELECT COUNT(*) FROM alunos" + conditionCount);
		
		paginate.setTotalNumberOfEntries(Integer.valueOf(query.getResultList().get(0).toString()));
		
		TypedQuery<Aluno> query2 = em.createQuery("SELECT a FROM alunos AS a" + condition + " ORDER BY a.nomeAluno ASC", Aluno.class);
		query2.setFirstResult(paginate.getFirstResult());
		query2.setMaxResults(paginate.getPagingNumberPer());
		
		return query2.getResultList();
	}

	public List<Aluno> getByProperty(String whereClause, Object param) {
		TypedQuery<Aluno> query = em.createQuery("SELECT a FROM alunos AS a WHERE " + whereClause + param + " ORDER BY a.nomeAluno ASC", Aluno.class);
		
		return query.getResultList();
	}
	
	public List<Aluno> getNaoMatriculadosNaTurma(Integer codMatricula) {
		TypedQuery<Aluno> query = em.createQuery("SELECT a FROM alunos AS a WHERE NOT EXISTS(SELECT m.codAluno FROM matriculas AS m WHERE m.codTurma=" + codMatricula + " AND m.codAluno=a.codAluno) ORDER BY a.nomeAluno ASC", Aluno.class);
		
		return query.getResultList();
	}
}
