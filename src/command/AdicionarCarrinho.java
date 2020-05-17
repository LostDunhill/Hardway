package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Produto;

public class AdicionarCarrinho implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;
				
		ArrayList<Produto> produtosCarrinho = (ArrayList<Produto>) session.getAttribute("produtosCarrinho"); // pega os produtos do carrinho
		Produto produto = (Produto) session.getAttribute("produto"); // pega o produto que vai ser adicionado ao carrinho
		String pagina = request.getParameter("pagina"); // pega pagina que vai ser redirecionado

		if (produtosCarrinho == null) { // verifica se já tem produtos no carrinho
			produtosCarrinho = new ArrayList<>(); // cria um array list de produtos do carrinho
			produtosCarrinho.add(produto); // adiciona o produto ao array list
		} else {
			int i = Calcula.indiceProduto(produtosCarrinho, produto); // verifica se já tem um produto igual no carrinho
			if (i != -1) produtosCarrinho.get(i).aumentaQuantidade(); // se sim, ele só aumenta a quantidade em +1
			else produtosCarrinho.add(produto); // se não, ele adiciona o produto ao carrinho
		}
		
		double total = Calcula.total(produtosCarrinho); // calcula o total dos produtos do carrinho
		
		session.setAttribute("total", total); // atribui o total ao atributo "total" da sessão					
		session.setAttribute("produtosCarrinho", produtosCarrinho); // atribui o produtosCarrinho ao atributo "produtosCarrinho" da sessão		
		
		view = request.getRequestDispatcher(pagina); // carrega a pagina
		view.forward(request, response); // redireciona pra pagina
	}
	
}
