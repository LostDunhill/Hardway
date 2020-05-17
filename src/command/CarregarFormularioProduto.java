package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Fornecedor;
import model.Produto;
import model.SubCategoria;
import service.FornecedorService;
import service.ProdutoService;
import service.SubCategoriaService;

public class CarregarFormularioProduto implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// reclicar as paginas de editar e criar categoria para a mesma página
		
		// instancia os services
		FornecedorService fs = new FornecedorService();
		SubCategoriaService ss = new SubCategoriaService();
		
		// carrega os fornecedores e as subCategorias do banco
		ArrayList<Fornecedor> fornecedores = fs.carregarFornecedores();
		ArrayList<SubCategoria> subCategorias = ss.carregarSubCategorias();
		
		//pega a ação (command)
		String acao = request.getParameter("acao");
		
		// passa os arrays como atributo para a página
		request.setAttribute("fornecedores", fornecedores);
		request.setAttribute("subCategorias", subCategorias);
		
		
		if (acao.equals("CriarProduto")) request.setAttribute("titulo", "Cadastrar Produto"); // se a ação for de criar produto ele mostra o título da página "Criar Produto"
		else {
			// se não, ele mostra os dados da categoria e carrega o título da página como "Editar Produto"
			ProdutoService ps = new ProdutoService();
			int id = Integer.parseInt(request.getParameter("id"));
			Produto produto = ps.carregar(id);
			// coloca os dados nos inputs 
			request.setAttribute("nome", produto.getNome());
			request.setAttribute("desc", produto.getDesc());
			request.setAttribute("preco", produto.getPreco());
			request.setAttribute("qtdeestoque", produto.getQtdeEstoque());
			request.setAttribute("imagem", produto.getBase64Imagem());
			request.setAttribute("idFornecedor", produto.getIdFornecedor());
			request.setAttribute("idSubCategoria", produto.getIdSubCategoria());
			request.setAttribute("titulo", "Editar Produto");
			session.setAttribute("id", id);
		}
		
		session.setAttribute("command", acao); // carrega o respectivo command
		RequestDispatcher view = request.getRequestDispatcher("CadastrarProduto.jsp");
		view.forward(request, response);
	}
	
}
