package br.com.app01.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.app01.model.entities.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO, Serializable {
	
	private static final long serialVersionUID = -3608014446341606362L;
	
	@Produces
	@PersistenceContext(unitName="gerenciadorDS")
	private EntityManager em;

	@Transactional
	public Usuario insert(Usuario usuario) {
		em.persist(usuario);
		
		return null;
	}
	
	@Transactional
	public Usuario update(Usuario usuario) {
		em.merge(usuario);
		
		return usuario;
	}
	
	@Transactional
	public Usuario delete(Usuario usuario) {
		em.remove(em.getReference(Usuario.class, usuario.getCodUsuario()));
		
		return usuario;
	}

	public List<Usuario> getList() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u ORDER BY u.nome ASC", Usuario.class);
				
		return query.getResultList();
	}

	public List<Usuario> getByProperty(String whereClause, Object param) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u WHERE " + whereClause + param + " ORDER BY u.nome ASC", Usuario.class);
		
		return query.getResultList();
	}
	
	public Usuario getUsuario(Integer codUsuario) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u WHERE u.CodUsuario=:codUsuario ORDER BY u.nome ASC", Usuario.class);
		query.setParameter("codUsuario", codUsuario);
		
		List<Usuario> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public Usuario login(String login, String senha) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u WHERE u.login=:login AND u.senha=:senha", Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		List<Usuario> list = query.getResultList();
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	public Boolean usuarioValido(String login) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u WHERE u.login=:login", Usuario.class);
		query.setParameter("login", login);
		
		List<Usuario> list = query.getResultList();
		
		if (list.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	public Boolean usuarioValido(Integer codUsuario, String login) {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM usuarios AS u WHERE u.codUsuario!=:codUsuario AND u.login=:login", Usuario.class);
		query.setParameter("codUsuario", codUsuario);
		query.setParameter("login", login);
		
		List<Usuario> list = query.getResultList();
		
		if (list.isEmpty()) {
			return true;
		}
		
		return false;
	}
}
