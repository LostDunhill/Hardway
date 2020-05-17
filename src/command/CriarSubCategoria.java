package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SubCategoria;
import service.CategoriaService;
import service.SubCategoriaService;

public class CriarSubCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
		// instancia o service
		SubCategoriaService ss = new SubCategoriaService();
		
		// carrega os dados
		String nome = request.getParameter("cdnome");
		int idCategoria = Integer.parseInt(request.getParameter("cdcategoria"));
		
		// verifica se o nome já existe no banco de dados
		if (!(ss.verificarNome(nome))) { // se não, ele cadastra a SubCategoria no banco
			SubCategoria subCategoria = new SubCategoria();
			
			subCategoria.setNome(nome);
			subCategoria.setIdCategoria(idCategoria);
			ss.incluir(subCategoria);
			
			ArrayList<SubCategoria> subCategorias = ss.carregarSubCategorias();
			
			session.setAttribute("subCategorias", subCategorias);
			request.setAttribute("subCategoria", subCategoria);
			RequestDispatcher view = request.getRequestDispatcher("SubCategoria.jsp");
			view.forward(request, response);
		} else { // se sim, ele retorna um erro
			request.setAttribute("erro", "Erro ao cadastrar sub categoria! Já existe uma sub categoria com esse nome.");
			request.setAttribute("titulo", "Editar SubCategoria");
			request.setAttribute("command", "EditarSubCategoria");
			
			RequestDispatcher view = request.getRequestDispatcher("CadastrarSubCategoria.jsp");
			view.forward(request, response);
		}
	
	}
	
}
