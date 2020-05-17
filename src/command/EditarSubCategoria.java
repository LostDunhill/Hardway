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

public class EditarSubCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// carrega os dados
		int id = (int) session.getAttribute("id");
		String nome = request.getParameter("cdnome");
		int idCategoria = Integer.parseInt(request.getParameter("cdcategoria"));
		
		// pega o ArrayList de SubCategorias da sessão
		ArrayList<SubCategoria> subCategorias = (ArrayList<SubCategoria>) session.getAttribute("subCategorias");
		
		// atualiza a SubCategoria
		SubCategoriaService ss = new SubCategoriaService();
		SubCategoria categoria = new SubCategoria(id, nome, idCategoria);
		ss.atualizar(categoria);
		
		// remove a SubCategoria com dados desatualizados do array e adiciona a nova
		int i = Calcula.indiceSubCategoria(subCategorias, new SubCategoria(id));
		subCategorias.remove(i);
		subCategorias.add(i, categoria);
		
		session.setAttribute("subCategorias", subCategorias);
		RequestDispatcher view = request.getRequestDispatcher("SubCategoria.jsp");
		view.forward(request, response);
	}
	
}
