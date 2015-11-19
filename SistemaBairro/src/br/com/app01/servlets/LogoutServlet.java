package br.com.app01.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout.faces")
public class LogoutServlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = -8089188745390119920L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.getSession().removeAttribute("usuarioLogado");
		
		res.sendRedirect(req.getContextPath() + "/login.faces");
	}
}