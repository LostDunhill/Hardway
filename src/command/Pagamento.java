package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pagamento implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// alterna as opções de pagamento, boleto ou cartão
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		RequestDispatcher view = request.getRequestDispatcher("Pagamento.jsp");
		view.forward(request, response);
	}
	
}
