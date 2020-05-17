package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Fornecedor;
import service.FornecedorService;

public class ExcluirFornecedor implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// pega o id do fornecedor
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) session.getAttribute("fornecedores");
		
		// exclui do banco
		FornecedorService cs = new FornecedorService();
		cs.excluir(id);
		
		// exclui do ArrayList
		int i = Calcula.indiceFornecedor(fornecedores, new Fornecedor(id));
		fornecedores.remove(i);
		
		session.setAttribute("fornecedores", fornecedores);
		RequestDispatcher view = request.getRequestDispatcher("Fornecedores.jsp");
		view.forward(request, response);
	}
	
}
