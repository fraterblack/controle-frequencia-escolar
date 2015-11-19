package br.com.app01.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/informacoes")
public class Servlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = -3904046157405654354L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
        PrintWriter out  = res.getWriter();
		
		out.println("<html>");
		   out.println("<body>");
		     out.println("<h1>Edvaldo da Rosa</h1>");
		     out.println("<h3>(48)8842-7300</h3>");
		   out.println("</body>");
		out.println("</html>");   
	}
}
