package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SubCategoria;
import service.SubCategoriaService;

public class ExcluirSubCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega o id da SubCategoria
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<SubCategoria> subCategorias = (ArrayList<SubCategoria>) session.getAttribute("subCategorias");
		
		// exclui do banco
		SubCategoriaService ss = new SubCategoriaService();
		ss.excluir(id);
		
		// exclui do ArrayList
		int i = Calcula.indiceSubCategoria(subCategorias, new SubCategoria(id));
		subCategorias.remove(i);
		
		session.setAttribute("subCategorias", subCategorias);
		RequestDispatcher view = request.getRequestDispatcher("SubCategoria.jsp");
		view.forward(request, response);
	}
	
}
