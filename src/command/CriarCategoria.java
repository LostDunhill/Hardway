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

public class CriarCategoria implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// carrega os dados
		String nome = request.getParameter("cdnome");
		
		// instância o service
		CategoriaService cs = new CategoriaService();
		
		// verifica se o nome já existe no banco
		if (!(cs.verificarNome(nome))) { // se não, ele cadastra a categoria no banco
			Categoria categoria = new Categoria();	
			categoria.setNome(nome);
			cs.incluir(categoria);
			
			ArrayList<Categoria> categorias = cs.carregarCategorias();
			
			session.setAttribute("categorias", categorias);
			request.setAttribute("categoria", categoria);
			RequestDispatcher view = request.getRequestDispatcher("Categorias.jsp");
			view.forward(request, response);
		} else { // se sim, ele retorna um erro
			request.setAttribute("erro", "Erro ao editar categoria: nome de categoria já cadastrado!");
			request.setAttribute("titulo", "Cadastrar Categoria");
			request.setAttribute("command", "CriarCategoria");
			RequestDispatcher view = request.getRequestDispatcher("CadastrarCategoria.jsp");
			view.forward(request, response);
		}
	}
	
}
