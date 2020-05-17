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

public class ExcluirCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega o id da categoria
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) session.getAttribute("categorias");
		
		// exclui do banco
		CategoriaService cs = new CategoriaService();
		cs.excluir(id);
		
		// exclui do ArrayList
		int i = Calcula.indiceCategoria(categorias, new Categoria(id));
		categorias.remove(i);
		
		session.setAttribute("categorias", categorias);
		RequestDispatcher view = request.getRequestDispatcher("Categorias.jsp");
		view.forward(request, response);
	}
	
}
