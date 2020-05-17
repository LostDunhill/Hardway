package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Produto;

public class LimparCarrinho implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		RequestDispatcher view = null;
		
		// reseta o ArrayList e o total
		ArrayList<Produto> produtosCarrinho = new ArrayList<>();
		int total = 0;
		
		session.setAttribute("total", total);
		session.setAttribute("produtosCarrinho", produtosCarrinho);
		view = request.getRequestDispatcher("Carrinho.jsp");
		view.forward(request, response);
	}
	
}
