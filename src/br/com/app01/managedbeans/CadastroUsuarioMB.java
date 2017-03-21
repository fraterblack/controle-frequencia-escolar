package br.com.app01.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.model.dao.UsuarioDAOImpl;
import br.com.app01.model.entities.Usuario;
import br.com.app01.util.Message;
import br.com.app01.util.PasswordEncryptor;

@Named(value="cadUsuarioMB")
@ViewScoped
public class CadastroUsuarioMB  implements Serializable {
	
	private static final long serialVersionUID = -3689092466154807682L;
	
	private Integer codUsuario;
	private String login;
	private String senha;
	private String nome;
	private String tipo;
	
	private List<SelectItem> listaTiposDeUsuarios;
	
	private String senhaAntiga;

	private Boolean modoEdicao = false;

	@Inject
	UsuarioDAOImpl usuarioDao;

	public List<SelectItem> getListaTiposDeUsuarios() {
		listaTiposDeUsuarios = new ArrayList<SelectItem>();
		listaTiposDeUsuarios.add(new SelectItem("p", "Professor "));
		listaTiposDeUsuarios.add(new SelectItem("s", "Supervisor"));
		listaTiposDeUsuarios.add(new SelectItem("a", "Administrador"));
		
		return listaTiposDeUsuarios;
	}
	
	public String getTipoUsuario(String codTipo) {
		for (SelectItem item: listaTiposDeUsuarios) {
			if (item.getValue().equals(codTipo)) {
				return item.getLabel();
			}
		}
		
		return "";
	}
	
	public Integer getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private String getSenhaAntiga() {
		return senhaAntiga;
	}
	private void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}
	
	public Boolean getModoEdicao() {
		return modoEdicao;
	}
	public void setModoEdicao(Boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}
	
	public List<Usuario> getListaUsuarios() {
		return (List<Usuario>) usuarioDao.getList();
	}

	public void limpar() {
		setCodUsuario(0);
		setLogin(null);
		setSenha(null);
		setNome(null);
		setTipo(null);
		
		setSenhaAntiga(null);
		
		setModoEdicao(false);
	}
	
	public String gravarUsuario() {
		try {
			Usuario usuario = new Usuario();
			usuario.setCodUsuario(getCodUsuario());
			usuario.setLogin(getLogin());
			usuario.setNome(getNome());
			usuario.setTipo(getTipo());
					
			if (usuario.getCodUsuario() == null || usuario.getCodUsuario() == 0) {
				//Verifica se já existe usuário com o mesmo login
				Boolean usuarioValido = usuarioDao.usuarioValido(getLogin());

				if (usuarioValido) {
					usuario.setCodUsuario(null);
					usuario.setSenha(PasswordEncryptor.shaEncrypt(getSenha()));
					
					usuarioDao.insert(usuario);
				} else {
					Message.warningMessage("Já existe um usuário com o login informado");
					
					return "";
				}
			} else {
				//Verifica se já existe usuário com o mesmo login
				Boolean usuarioValido = usuarioDao.usuarioValido(usuario.getCodUsuario(), getLogin());

				if (usuarioValido) {
					if (getSenha().isEmpty()) {
						usuario.setSenha(getSenhaAntiga());
					} else {
						usuario.setSenha(PasswordEncryptor.shaEncrypt(getSenha()));
					}
					
					usuarioDao.update(usuario);
				} else {
					Message.warningMessage("Já existe um usuário com o login informado");
					
					return "";
				}
			}
			
			limpar();
		} catch (Exception e) {
			Message.errorMessage(e.getMessage());
		}

		return "";
	}
	
	public void editar(Usuario usuario) {
		setCodUsuario(usuario.getCodUsuario());
		setLogin(usuario.getLogin());
		setNome(usuario.getNome());
		setTipo(usuario.getTipo());
		
		setSenhaAntiga(usuario.getSenha());
		
		setModoEdicao(true);
	}
	
	public void excluir(Usuario usuario) {
		limpar();
		
		if (Integer.valueOf(1).equals(usuario.getCodUsuario())) {
			Message.errorMessage("Não é possível excluir este usuário");
			
			return;
		}
		
		try {
			usuarioDao.delete(usuario);
		} catch (Exception e) {
			Message.errorMessage("Não é possível excluir o usuário");
		}
	}
}
