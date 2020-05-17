package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ProdutoService;

public class VisualizarProduto implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		HttpSession session = request.getSession();
		
		// carrega o produto para a página de produtos
		ProdutoService ps = new ProdutoService();
		int id = Integer.parseInt(request.getParameter("produto"));
		
		session.setAttribute("produto", ps.carregar(id));
		
		RequestDispatcher view = request.getRequestDispatcher("Produto.jsp");
		view.forward(request, response);
	}
	
}
