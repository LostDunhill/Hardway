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

public class Buscar implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher view;
		
		String chave = request.getParameter("chave"); // pega o que foi digitado no campo de busca
		
		if (chave == null || chave.length() < 0) { // se não tiver nada escrito, ele só redireciona pro index
			view = request.getRequestDispatcher("Index.jsp");
		} else { // se não, ele procura os itens no banco que tem nome parecido com a "chave" e que tenha a quantidade no estoque maior que zero e exibe para o usuário
			ProdutoService ps = new ProdutoService(); // instancia ProdutoService
			ArrayList<Produto> produtosBanco =  ps.carregarProdutos(chave); // carrega os produtos do banco
			ArrayList<Produto> produtos = new ArrayList<>();
			
			for (Produto produto : produtosBanco) {
				if (produto.getQtdeEstoque() > 0) {
					produtos.add(produto); // adiciona os produtos que estão disponiveis no estoque no ArrayList de produtos
				}
			}
		
			request.setAttribute("nomeSubCategoria", "Exibindo resultados para: " + chave); // titulo da página
			session.setAttribute("produtos", produtos);
			view = request.getRequestDispatcher("SubCategoriaProdutos.jsp");
		}
		
		view.forward(request, response);
		
	}
	
}
