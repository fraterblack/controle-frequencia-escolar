package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.FrequenciaAluno;

public class FrequenciaAlunoDAOImpl implements FrequenciaAlunoDAO, Serializable {

	private static final long serialVersionUID = -6781954782493698484L;

	@Produces
	@PersistenceContext(unitName = "gerenciadorDS")
	private EntityManager em;

	@Transactional
	public FrequenciaAluno insert(FrequenciaAluno frequenciaAluno) {
		em.persist(frequenciaAluno);

		return null;
	}

	@Transactional
	public FrequenciaAluno update(FrequenciaAluno frequenciaAluno) {
		em.merge(frequenciaAluno);

		return frequenciaAluno;
	}

	@Transactional
	public FrequenciaAluno delete(FrequenciaAluno frequenciaAluno) {
		em.remove(em.getReference(FrequenciaAluno.class,
				frequenciaAluno.getCodChamada()));

		return frequenciaAluno;
	}

	@Transactional
	public List<FrequenciaAluno> getList() {
		TypedQuery<FrequenciaAluno> query = em.createQuery(
				"SELECT fa FROM frequencia_aluno AS fa", FrequenciaAluno.class);

		return query.getResultList();
	}

	@Transactional
	public List<FrequenciaAluno> getByProperty(String whereClause, Object param) {
		TypedQuery<FrequenciaAluno> query = em.createQuery(
				"SELECT fa FROM frequencia_aluno AS fa WHERE " + whereClause
						+ param, FrequenciaAluno.class);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> getFrequenciaPorDisciplina(Integer codDiscTurma) {
		Query query = em.createNativeQuery("SELECT a.nome_aluno AS nome, "
				+ "SUM(IF(fa.presente='s', 1, 0)) AS qtP, "
				+ "SUM(IF(fa.presente='n' && fa.falta_justificada='n', 1, 0)) AS qtF, "
				+ "SUM(IF(fa.falta_justificada='s', 1, 0)) AS qtFJ, "
				+ "COUNT(f.cod_chamada) AS total "
				+ "FROM frequencia AS f "
				+ "INNER JOIN frequencia_aluno AS fa ON fa.cod_chamada=f.cod_chamada "
				+ "INNER JOIN alunos AS a ON a.cod_alunos=fa.cod_alunos "
				+ "WHERE f.cod_disc_turmas=" + codDiscTurma +" GROUP BY a.cod_alunos ORDER BY nome");
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> getFrequenciaPorTurma(Integer codTurma) {
		Query query = em.createNativeQuery("SELECT a.nome_aluno AS nome, "
				+ "SUM(IF(fa.presente='s', 1, 0)) AS qtP, "
				+ "SUM(IF(fa.presente='n' && fa.falta_justificada='n', 1, 0)) AS qtF, "
				+ "SUM(IF(fa.falta_justificada='s', 1, 0)) AS qtFJ, "
				+ "COUNT(f.cod_chamada) AS total "
				+ "FROM frequencia AS f "
				+ "INNER JOIN frequencia_aluno AS fa ON fa.cod_chamada=f.cod_chamada "
				+ "INNER JOIN disc_turmas AS dt ON dt.cod_disc_turmas=f.cod_disc_turmas "
				+ "INNER JOIN alunos AS a ON a.cod_alunos=fa.cod_alunos "
				+ "WHERE dt.cod_turmas=" + codTurma +" GROUP BY a.cod_alunos ORDER BY nome");
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> getFrequenciaPorAluno(Integer codAluno) {
		Query query = em.createNativeQuery("SELECT f.data_aula, "
				+ "d.nome_disciplina AS disciplina, "
				+ "IF(fa.presente='s', 'p', IF(fa.falta_justificada='s', 'fj', 'f')) AS presenca, "
				+ "fa.observacao "
				+ "FROM frequencia AS f "
				+ "INNER JOIN frequencia_aluno AS fa ON fa.cod_chamada=f.cod_chamada "
				+ "INNER JOIN disc_turmas AS dt ON dt.cod_disc_turmas=f.cod_disc_turmas "
				+ "INNER JOIN disciplinas AS d ON d.cod_disciplinas=dt.cod_disciplinas "
				+ "WHERE fa.cod_alunos=" + codAluno +" GROUP BY f.cod_disc_turmas, f.data_aula ORDER BY f.data_aula DESC, d.nome_disciplina ASC");
		
		return query.getResultList();
	}
}
