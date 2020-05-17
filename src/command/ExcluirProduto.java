package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Produto;
import service.ProdutoService;

public class ExcluirProduto implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega o id do produto
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");
		
		// exclui do banco
		ProdutoService cs = new ProdutoService();
		cs.excluir(id);
		
		// exclui do ArrayList
		int i = Calcula.indiceProduto(produtos, new Produto(id));
		produtos.remove(i);
		
		session.setAttribute("produtos", produtos);
		RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
		view.forward(request, response);
	}
	
}
