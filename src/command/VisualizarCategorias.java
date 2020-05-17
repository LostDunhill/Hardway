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

public class VisualizarCategorias implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// carrega a tabela de categorias do admin
		
		CategoriaService cs = new CategoriaService();
		ArrayList<Categoria> categorias = cs.carregarCategorias();
		
		session.setAttribute("categorias", categorias);
		RequestDispatcher view = request.getRequestDispatcher("Categorias.jsp");
		view.forward(request, response);
	}

}
