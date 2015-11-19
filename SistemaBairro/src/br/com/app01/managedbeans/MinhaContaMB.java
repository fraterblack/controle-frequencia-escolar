package br.com.app01.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.app01.context.SessionContext;
import br.com.app01.model.dao.UsuarioDAOImpl;
import br.com.app01.model.entities.Usuario;
import br.com.app01.util.Message;
import br.com.app01.util.PasswordEncryptor;

@Named(value="minhaContaMB")
@ViewScoped
public class MinhaContaMB  implements Serializable {
	
	private static final long serialVersionUID = 3031085115146169870L;
	
	private String login;
	private String senha;
	private String nome;
	
	private Usuario usuarioLogado;
	
	@Inject
	UsuarioDAOImpl usuarioDao;
	
	@PostConstruct
	public void init() {
		this.usuarioLogado = (Usuario) SessionContext.getInstance().getUsuarioLogado();
		
		setNome(usuarioLogado.getNome());
		setLogin(usuarioLogado.getLogin());
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

	public String salvar() {
		try {
			Usuario usuario = new Usuario();
			usuario.setCodUsuario(usuarioLogado.getCodUsuario());
			usuario.setLogin(getLogin());
			usuario.setNome(getNome());
			usuario.setTipo(usuarioLogado.getTipo());
			
			//Verifica se já existe usuário com o mesmo login
			Boolean usuarioValido = usuarioDao.usuarioValido(usuarioLogado.getCodUsuario(), getLogin());

			if (usuarioValido) {
				if (getSenha().isEmpty()) {
					usuario.setSenha(usuarioLogado.getSenha());
				} else {
					usuario.setSenha(PasswordEncryptor.shaEncrypt(getSenha()));
				}
				
				usuarioDao.update(usuario);
				
				SessionContext.getInstance().setUsuarioLogado(usuario);
				
				Message.successMessage("Seu cadastro foi editado com sucesso");
			} else {
				Message.warningMessage("Já existe um usuário com o login informado");
				
				return "";
			}
		} catch (Exception e) {
			Message.errorMessage(e.getMessage());
		}

		return "";
	}
}
