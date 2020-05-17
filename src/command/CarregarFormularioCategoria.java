package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CategoriaService;

public class CarregarFormularioCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// reclicar as paginas de editar e criar categoria para a mesma página
		String acao = request.getParameter("acao"); // carrega a ação que se tornará o command
		
		if (acao.equals("CriarCategoria")) request.setAttribute("titulo", "Cadastrar Categoria"); // se a ação for de criar categoria ele mostra o título da página "Criar Categoria"
		else { 
			// se não, ele mostra os dados da categoria e carrega o título da página como "Editar Categoria"
			CategoriaService cs = new CategoriaService();
			int id = Integer.parseInt(request.getParameter("id"));
			// coloca os dados nos inputs 
			request.setAttribute("nome", cs.carregar(id).getNome());
			request.setAttribute("titulo", "Editar Categoria");
			session.setAttribute("id", id);
		}
		
		request.setAttribute("command", acao); // carrega o respectivo command
		RequestDispatcher view = request.getRequestDispatcher("CadastrarCategoria.jsp");
		view.forward(request, response);
	}
	
}
