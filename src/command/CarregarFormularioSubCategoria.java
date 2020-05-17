package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categoria;
import service.CategoriaService;
import service.SubCategoriaService;

public class CarregarFormularioSubCategoria implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// reclicar as paginas de editar e criar categoria para a mesma página
		String acao = request.getParameter("acao"); // carrega a ação que se tornará o command
		
		if (acao.equals("CriarSubCategoria")) request.setAttribute("titulo", "Cadastrar SubCategoria"); // se a ação for de criar sub categoria ele mostra o título da página "Criar SubCategoria"
		else {
			// se não, ele mostra os dados da categoria e carrega o título da página como "Editar Categoria"
			SubCategoriaService ss = new SubCategoriaService();
			int id = Integer.parseInt(request.getParameter("id"));
			// coloca os dados nos inputs 
			request.setAttribute("nome", ss.carregar(id).getNome());
			request.setAttribute("idCategoria", ss.carregar(id).getIdCategoria());
			request.setAttribute("titulo", "Editar SubCategoria");
			session.setAttribute("id", id);
		}
		
		// carrega as categorias do banco
		CategoriaService cs = new CategoriaService();
		ArrayList<Categoria> categorias = cs.carregarCategorias();
		
		// passa o array de categorias pra paǵina
		session.setAttribute("categorias", categorias);
		// carrega o respectivo command
		request.setAttribute("command", acao);
		RequestDispatcher view = request.getRequestDispatcher("CadastrarSubCategoria.jsp");
		view.forward(request, response);
	}
	
}
