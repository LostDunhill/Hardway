package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FornecedorService;

public class CarregarFormularioFornecedor implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// reciclar as paginas de editar e criar categoria para a mesma página
		String acao = request.getParameter("acao"); // carrega a ação que se tornará o command
		
		if (acao.equals("CadastroFornecedor")) request.setAttribute("titulo", "Cadastrar Fornecedor"); // se a ação for de cadastrar o fornecedor, ele mostra o título da página como "Cadatrar Fornecedor"
		else {
			// se não, ele mostra os dados do fornecedor e carrega o título da página como "Editar Fornecedor"
			FornecedorService fs = new FornecedorService();
			int id = Integer.parseInt(request.getParameter("id"));
			// coloca os dados nos inputs
			request.setAttribute("nome", fs.carregar(id).getNome());
			request.setAttribute("cnpj", fs.carregar(id).getCnpj());
			request.setAttribute("email", fs.carregar(id).getEmail());
			request.setAttribute("telefone", fs.carregar(id).getTelefone());
			request.setAttribute("titulo", "Editar Fornecedor");
			session.setAttribute("id", id);
		}
		
		request.setAttribute("command", acao);
		RequestDispatcher view = request.getRequestDispatcher("CadastrarFornecedor.jsp");
		view.forward(request, response);
	}
	
}
