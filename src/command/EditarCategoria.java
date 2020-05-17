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

public class EditarCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega os dados
		int id = (int) session.getAttribute("id");
		String nome = request.getParameter("cdnome");	
		
		// instância o service
		CategoriaService cs = new CategoriaService();
		
		// pega o arraylist de categorias da sessão
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) session.getAttribute("categorias");
		
		// verifica se o nome já existe no banco
		if(!(cs.verificarNome(nome))) { // se não, ele atualiza
			Categoria categoria = new Categoria(id, nome);
			cs.atualizar(categoria);
			
			int i = Calcula.indiceCategoria(categorias, new Categoria(id));
			categorias.remove(i);
			categorias.add(i, categoria);
			
			session.setAttribute("categorias", categorias);
			RequestDispatcher view = request.getRequestDispatcher("Categorias.jsp");
			view.forward(request, response);
		} else { // se sim, ele mostra um erro
			request.setAttribute("erro", "Erro ao editar categoria: nome de categoria já cadastrado!");
			request.setAttribute("nome", cs.carregar(id).getNome());
			request.setAttribute("titulo", "Editar Categoria");
			request.setAttribute("command", "EditarCategoria");
			session.setAttribute("id", id);
			RequestDispatcher view = request.getRequestDispatcher("CadastrarCategoria.jsp");
			view.forward(request, response);
		}
	}
	
}
