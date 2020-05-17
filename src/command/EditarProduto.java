package command;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Produto;
import service.ProdutoService;
import service.SubCategoriaService;

public class EditarProduto implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// instancia os services
		SubCategoriaService ss = new SubCategoriaService();
		ProdutoService ps = new ProdutoService();
		
		InputStream inputStream = null;
		
		// carrega os dados
		int id = (int) session.getAttribute("id");
		String nome = request.getParameter("cdnome");
		String desc = request.getParameter("cddesc");
		double preco = Double.parseDouble(request.getParameter("cdpreco"));
		int qtdeEstoque = Integer.parseInt(request.getParameter("cdqtdeestoque"));
		
		byte[] imagem;
		
		// carrega a imagem nova
		Part filePart = request.getPart("cdimagem"); 
		if (filePart != null && filePart.getSize() != 0) { // verifica se o usuario colocou uma imagem nova, se sim, ele converte em um array de bytes
            inputStream = filePart.getInputStream();
            imagem = new byte[(int) filePart.getSize()];
    		inputStream.read(imagem);
    		inputStream.close();
		} else { // se não ele pega a string da base64 da imagem antiga e converte num array de bytes
			String imagemBase64 = request.getParameter("imagem");
			imagem = Base64.getDecoder().decode(imagemBase64);
		}
		
		// carrega o restante dos dados
		int idFornecedor = Integer.parseInt(request.getParameter("cdfornecedor"));
		int idSubCategoria = Integer.parseInt(request.getParameter("cdsubcategoria"));
		int idCategoria = ss.carregar(idSubCategoria).getIdCategoria();
		
		// atualiza no banco
		Produto produto = new Produto(id, nome, desc, preco, qtdeEstoque, imagem, idFornecedor, idSubCategoria, idCategoria);
		ps.atualizar(produto);
		
		// remove o produto com os dados desatualizados do ArrayList e adiciona o novo
		ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");
		int i = Calcula.indiceProduto(produtos, produto);
		produtos.remove(i);
		produtos.add(i, produto);
		
		session.setAttribute("produtos", produtos);
		request.setAttribute("produto", produto);
		RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
		view.forward(request, response);
	}
	
}
