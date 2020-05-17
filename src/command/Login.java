package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Compra;
import model.Produto;
import service.AdminService;
import service.ClienteService;
import service.CompraService;
import service.ProdutoService;

public class Login implements Command {
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteService cs = new ClienteService();
		
		// pega os dados inseridos
		String email = request.getParameter("cdemail");
		String senha = request.getParameter("cdsenha");
		
		// se ele estiver vindo da pagina de carrinho, 
		// ou seja, está realizando uma compra, 
		// ele será redirecionado para a página de pagamento, 
		// se não ele será redirecionado para a "Index.jsp"
		String pagina = request.getParameter("pagina");
		if (pagina.length() == 0) {
			pagina = "Index.jsp";
		}
		
		HttpSession session = request.getSession();
		
		// Verificação adm
		if (email.equals("admin") && verificaSenha(cs.senhaToHash(senha))) {
			CompraService service = new CompraService();
			ProdutoService ps = new ProdutoService();
			
			ArrayList<Compra> pedidos = service.listar();
			ArrayList<Produto> produtos = ps.carregarProdutos();
			
			// inverte o array de pedidos, para que os pedidos mais recentes fiquem em primerio
			Collections.reverse(pedidos);
			
			session.setAttribute("produtos", produtos);
			session.setAttribute("usuario", email);
			session.setAttribute("pedidos", pedidos);
			response.sendRedirect("Admin.jsp");
		}
		// Login cliente
		else if (cs.carregaEmail(email).getNome() != null) { // verifica se o email existe no banco
			Cliente cliente = cs.carregaEmail(email);
			senha = cs.senhaToHash(senha); // transforma a senha digitada em uma hash
			if(senha.equals(cliente.getSenha())) { // compara as hashs do banco com a da senha digitada, se for sim, ele faz o login
				session.setAttribute("cliente", cliente);
				response.sendRedirect(pagina);
			} else { // se não ele retorna erro
				request.setAttribute("erro", "Erro: E-mail ou senha inválidos!");
				session.setAttribute("pagina", pagina);
				RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
				view.forward(request, response);
			}
		} else { // retorna erro se o email não existir
			request.setAttribute("erro", "Erro: E-mail ou senha inválidos!");
			session.setAttribute("pagina", pagina);
			RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
			view.forward(request, response);
		}
	}
	
	private static boolean verificaSenha(String senha) {
       AdminService as = new AdminService();
       if (senha.equals(as.pass())) {
    	   return true;
       }
       return false;
    }
}
