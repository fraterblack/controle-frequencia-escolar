package br.com.app01.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.app01.model.entities.Usuario;

@WebFilter(
		urlPatterns = "/*", 
		filterName = "Área restrita nível 1",
		description = "Somente usuários administrar e supervisor podem acessar a página"
)
public class AreaRestritaNivel1Filter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    
	    HttpSession session = request.getSession();

	    if (session != null) {
	    	if (request.getRequestURL().toString().contains("/cadastroalunos.") || 
	    		request.getRequestURL().toString().contains("/cadastroturmas.") || 
	    		request.getRequestURL().toString().contains("/gerenciarturmas.") || 
	    		request.getRequestURL().toString().contains("/cadastrodisciplinas.") || 
	    		request.getRequestURL().toString().contains("/cadastromatriculas.")) {
	    		
	    		try {
	    			
	    			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
					
					//Somente usuários "administrar" e "supervisor" podem acessar a página
				    if(!"a".equals(usuario.getTipo()) && !"s".equals(usuario.getTipo())) {
				    	response.sendRedirect(request.getContextPath() + "/acessorestrito.faces");
				    } 
	    		} catch (Exception e) {
	    			
	    		}
	    	}
		}
	    
	    filterChain.doFilter(req,res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
