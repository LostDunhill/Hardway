package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import service.ProdutoService;
import service.SubCategoriaService;

public class SelecionarSubCategoria implements Command{
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// mostra os produtos da subCategoria quando seleciona no dropdown
		
		// instância services
		ProdutoService ps = new ProdutoService();
		SubCategoriaService ss = new SubCategoriaService();
		
		// pega o id da SubCategoria
		int idSubCategoria = Integer.parseInt(request.getParameter("subcategoria"));
		
		// pega os produtos da SubCategoria do banco
		ArrayList<Produto> produtosBanco = ps.carregarProdutosDaSubCategoria(idSubCategoria);
		ArrayList<Produto> produtos = new ArrayList<>();
		
		// adiciona somente os produtos que tem estoque no ArrayList "produtos"
		for (Produto produto : produtosBanco) {
			if (produto.getQtdeEstoque() > 0) {
				produtos.add(produto);
			}
		}
		
		request.setAttribute("nomeSubCategoria", ss.carregar(idSubCategoria).getNome()); // titulo da página
		request.setAttribute("produtos", produtos); // produtos da sub Categoria
		
		RequestDispatcher view = request.getRequestDispatcher("SubCategoriaProdutos.jsp");
		view.forward(request, response);
	}
	
}
