package command;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Produto;
import service.ProdutoService;
import service.SubCategoriaService;

public class CriarProduto implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		SubCategoriaService ss = new SubCategoriaService();
		ProdutoService ps = new ProdutoService();
		
		InputStream inputStream = null;
		
		//carrega os dados do produto
		String nome = request.getParameter("cdnome");
		String desc = request.getParameter("cddesc");
		double preco = Double.parseDouble(request.getParameter("cdpreco"));
		int qtdeEstoque = Integer.parseInt(request.getParameter("cdqtdeestoque"));
		
		// pega o arquivo da imagem
		Part filePart = request.getPart("cdimagem");
		if (filePart != null) {
            inputStream = filePart.getInputStream();
		}
		
		// converte o arquivo em um array de bytes
		byte[] imagem = new byte[(int) filePart.getSize()];
		inputStream.read(imagem);
		inputStream.close();
		
		int idFornecedor = Integer.parseInt(request.getParameter("cdfornecedor"));
		int idSubCategoria = Integer.parseInt(request.getParameter("cdsubcategoria"));
		int idCategoria = ss.carregar(idSubCategoria).getIdCategoria();
		
		// inclui o produto no banco
		Produto produto = new Produto(0, nome, desc, preco, qtdeEstoque, imagem, idFornecedor, idSubCategoria, idCategoria);
		ps.incluir(produto);
		
		// carrega o ArrayList de Produtos com o novo produto cadastrado
		ArrayList<Produto> produtos = ps.carregarProdutos();
		
		session.setAttribute("produtos", produtos);
		request.setAttribute("produto", produto);
		RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
		view.forward(request, response);
	}
	
}
