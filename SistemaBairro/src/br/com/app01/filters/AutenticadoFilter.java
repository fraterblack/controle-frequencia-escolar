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

@WebFilter("/*")
public class AutenticadoFilter implements Filter {

	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		
		Usuario usuario = null;
		
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    
	    HttpSession session = request.getSession();
	    
		if (session != null) {
			usuario = (Usuario) session.getAttribute("usuarioLogado");
		}
	    
	  //Quando em /resources
	    boolean atResources = false;
	    if(request.getRequestURL() != null && request.getRequestURL().toString().contains("javax.faces.resource")){
	    	atResources = true;
	    }
	    
	    //Quando em /login
	    boolean atLogin = false;
	    if(request.getRequestURL() != null && !atResources && request.getRequestURL().toString().contains("login")){
	    	atLogin = true;
	    }
	    
	    
	    //Se não estiver logado, redireciona para a página de login
	    if(usuario == null && !atLogin && !atResources) {
	    	response.sendRedirect(request.getContextPath() + "/login.faces");
	    }
	    
	    //Se já estiver logado, e estiver na página login, redireciona para index.jsp
	    if(usuario != null && atLogin) {
	    	response.sendRedirect(request.getContextPath() + "/index.faces");
	    }
	    
	    filterChain.doFilter(req,res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
