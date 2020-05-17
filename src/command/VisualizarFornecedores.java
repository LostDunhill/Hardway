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

public class VisualizarFornecedores implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// carrega a tabela de forncecedores do admin
		
		FornecedorService fs = new FornecedorService();
		ArrayList<Fornecedor> fornecedores = fs.carregarFornecedores();
		
		session.setAttribute("fornecedores", fornecedores);
		RequestDispatcher view = request.getRequestDispatcher("Fornecedores.jsp");
		view.forward(request, response);
	}
	
}
