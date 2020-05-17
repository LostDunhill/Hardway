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

public class ExcluirProdutoCarrinho implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega o id do produto
		ArrayList<Produto> produtosCarrinho = (ArrayList<Produto>) session.getAttribute("produtosCarrinho");
		int id = Integer.parseInt(request.getParameter("id"));
		
		// exclui o produto do ArrayList do carrinho
		ProdutoService ps = new ProdutoService();
		int i = Calcula.indiceProduto(produtosCarrinho, ps.carregar(id));
		produtosCarrinho.remove(i);
		
		// Atualiza o total e os produtos do carrinho na sessão
		session.setAttribute("total", Calcula.total(produtosCarrinho));
		session.setAttribute("produtosCarrinho", produtosCarrinho);
		RequestDispatcher view = request.getRequestDispatcher("Carrinho.jsp");
		view.forward(request, response);
	}

}
