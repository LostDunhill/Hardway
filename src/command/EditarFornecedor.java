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

public class EditarFornecedor implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// carrega os dados
		int id = (int) session.getAttribute("id");
		String nome = request.getParameter("cdnome");
		String cnpj = request.getParameter("cdcnpj");
		String email = request.getParameter("cdemail");
		long telefone = Long.parseLong(request.getParameter("cdtelefone"));
		
		// pega o ArrayList da sessão
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) session.getAttribute("fornecedores");
		
		// atualiza no banco
		FornecedorService fs = new FornecedorService();
		Fornecedor fornecedor = new Fornecedor(id, nome, cnpj, email, telefone);
		fs.atualizar(fornecedor);
		
		// remove o fornecedor com os dados desatualizados do ArrayList e coloca o novo no lugar
		int i = Calcula.indiceFornecedor(fornecedores, new Fornecedor(id));
		fornecedores.remove(i);
		fornecedores.add(i, fornecedor);
		
		session.setAttribute("fornecedores", fornecedores);
		RequestDispatcher view = request.getRequestDispatcher("Fornecedores.jsp");
		view.forward(request, response);
	}
	
}
