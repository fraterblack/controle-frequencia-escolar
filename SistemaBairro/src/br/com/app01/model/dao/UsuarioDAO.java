package br.com.app01.model.dao;

import java.util.List;

import br.com.app01.model.entities.Usuario;

public interface UsuarioDAO {
	
	public Usuario insert(Usuario usuario);
	
	public Usuario update(Usuario usuario);
	
	public Usuario delete(Usuario usuario);
	
	public List<Usuario> getList();
	
	public List<Usuario> getByProperty(String whereClause, Object param);
	
	public Usuario login(String login, String senha);
	
	public Boolean usuarioValido(String login);
	
	public Boolean usuarioValido(Integer codUsuario, String login);
}
