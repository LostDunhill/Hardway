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

public class CadastroFornecedor implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		FornecedorService fs = new FornecedorService();
		
		// carrega os dados inseridos pelo admin
		String nome = request.getParameter("cdnome");
		String cnpj = request.getParameter("cdcnpj");
		String email = request.getParameter("cdemail");
		long telefone = Long.parseLong(request.getParameter("cdtelefone"));
		
		// verifica a disponibilidade do cnpj, se sim, ele cadastra e carrega no ArrayList
		if (!(fs.verificarCnpj(cnpj))) { 
			Fornecedor fornecedor = new Fornecedor(0, nome, cnpj, email, telefone);
			fs.incluir(fornecedor);
			
			ArrayList<Fornecedor> fornecedores = fs.carregarFornecedores();
			
			session.setAttribute("fornecedores", fornecedores);
			request.setAttribute("fornecedor", fornecedor);
			RequestDispatcher view = request.getRequestDispatcher("Fornecedores.jsp");
			view.forward(request, response);
		} 
		// se não, ele mostra o erro de que o cnpj já foi cadastrado
		else { 
			request.setAttribute("erro", "CNPJ já cadastrado");
			request.setAttribute("titulo", "Editar Fornecedor");
			request.setAttribute("command", "CadastroFornecedor");
			RequestDispatcher view = request.getRequestDispatcher("CadastrarFornecedor.jsp");
			view.forward(request, response);
		}
		
		
	}
}
