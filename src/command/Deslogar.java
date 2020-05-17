package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deslogar implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		HttpSession session = request.getSession();
		// invalida  a sessão e redireciona pro login
		session.invalidate(); 
		response.sendRedirect("Login.jsp");
	}
	
}
