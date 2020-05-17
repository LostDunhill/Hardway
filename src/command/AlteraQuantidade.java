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

public class AlteraQuantidade implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // pega a sessão
		
		ArrayList<Produto> produtosCarrinho = (ArrayList<Produto>) session.getAttribute("produtosCarrinho"); // pega os produtos do carrinho
		int id = Integer.parseInt(request.getParameter("id")); // pega o id do produto
		int quantidade = Integer.parseInt(request.getParameter("quantidadeProduto")); // pega a quantidade e converte pra int
		
		ProdutoService ps = new ProdutoService(); // instancia o ProdutoService
		int i = Calcula.indiceProduto(produtosCarrinho, ps.carregar(id)); // pega o indice do produto no ArrayList dos produtoCarrinho
		
		produtosCarrinho.get(i).setQuantidade(quantidade); // pega o produto no indice que ele encontrou e muda a quantidade para a escolhida
		
		double total = Calcula.total(produtosCarrinho); // calcula o total dos produtos
		
		session.setAttribute("total", total); // atribui o total ao atributo "total" da sessão
		session.setAttribute("produtosCarrinho", produtosCarrinho); // atribui o produtosCarrinho ao atributo "produtosCarrinho" da sessão
		RequestDispatcher view = request.getRequestDispatcher("Carrinho.jsp"); // carrega a pagina
		view.forward(request, response); // redireciona a pagina
				
	}

}
