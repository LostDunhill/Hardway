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

public class VisualizarSubCategorias implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
		// carrega a tabela de subCategorias do admin
		SubCategoriaService ss = new SubCategoriaService();
		ArrayList<SubCategoria> subCategorias = ss.carregarSubCategorias();
		
		session.setAttribute("subCategorias", subCategorias);
		RequestDispatcher view = request.getRequestDispatcher("SubCategoria.jsp");
		view.forward(request, response);
	}

}
